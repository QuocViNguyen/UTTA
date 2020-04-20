package com.example.a3350;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.example.a3350.data.UserDataInterface;
import com.example.a3350.logic.AccessCourses;
import com.example.a3350.logic.AccessPostings;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;
import com.example.a3350.objects.Institution;

import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

//(list of postings, string name, int id)

public class FilterTest {
    private List<Posting> testList;

    private Institution institution1 = new Institution("UofU", "@UU.ca");
    private Institution institution2 = new Institution("UofM", "@UM.ca");

    private Course course1 = new Course("COMP", 101, "5th Year Binary", institution1);
    private Course course2 = new Course("COMP", 1020, "Intro Comp Sci", institution1);
    private Course course3 = new Course("PHIL", 1000, "What is Philosophy?", institution1);
    private Course course4 = new Course("PHIL", 4000, "Why is Philosophy?", institution1);
    private Course course5 = new Course("PHIL", 4000, "Why is Philosophy?", institution2);
    private List<Course> courseList;


    private User owner1 = new User("John Doe", institution1, "John@UU.ca", "Password");
    private User owner2 = new User("Not Doe", institution1, "Not@UU.ca", "Password");
    private User owner3 = new User("Not Doe", institution2, "Not@UU.ca", "Password");

    private Posting posting1 = new Posting(owner1, course1, "book1", 100.10, "Good book", true, 5);
    private Posting posting2 = new Posting(owner1, course2, "book2", 230.10, "Bad book", true, 8);
    private Posting posting3 = new Posting(owner1, course3, "book2", 50.40, "Bad book", true, 6);
    private Posting posting4 = new Posting(owner1, course4, "book3", 100.20, "Lame book", true, 7);
    private Posting posting5 = new Posting(owner1, course2, "book4", 0.10, "Rad book", true, 5);
    private Posting posting6 = new Posting(owner2, course2, "book5", 0.10, "Fad book", true, 5);
    private Posting posting7 = new Posting(owner3, course5, "book5", 0.10, "Fad book", true, 5);

    private List<Posting> course2Filtered = Arrays.asList(posting2, posting5,posting6);
    private List<Posting> COMPFiltered = Arrays.asList(posting1, posting2, posting5,posting6);
    private List<Posting> priceSorted = Arrays.asList(posting5,posting6, posting3, posting1, posting4, posting2);
    private List<Posting> UserSorted = Arrays.asList(posting1, posting2, posting3, posting4, posting5);

    @Before
    public void setup()
    {
        testList = Arrays.asList(posting1, posting2, posting3, posting4, posting5, posting6);
        courseList = Arrays.asList(course1,course2,course3,course4);

    }

    @Test
    public void CourseFilterTest() {
        assertThat(Filter.byCourses(testList, course2.getFaculty(), course2.getCourseID()), is(course2Filtered));
    }

    @Test
    public void PriceSortTest(){
        Filter.sortByPrices(testList);
        assertThat(testList, is(priceSorted));
    }

    @Test
    public void FacultyFilterTest(){
        assertThat(Filter.byFaculty(testList, course1.getFaculty()), is(COMPFiltered));
    }

    @Test
    public void UserNameTesting(){
        assertThat(Filter.byUserEmail(testList,owner1.getEmail()), is(UserSorted));
    }


    @Test
    public void getCoursesByFacultyTest(){
        List<Course> compCourseTestList = Filter.getCoursesByFacAndIn(courseList,"COMP", institution1);
        List<Course> expectedCourse = Arrays.asList(course1,course2);
        assertThat("The same list",compCourseTestList, is(expectedCourse));
    }

    @Test
    public void getCourseByNameTest(){
        List<Course> courseTestList = Arrays.asList(course1,course2,course3);
        Course test = Filter.getCourseByName(courseTestList,courseTestList.get(1).toString());
        assertThat("the test result in", test, is(course2));
    }

    @Test
    public void getPostByIDTest(){
        Posting testPost = Filter.getPostByID(testList,posting1.getId());
        assertThat("Should be posting1",testPost, is(posting1));
    }

    @Test
    public void getPostingByUserInstitutionTest(){
        List<Posting> postList = Arrays.asList(posting6,posting7,posting1);
        Institution testInt = posting7.getOwner().getInstitution();
        List<Posting> testPost = Filter.getPostingsByUserInstitution(postList,testInt);
        List<Posting> expectedList = Arrays.asList(posting7);
        assertThat("should be the same", testPost, is(expectedList));
    }


    @Test
    public void getCourseByInstitutionTest(){
        List<Course> courseList2 = Arrays.asList(course1,course2,course3,course4,course5);
        List<Course> courseTest = Filter.getCoursesByInstitution(courseList2,institution2);
        List<Course> expectedList = Arrays.asList(course5);
        assertThat("should be the same list",courseTest,is(expectedList));
    }

    @Test
    public void getFacultiesFromCoursesTest(){
        List<String> testFac = Filter.getFacultiesFromCourses(courseList);
        List<String> expected = Arrays.asList("COMP","PHIL");
        assertThat("The test matches exepected",testFac,is(expected));
    }


    @Test
    public void getIndexOfInstitutionTest(){
        List<Institution> instTest = Arrays.asList(institution1,institution2);
        int testIndex = Filter.getIndexOfInstitution(instTest,institution1);
        assertThat("the expected is the tested",testIndex,is(0));
    }




}
