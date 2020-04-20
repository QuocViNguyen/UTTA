package com.example.a3350.ui;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.a3350.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.StringEndsWith.endsWith;

public class CourseActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private Object RecyclerViewActions;

    public void setUp(){
        Intent intent =  new Intent();
        mLoginActivityActivityTestRule.launchActivity(intent);
    }

    @Before
    public void SettingUpReal(){
        //Login to the account
        onView(withId(R.id.loginButton)).perform(click());
        //Select post button
        onView(withId(R.id.postingsButton)).perform(click());
        //Select All button
        onView(withId(R.id.courseTab)).perform(click());
    }


    @Test
    public void SelectAPostScenario(){
        Espresso.onView(withText(endsWith("courses"))).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onData(anything()).inAdapterView(withId(R.id.postingListView)).atPosition(0).perform(click());

        Espresso.onView(withId(R.id.isHighlightedVIew)).check(matches(isDisplayed()));
    }

}