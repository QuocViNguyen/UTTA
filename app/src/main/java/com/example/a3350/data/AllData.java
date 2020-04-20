package com.example.a3350.data;

import com.example.a3350.application.Main;
import com.example.a3350.data.database.CourseDatabase;
import com.example.a3350.data.database.InstitutionDatabase;
import com.example.a3350.data.database.PostingDatabase;
import com.example.a3350.data.database.UserDatabase;
import com.example.a3350.data.stubs.CourseStub;
import com.example.a3350.data.stubs.InstitutionStub;
import com.example.a3350.data.stubs.PostingStub;
import com.example.a3350.data.stubs.UserStub;
import com.example.a3350.logic.Filter;

public class AllData
{
    private static PostingDataInterface postingData;
    private static CourseDataInterface courseData;
    private static UserDataInterface userData;
    private static InstitutionDataInterface institutionData;

    public static PostingDataInterface getPostingData()
    {
        if(postingData == null)
        {
            if(Filter.usingDatabase)
                postingData = new PostingDatabase(Main.getDBPathName());
            else
                postingData = new PostingStub();
        }
        return postingData;
    }

    public static CourseDataInterface getCourseData()
    {
        if(courseData == null)
        {
            if(Filter.usingDatabase)
                courseData = new CourseDatabase(Main.getDBPathName());
            else
                courseData = new CourseStub();
        }
        return courseData;
    }

    public static UserDataInterface getUserData()
    {
        if(userData == null)
        {
            if(Filter.usingDatabase)
                userData = new UserDatabase(Main.getDBPathName());
            else
                userData = new UserStub();
        }
        return userData;
    }

    public static InstitutionDataInterface getInstitutionData()
    {
        if(institutionData == null)
        {
            if(Filter.usingDatabase)
                institutionData = new InstitutionDatabase(Main.getDBPathName());
            else
                institutionData = new InstitutionStub();
        }
        return institutionData;
    }
}
