package com.example.a3350.logic;

import com.example.a3350.application.Main;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Filter
{
    public static boolean usingDatabase = true;
    private static String db = Main.getDBPathName();
    public static int postID = 0;

    private static Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + db + ";shutdown=true;create=false", "SA", "");
    }

    public static List<Posting> byCourses(List<Posting> postings, String courseFaculty, int courseID)
    {
        List<Posting> newPostings = new ArrayList<>();
        for (Posting posting : postings) {
            if (posting.getCourse().getFaculty().equalsIgnoreCase(courseFaculty) && posting.getCourse().getCourseID() == courseID)
                newPostings.add(posting);
        }
        return newPostings;
    }

    public static List<Posting> byFaculty(List<Posting> postings, String faculty) {
        List<Posting> newPostings = new ArrayList<>();
        for (Posting posting : postings) {
            if (posting.getCourse().getFaculty().equalsIgnoreCase(faculty))
                newPostings.add(posting);
        }
        return newPostings;
    }

    public static List<Posting> byUserEmail(List<Posting> postings, String email)
    {
        List<Posting> newPostings = new ArrayList<>();
        for (Posting posting : postings) {
            if (posting.getOwner().getEmail().equals(email))
                newPostings.add(posting);
        }
        return newPostings;
    }


    public static void sortByPrices(List<Posting> postings)
    {
        Collections.sort(postings, new sortByPrice());
    }

    public static User getUserByEmail(String email)
    {
        List<User> users = new AccessUsers().getUsers();
        User user = null;
        for (int i = 0; i < users.size() && user == null; i++) {
            if(users.get(i).getEmail().equals(email))
                user = users.get(i);
        }
        return user;
    }

    public static List<Course> getCoursesByFacAndIn(List<Course> courses, String faculty, Institution institution)
    {
        List<Course> newCourses = new ArrayList<>();
        for (Course course : courses) {
            if(course.getFaculty().equals(faculty) && course.getInstitution().equals(institution))
                newCourses.add(course);
        }
        return newCourses;
    }

    //actually compares the toString from course, should think of better of it
    public static Course getCourseByName(List<Course> courses, String name)
    {
        Course course = null;
        for (int i = 0; i < courses.size() && course == null; i++) {
            if(courses.get(i).toString().equals(name))
                course = courses.get(i);
        }
        return course;
    }

    public static Posting getPostByID( List<Posting> postings,int postID)
    {
        Posting posting = null;
        for (Posting post : postings)
        {
            if (post.getId() == postID)
                posting = post;
        }
        return posting;
    }

    //should have passed a parameter in accessPostings.getPostings(institution)
    public static List<Posting> getPostingsByUserInstitution( List<Posting> postings,Institution institution)
    {
        List<Posting> newPostings = new ArrayList<>();
        for (Posting post : postings) {
            if (post.getOwner().getInstitution().equals(institution))
                newPostings.add(post);
        }
        return newPostings;
    }

    public static Institution getInstitutionByName(String name)
    {
        Institution institution = null;
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM INSTITUTION WHERE name =?");
            st.setString(1,name);
            ResultSet result = st.executeQuery();
            if (result.next())
                institution = new Institution(result.getString("name"), result.getString("domain"));
            st.close();
            result.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return institution;
    }

    //could have done this in better way by passing an institution parameter in accessCourses.getCourses()
    //will do this, if time permits
    public static List<Course> getCoursesByInstitution(List<Course> courses, Institution institution)
    {
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : courses)
        {
            if (course.getInstitution().equals(institution))
                filteredCourses.add(course);
        }
        return filteredCourses;
    }

    //the courses here are from one institution, so just get the faculties
    public static List<String> getFacultiesFromCourses(List<Course> courses)
    {
        List<String> faculties = new ArrayList<>();
        for (Course course : courses)
        {
            if(!faculties.contains(course.getFaculty()))
                faculties.add(course.getFaculty());
        }
        return faculties;
    }

    public static Course getCourseByIdAndFaculty(int id, String faculty)
    {
        Course course = null;
        try
        {
            Connection connection = connection();
            PreparedStatement statement = connection.prepareStatement("Select * from COURSE where faculty = ? and cid = ?");
            statement.setString(1, faculty);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                Institution institution = getInstitutionByName(resultSet.getString("institution"));
                course = new Course(faculty, id, resultSet.getString("name"), institution);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return course;
    }

    public static int getIndexOfInstitution(List<Institution> institutions, Institution institution)
    {
        int index = -1;
        for (int i = 0; i < institutions.size() && index == -1; i++)
        {
            if(institutions.get(i).equals(institution))
                index = i;
        }
        return index;
    }

    public static void setPostID()
    {
        if(usingDatabase)
        {
            try
            {
                Connection connection = connection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select count(*) from POSTING;");
                if (resultSet.next())
                    postID = resultSet.getInt(1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            postID = Math.max(1,postID);
    }
}

class sortByPrice implements Comparator<Posting>
{
    @Override
    public int compare(Posting posting, Posting t1) {
        return (int)(posting.getPrice() - t1.getPrice());
    }
}
