package com.example.a3350;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        CourseDBTest.class,
        InstitutionDBTest.class,
        PostingDBTest.class,
        UserDBTest.class})

public class AllIntegrationTests {

}
