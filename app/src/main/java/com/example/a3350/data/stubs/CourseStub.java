package com.example.a3350.data.stubs;

import com.example.a3350.data.AllData;

import com.example.a3350.data.CourseDataInterface;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Institution;

import java.util.ArrayList;
import java.util.List;

public class CourseStub implements CourseDataInterface
{

    private List<Course> courses;
    private List<String> faculties;

    public CourseStub()
    {
        this.courses = new ArrayList<>();
        this.faculties = new ArrayList<>();

        String comp = "COMP";
        String eng = "ENG";

        Institution uOfm = AllData.getInstitutionData().getInstitutions().get(0);

        courses.add(new Course(comp,3030, "Automata", uOfm));
        courses.add(new Course(comp,3350, "Software Engineering 1", uOfm));
        courses.add(new Course(comp,3490, "Computer Graphics", uOfm));
        courses.add(new Course(comp,3380, "Database Concepts", uOfm));
        courses.add(new Course(eng,3130, "Something", uOfm));
        courses.add(new Course(comp, 3170, "Analysis of Algorithms and Data Structures", uOfm));

        faculties.add(comp);
        faculties.add(eng);
    }

    @Override
    public List<Course> getCourses()
    {
        return courses;
    }

    @Override
    public List<String> getFaculties() {
        return faculties;
    }

}
