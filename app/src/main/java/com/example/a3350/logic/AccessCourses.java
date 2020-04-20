package com.example.a3350.logic;

import com.example.a3350.data.AllData;
import com.example.a3350.data.CourseDataInterface;
import com.example.a3350.objects.Course;

import java.util.List;

public class AccessCourses
{
    private CourseDataInterface courseDataInterface;

    public AccessCourses()
    {
        courseDataInterface = AllData.getCourseData();
    }

    public AccessCourses(CourseDataInterface dataInterface) {
        this();
        courseDataInterface = dataInterface;
    }

    public List<Course> getCourses()
    {
        return courseDataInterface.getCourses();
    }

    public List<String> getFaculties()
    {
        return courseDataInterface.getFaculties();
    }
}
