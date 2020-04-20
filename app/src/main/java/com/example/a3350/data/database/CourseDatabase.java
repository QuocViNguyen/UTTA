package com.example.a3350.data.database;

import com.example.a3350.data.CourseDataInterface;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Institution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDatabase implements CourseDataInterface
{
    private String db;

    public CourseDatabase(String db)
    {
        this.db = db;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + db + ";shutdown=true;create=false", "SA", "");
    }

    private Course fromResultSet(ResultSet resultSet) throws SQLException
    {
        String faculty = resultSet.getString("faculty");
        int courseID = resultSet.getInt("cid");
        String name = resultSet.getString("name");
        Institution institution = Filter.getInstitutionByName(resultSet.getString("institution"));
        return new Course(faculty, courseID, name, institution);
    }

    @Override
    public List<Course> getCourses()
    {
        List<Course> courses = new ArrayList<>();
        try
        {
            Connection connection = connection();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM COURSE");
            while (result.next())
            {
                Course course = fromResultSet(result);
                courses.add(course);
            }
            result.close();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<String> getFaculties()
    {
        List<String> faculties = new ArrayList<>();
        try
        {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select faculty from COURSE");
            while (resultSet.next())
            {
                String faculty = resultSet.getString("faculty");
                if(!faculties.contains(faculty))
                    faculties.add(faculty);
            }
            statement.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return faculties;
    }
}
