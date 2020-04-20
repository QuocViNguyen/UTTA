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
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

public class PostingActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private Object RecyclerViewActions;

    public void setUp(){
        Intent intent =  new Intent();
        mLoginActivityActivityTestRule.launchActivity(intent);
    }


    @Before
    public void SettingUpReal(){
        onView(withId(R.id.loginButton)).perform(click());

    }

    @Test
    public void SelectAPostScenario(){
        //Select post button
        onView(withId(R.id.postingsButton)).perform(click());
        //Select All button
        onView(withId(R.id.allPostingsTab)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.postingListView)).atPosition(0).perform(click());
        Espresso.onView(withId(R.id.isHighlightedVIew)).check(matches(isDisplayed()));
    }

    @Test
    public void AddAPostScenario(){
        //Select post button
        onView(withId(R.id.addPostButton)).perform(click());
        Espresso.onView(withId(R.id.isHighlightedVIew)).check(matches(isDisplayed()));
        String title = "Book From Espresso";
        String description = "This book is posted by Espresso";
        String price = "69";
        String howOld = "1691";

        Espresso.onView(withId(R.id.titleView)).perform(typeText(title),closeSoftKeyboard());
        Espresso.onView(withId(R.id.detailView)).perform(typeText(description),closeSoftKeyboard());
        Espresso.onView(withId(R.id.priceView)).perform(typeText(price),closeSoftKeyboard());
        Espresso.onView(withId(R.id.ageView)).perform(typeText(howOld),closeSoftKeyboard());

        //onData(anything()).inAdapterView(withId(R.id.facultyVIew)).atPosition(0).perform(click());

        // Click on the Spinner
        onView(withId(R.id.facultyVIew)).perform(click());
        // Select a country from the list
        onData(allOf(is(instanceOf(String.class)), is("COMP"))).perform(click());
        Espresso.onView(withId(R.id.okayButton)).perform(click());

        onView(withText("Post created successfully.")).
                inRoot(withDecorView(not(is(mLoginActivityActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void DeletePostScenario(){
        String title = "Book From Espresso";
        onView(withId(R.id.myProfileButton)).perform(click());
        Espresso.onView(withId(R.id.nameUserProfile)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.usersPostListView)).check(matches(isEnabled()));

        onData(anything()).inAdapterView(withId(R.id.usersPostListView)).atPosition(4).perform(click());
        Espresso.onView(withId(R.id.ageView)).perform(closeSoftKeyboard());
        Espresso.onView(withId(R.id.backButton)).perform(click());

        onView(withText("Are you sure you want to delete?")).
                inRoot(withDecorView(not(is(mLoginActivityActivityTestRule.getActivity().getWindow().getDecorView())))).perform(click());
    }
}