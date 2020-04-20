package com.example.a3350.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main
{
    private static String dbName="SC";

    public static void main(String[] args)
    {
    }

    public static void setDBPathName(final String name)
    {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        dbName = name;
        workaround();
    }

    public static String getDBPathName() {
        return dbName;
    }

    /*
    * This was the only way we could make it work
    * Not the best way and there are definitely potential errors
    * but given the amount of time left, we can't find any other
    * solutions. */
    private static void workaround()
    {
        Connection connection;
        Statement statement = null;
        boolean connectionEstablished = false;
        try
        {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:"+dbName+";shutdown=true", "SA", "");;
            statement = connection.createStatement();
            connectionEstablished = true;
            ResultSet resultSet = statement.executeQuery("Select * from USER");
            resultSet.close();
        }
        catch (Exception e)
        {
            if(connectionEstablished)
                new Database(statement);
            else
                e.printStackTrace();
        }
    }
}
