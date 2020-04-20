package com.example.a3350.ui;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.a3350.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringEndsWith.endsWith;

public class PostingTabsActivityTest{

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    public void setUp(){
        Intent intent =  new Intent();
        mLoginActivityActivityTestRule.launchActivity(intent);

    }

    @Test
    public void PostingToCourseScenario(){
        //Login to the account
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        //Select post button
        Espresso.onView(withId(R.id.postingsButton)).perform(click());
        //Select Course
        Espresso.onView(withId(R.id.courseTab)).perform(click());
        //Checking
        Espresso.onView(withText(endsWith("courses"))).check(matches(isDisplayed()));
    }

    @Test
    public void PostingToFacultyScenario(){
        //Login to the account
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        //Select post button
        Espresso.onView(withId(R.id.postingsButton)).perform(click());
        //Select Faculty button
        Espresso.onView(withId(R.id.facultyTab)).perform(click());
        //Checking
        Espresso.onView(withText(endsWith("faculties"))).check(matches(isDisplayed()));
    }

    @Test
    public void PostingToAllScenario(){
        //Login to the account
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        //Select post button
        Espresso.onView(withId(R.id.postingsButton)).perform(click());
        //Select All button
        Espresso.onView(withId(R.id.allPostingsTab)).perform(click());
        //Checking
        Espresso.onView(withId(R.id.listingPostingHeading)).check(matches(isEnabled()));


    }

}