package com.example.a3350.ui;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.a3350.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class AccountActivityTest{
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    public void setUp(){
        Intent intent =  new Intent();
        mLoginActivityActivityTestRule.launchActivity(intent);
    }

    @Before
    public void SettingUpReal(){
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.editAccountButton)).perform(click());
    }

    @Test
    public void EmptyEditScenario(){
        onView(withId(R.id.emailTextView)).perform(closeSoftKeyboard());
        onView(withId(R.id.accountButton)).perform(click());
        onView(withId(R.id.accountButton)).check(matches(isEnabled()));

    }


}
