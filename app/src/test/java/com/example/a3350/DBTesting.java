package com.example.a3350;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBTesting {
    private String dbPath = "data/user/0/com.example.a3350/app_db/SC";
    Connection con = null;
    private Statement stmt = null;
    int insert;


    public void setup() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("ERROR: Test database setup.");
            System.out.println(e.getCause());
        }
        //Create UserTable
        try {
            int UserTable = stmt.executeUpdate("CREATE TABLE USER(" +
                    "UserName VARCHAR(40) NOT NULL, UserInstitution VARCHAR(40) NOT NULL, " +
                    "UserEmail VARCHAR(50) NOT NULL, UserPassword VARCHAR(30) NOT NULL, PRIMARY KEY (UserEmail));");
        } catch (Exception e) {
            System.out.println("ERROR: Initializing test user table");
            System.out.println(e.getCause());
        }

        //CREATE INSTITUTION TABLE
        try {
            int InstitutionTable = stmt.executeUpdate("CREATE TABLE INSTITUTION(" +
                    "Institution VARCHAR(50) NOT NULL UNIQUE, domain VARCHAR(40) NOT NULL, PRIMARY KEY (domain));");
        } catch (Exception e) {
            System.out.println("ERROR: Initializing test institution table");
            System.out.println(e.getCause());
        }

        //CREATE COURSETABLE
        try {
            int CourseTable = stmt.executeUpdate("CREATE TABLE COURSE(" +
                    "Faculty VARCHAR(40) NOT NULL, cID VARCHAR(40) NOT NULL, " +
                    "cName VARCHAR(60) NOT NULL, Institution VARCHAR(50) NOT NULL, PRIMARY KEY (cID)," +
                    " FOREIGN KEY (Institution) REFERENCES InstitutionTable(Institution));");
        } catch (Exception e) {
            System.out.println("ERROR: Initializing test course table");
            System.out.println(e.getCause());
        }

        //CREATE POSTINGTABLE
        try {
            int PostingTable = stmt.executeUpdate("CREATE TABLE POSTING(" +
                    "pID BIGINT NOT NULL, cID VARCHAR(40) NOT NULL, title VARCHAR(100) NOT NULL, " +
                    "price FLOAT NOT NULL, detail NVARCHAR(500) NOT NULL, highlighted BOOLEAN NOT NULL, howOld INT NOT NULL, Owner VARCHAR(50) NOT NULL, " +
                    "PRIMARY KEY (pID)," +
                    " FOREIGN KEY (cID) REFERENCES CourseTable(cID) ON DELETE CASCADE," +
                    " FOREIGN KEY (Owner) REFERENCES UserTable(UserEmail) ON DELETE CASCADE);");
        } catch (Exception e) {
            System.out.println("ERROR: Initializing test posting table");
            System.out.println(e.getCause());
        }


        //Insert pre-define data
        try {
            insert = stmt.executeUpdate("INSERT INTO INSTITUTION VALUES" +
                    " ('University of Manitoba', 'umanitoba.ca')");
            insert = stmt.executeUpdate("INSERT INTO INSTITUTION VALUES" +
                    " ('Red River College', 'rrc.ca')");
            insert = stmt.executeUpdate("INSERT INTO COURSE VALUES" +
                    " ('DDM', '101', 'Literary Comprehension', 'University of Manitoba')");
            insert = stmt.executeUpdate("INSERT INTO COURSE VALUES" +
                    " ('COMP', '1234', 'Algorithms', 'Red River College')");
            insert = stmt.executeUpdate("INSERT INTO USER VALUES" +
                    " ('Quoc', 'umanitoba.ca', 'nguyenvq@myumanitoba.ca', '123456')");
            insert = stmt.executeUpdate("INSERT INTO POSTING VALUES" +
                    " (0, '101' ,'First Book Ever In The Database', 3.99," +
                    "'Well, this is the detail section, so..... welcome',false,4,'nguyenvq@myumanitoba.ca')");
            insert = stmt.executeUpdate("INSERT INTO POSTING VALUES" +
                    " (1, '101', 'Twilight', 17.99," +
                    "'This is a master piece in liturature and also cinema',false,36,'nguyenvq@myumanitoba.ca')");
        } catch (Exception e) {
            System.out.println("ERROR: Inserting default test data");
            System.out.println(e.getCause());
        }
    }

    public void collapseTables() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("ERROR: Test database close.");
            System.out.println(e.getCause());
        }

        //Drop PostingTable
        try {
            int PostingTable = stmt.executeUpdate("DROP TABLE POSTING");
        } catch (Exception e) {
            System.out.println("ERROR: Dropping test posting table");
            System.out.println(e.getCause());
        }

        //Drop CourseTable
        try {
            int CourseTable = stmt.executeUpdate("DROP TABLE COURSE");
        } catch (Exception e) {
            System.out.println("ERROR: Dropping test course table");
            System.out.println(e.getCause());
        }

        //Drop InstitutionTable
        try {
            int InstitutionTable = stmt.executeUpdate("DROP TABLE INSTITUTION");
        } catch (Exception e) {
            System.out.println("ERROR: Dropping test institution table");
            System.out.println(e.getCause());
        }

        //Drop UserTable
        try {
            int UserTable = stmt.executeUpdate("DROP TABLE USER");
        } catch (Exception e) {
            System.out.println("ERROR: Dropping test user table");
            System.out.println(e.getCause());
        }
    }
}
