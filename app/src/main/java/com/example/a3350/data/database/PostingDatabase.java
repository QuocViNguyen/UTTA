package com.example.a3350.data.database;

import com.example.a3350.data.PostingDataInterface;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostingDatabase implements PostingDataInterface
{
    private String db;

    public PostingDatabase(String db)
    {
        this.db = db;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + db + ";shutdown=true;create=false", "SA", "");
    }

    private Posting fromResultSet(ResultSet resultSet) throws SQLException
    {
        String faculty = resultSet.getString("faculty");
        int cid = resultSet.getInt("cid");
        String title = resultSet.getNString("title");
        double price = resultSet.getDouble("price");
        String detail = resultSet.getString("detail");
        boolean highlighted = resultSet.getBoolean("highlighted");
        int howOld = resultSet.getInt("howold");
        User owner = Filter.getUserByEmail(resultSet.getString("owner"));
        Course course = Filter.getCourseByIdAndFaculty(cid, faculty);
        int pid = resultSet.getInt("pid");
        return new Posting(pid, owner, course, title, price, detail, highlighted, howOld);
    }

    @Override
    public List<Posting> getPostings()
    {
        List<Posting> postings = new ArrayList<>();
        try
        {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from POSTING");
            while (resultSet.next())
            {
                Posting posting = fromResultSet(resultSet);
                postings.add(posting);
            }
            statement.close();
            resultSet.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return postings;
    }

    @Override
    public void addPosting(User owner, Course course, String title, double price, String detail, boolean highlighted, int howOld)
    {
        Posting posting = new Posting(Filter.postID++,owner,course,title,price,detail,highlighted,howOld);
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO POSTING (pid, faculty, cid, title, price, detail, highlighted, howold, owner) values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            st.setInt(1,posting.getId());
            st.setString(2, posting.getCourse().getFaculty());
            st.setInt(3,posting.getCourse().getCourseID());
            st.setString(4,posting.getTitle());
            st.setDouble(5,posting.getPrice());
            st.setString(6,posting.getDetail());
            st.setBoolean(7,posting.isHighlighted());
            st.setInt(8,posting.getHowOld());
            st.setString(9,posting.getOwner().getEmail());
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePosting(int pID, String newTitle, String newDetail, Course newCourse, boolean newHighlighted, double newPrice, int newHowOld)
    {
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("UPDATE POSTING SET faculty = ?, cID=?, title=?, detail=?, price=?, highlighted=?, howOld=? WHERE pID=?;");
            st.setString(1, newCourse.getFaculty());
            st.setInt(2,newCourse.getCourseID());
            st.setString(3,newTitle);
            st.setString(4,newDetail);
            st.setDouble(5,newPrice);
            st.setBoolean(6,newHighlighted);
            st.setInt(7,newHowOld);
            st.setInt(8,pID);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void removePosting(Posting posting)
    {
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("DELETE FROM POSTING WHERE pID=?;");
            st.setInt(1,posting.getId());
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
