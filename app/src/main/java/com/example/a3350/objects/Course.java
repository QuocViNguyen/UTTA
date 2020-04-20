package com.example.a3350.objects;

public class Course
{
    private String faculty;
    private int courseID;
    private String courseName;
    private Institution institution;

    public Course(String faculty, int courseID, String courseName, Institution institution) {
        this.faculty = faculty;
        this.courseID = courseID;
        this.courseName = courseName;
        this.institution = institution;
    }

    public String getFaculty()
    {
        return faculty;
    }

    public int getCourseID()
    {
        return courseID;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public Institution getInstitution()
    {
        return institution;
    }

    public void setInstitution(Institution institution)
    {
        this.institution = institution;
    }

    @Override
    public String toString()
    {
        return faculty + " " + courseID + " : " + courseName;
    }
}
