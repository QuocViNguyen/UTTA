package com.example.a3350;

import androidx.annotation.VisibleForTesting;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.a3350.data.PostingDataInterface;
import com.example.a3350.logic.AccessPostings;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;

import static org.junit.Assert.*;

public class AccessPostingsTest {

    private PostingDataInterface dataInterface;
    private AccessPostings postTester;
    private AccessPostings postTester1;
    private Institution institution1 = new Institution("UofU", "@UU.ca");
    private Course course1 = new Course("PHIL", 1000, "What is Philosophy?", institution1);
    private Course course2 = new Course("PHIL", 2000, "That was not Philosophy?", institution1);
    private User owner1 = new User("John Doe", institution1, "John@UU.ca", "Password");
    private Posting posting1 = new Posting(owner1, course1, "book1", 100.10, "Good book", true, 5);


    @Before
    public void setup() {
        dataInterface = mock(PostingDataInterface.class);
        postTester = new AccessPostings(dataInterface);
    }

    @Test
    public void accessPostTest(){
        List<Posting> postList = Arrays.asList(posting1);
        when(dataInterface.getPostings()).thenReturn(postList);
        postTester1 = new AccessPostings(dataInterface);
        List<Posting> postListCheck = postTester1.getPostings();
        assertEquals("These should be equal", postListCheck,postList);
    }

    @Test
    public void addPostTest(){
        postTester.addPosting(posting1.getOwner(),posting1.getCourse(), posting1.getTitle(),posting1.getPrice(), posting1.getDetail(), posting1.isHighlighted(),posting1.getHowOld());
        verify(dataInterface).addPosting(posting1.getOwner(),posting1.getCourse(), posting1.getTitle(),posting1.getPrice(), posting1.getDetail(), posting1.isHighlighted(),posting1.getHowOld());
        postTester.removePosting(posting1);
    }

    @Test
    public void removePostTest(){
        postTester.addPosting(posting1.getOwner(),posting1.getCourse(), posting1.getTitle(),posting1.getPrice(), posting1.getDetail(), posting1.isHighlighted(),posting1.getHowOld());
        postTester.removePosting(posting1);
        verify(dataInterface).removePosting(posting1);
    }

    @Test
    public void updatePostTest(){
        postTester.addPosting(posting1.getOwner(),posting1.getCourse(), posting1.getTitle(),posting1.getPrice(), posting1.getDetail(), posting1.isHighlighted(),posting1.getHowOld());
        int id = posting1.getId();
        String newTitle = "new title";
        String newDetail = "better book";
        Course newCourse = course2;
        boolean high = false;
        double money = 0.12;
        int age = 2;
        postTester.updatePosting(id,newTitle,newDetail, newCourse, high, money,age );
        verify(dataInterface).updatePosting(id,newTitle,newDetail, newCourse, high, money,age );
    }
}