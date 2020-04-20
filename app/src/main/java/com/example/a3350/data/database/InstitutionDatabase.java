package com.example.a3350.data.database;

import com.example.a3350.data.InstitutionDataInterface;
import com.example.a3350.objects.Institution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InstitutionDatabase implements InstitutionDataInterface
{
    private String db;

    public InstitutionDatabase(String db)
    {
        this.db = db;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + db + ";shutdown=true;create=false", "SA", "");
    }

    private Institution fromResultSet(ResultSet resultSet) throws SQLException
    {
        String name = resultSet.getString("name");
        String domain = resultSet.getString("domain");
        return new Institution(name, domain);
    }

    @Override
    public List<Institution> getInstitutions()
    {
        List<Institution> institutions = new ArrayList<>();
        try
        {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from INSTITUTION");
            while(resultSet.next())
            {
                Institution institution = fromResultSet(resultSet);
                institutions.add(institution);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return institutions;
    }
}
