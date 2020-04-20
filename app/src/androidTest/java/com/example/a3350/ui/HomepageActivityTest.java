package com.example.a3350.ui;


import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.a3350.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HomepageActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    public void setUp(){
        Intent intent =  new Intent();
        mLoginActivityActivityTestRule.launchActivity(intent);

    }

    @Test
    public void PostingAccessScenario(){
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Espresso.onView(withId(R.id.postingsButton)).perform(click());
        Espresso.onView(withId(R.id.allPostingsTab)).check(matches(isEnabled()));
    }

    @Test
    public void AddPostAccessScenario(){
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Espresso.onView(withId(R.id.addPostButton)).perform(click());
        Espresso.onView(withId(R.id.isHighlightedVIew)).check(matches(isEnabled()));
    }

    @Test
    public void EditAccountAccessScenario(){
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Espresso.onView(withId(R.id.editAccountButton)).perform(click());
        Espresso.onView(withId(R.id.confirmPassTextView)).check(matches(isEnabled()));
    }

    @Test
    public void MyProfileAccessScenario(){
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Espresso.onView(withId(R.id.myProfileButton)).perform(click());
        Espresso.onView(withId(R.id.nameTextView)).check(matches(isEnabled()));
    }


    @Test
    public void LogOutButtonScenario(){
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Espresso.onView(withId(R.id.logoutButton)).perform(click());
        Espresso.onView(withId(R.id.loginButton)).check(matches(isEnabled()));
    }
}