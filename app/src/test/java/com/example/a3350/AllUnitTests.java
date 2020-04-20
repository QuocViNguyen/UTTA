package com.example.a3350;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AccessPostingsTest.class,
        AccessCoursesTest.class,
        AccessInstitutionsTest.class,
        AccessUserTest.class,
        FilterTest.class,
        LoginTest.class,
})

public class AllUnitTests {

}
