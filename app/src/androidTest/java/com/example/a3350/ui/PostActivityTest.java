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
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;


public class PostActivityTest {
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
        onView(withId(R.id.allPostingsTab)).perform(click());
    }

    @Test
    public void SelectContactOwnerScenario(){
        onData(anything()).inAdapterView(withId(R.id.postingListView)).atPosition(0).perform(click());
        Espresso.onView(withId(R.id.okayButton)).perform(click());

        Espresso.onView(withText("Contact Information")).check(matches(isDisplayed()));
    }

    @Test
    public void SelectBackButtonScenario(){
        onData(anything()).inAdapterView(withId(R.id.postingListView)).atPosition(0).perform(click());
        Espresso.onView(withId(R.id.backButton)).perform(click());

        Espresso.onView(withId(R.id.listingPostingHeading)).check(matches(isEnabled()));
    }
}