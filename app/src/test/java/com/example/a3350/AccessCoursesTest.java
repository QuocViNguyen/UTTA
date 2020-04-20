package com.example.a3350;


import com.example.a3350.data.CourseDataInterface;
import com.example.a3350.logic.AccessCourses;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Institution;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccessCoursesTest {
    private CourseDataInterface dataInterface;
    private AccessCourses accessCourses;
    private Course course;

    private Institution institution1 = new Institution("UofU", "@UU.ca");
    private Course course1 = new Course("COMP", 101, "5th Year Binary", institution1);
    private Course course2 = new Course("COMP", 1020, "Intro Comp Sci", institution1);

    @Before
    public void setup(){
        dataInterface = mock(CourseDataInterface.class);
    }

    @Test
    public void getCoursesTest(){
        List<Course> coursesTest = Arrays.asList(course1,course2);
        when(dataInterface.getCourses()).thenReturn(coursesTest);
        accessCourses = new AccessCourses(dataInterface);
        List<Course> checkTest = accessCourses.getCourses();
        assertEquals("the two list should be equal",coursesTest,checkTest);
    }

    @Test
    public void getFacultiesTest(){
        List<String> facultyTest = Arrays.asList("Comp");
        when(dataInterface.getFaculties()).thenReturn(facultyTest);
        accessCourses = new AccessCourses(dataInterface);
        List<String> checkTest = accessCourses.getFaculties();
        assertEquals("the two list should be equal",checkTest,facultyTest);
    }

}
