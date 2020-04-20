package com.example.a3350;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.a3350.data.database.CourseDatabase;
import com.example.a3350.utils.TestUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CourseDBTest {
    private CourseDatabase courseDB;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.courseDB = new CourseDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
    }


    @Test
    public void getCoursesTest(){
        assertEquals(1000, courseDB.getCourses().get(0).getCourseID());
    }


    @Test
    public void getFacultiesTest(){
        assertEquals("ABIZ", courseDB.getFaculties().get(0));
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
