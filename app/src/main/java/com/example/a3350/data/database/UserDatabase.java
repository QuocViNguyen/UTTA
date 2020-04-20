package com.example.a3350.data.database;

import com.example.a3350.data.UserDataInterface;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase implements UserDataInterface
{
    private String db;

    public UserDatabase(String db)
    {
        this.db = db;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+db+";shutdown=true;create=false", "SA", "");
    }

    private User fromResultSet(ResultSet resultSet) throws SQLException
    {
        String name = resultSet.getString("name");
        Institution institution = Filter.getInstitutionByName(resultSet.getString("institution"));
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(name, institution, email, password);
    }

    @Override
    public List<User> getUsers()
    {
        List<User> users = new ArrayList<>();
        try
        {
            Connection c = connection();
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM USER");
            while (result.next())
            {
                User user = fromResultSet(result);
                users.add(user);
            }
            result.close();
            st.close();
        }
        catch(SQLException e )
        {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addUser(User user)
    {
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO USER (name, email, password, institution) VALUES (?, ?, ?, ?);");
            st.setString(1,user.getName());
            st.setString(2,user.getEmail());
            st.setString(3,user.getPassword());
            st.setString(4,user.getInstitution().getName());
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(String oldEmail, String newName, String newEmail, String newPass, Institution newInstitution)
    {
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("UPDATE USER SET name=?, email=?, password=?, institution=? where email=?");
            st.setString(1,newName);
            st.setString(2,newEmail);
            st.setString(3,newPass);
            st.setString(4,newInstitution.getName());
            st.setString(5,oldEmail);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public float getRating(User ofUser)
    {
        float rating = 0;
        try
        {
            Connection connection = connection();
            PreparedStatement statement = connection.prepareStatement("select avg(rating) from RATING where RATING.RATED = ?;");
            statement.setString(1, ofUser.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                rating = resultSet.getFloat(1);
            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rating;
    }

    @Override
    public int getRatedBy(User ofUser)
    {
        int numOfRaters = 0;
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("select count(*) from RATING where RATING.RATED = ?;");
            st.setString(1,ofUser.getEmail());
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next())
                numOfRaters = resultSet.getInt(1);
            resultSet.close();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return numOfRaters;
    }

    @Override
    public float getRatingFromUser(User ofUser, String fromUser)
    {
        float rating = 0;
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("select rating from rating where RATED = ? and rater = ?;");
            st.setString(1,ofUser.getEmail());
            st.setString(2,fromUser);
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next())
                rating = resultSet.getFloat(1);
            resultSet.close();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rating;
    }

    @Override
    public void addRating(User toUser, float newRating, String fromUser)
    {
        if(alreadyRated(toUser, fromUser))
            updateRating(toUser, newRating, fromUser);
        else
            insertRating(toUser,newRating, fromUser);
    }

    private boolean alreadyRated(User toUser, String fromUser)
    {
        boolean rated = false;
        try
        {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from rating where RATED = ? and RATER = ?;");
            preparedStatement.setString(1,toUser.getEmail());
            preparedStatement.setString(2,fromUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                rated = resultSet.getInt(1) != 0;
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rated;
    }

    private void insertRating(User toUser, float newRating, String fromUser)
    {
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("insert into rating values (?, ?, ?);");
            st.setString(1,toUser.getEmail());
            st.setDouble(2,newRating);
            st.setString(3,fromUser);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void updateRating(User toUser, float newRating, String fromUser)
    {
        try
        {
            Connection connection = connection();
            PreparedStatement st = connection.prepareStatement("update rating set rating = ? where rated = ? and rater = ?;");
            st.setDouble(1,newRating);
            st.setString(2,toUser.getEmail());
            st.setString(3,fromUser);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
