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
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

public class UserProfileActivityTest {

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
        onView(withId(R.id.myProfileButton)).perform(click());
    }

    @Test
    public void InMyProfileScenario(){
        Espresso.onView(withId(R.id.nameUserProfile)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.emailTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void SelectCourseFromProfileScenario(){
        Espresso.onView(withId(R.id.nameUserProfile)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.usersPostListView)).atPosition(0).perform(click());
        Espresso.onView(withId(R.id.okayButton)).check(matches(isEnabled()));
    }

    @Test
    public void UpdateUserPostScenario(){
        Espresso.onView(withId(R.id.nameUserProfile)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.usersPostListView)).atPosition(0).perform(click());

        Espresso.onView(withId(R.id.ageView)).perform(clearText(),closeSoftKeyboard());
        Espresso.onView(withId(R.id.ageView)).perform(typeText("1691"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.okayButton)).perform(click());

        Espresso.onView(withId(R.id.nameUserProfile)).check(matches(isDisplayed()));
        Espresso.onView(isRoot()).perform(pressBack());
        onView(withId(R.id.myProfileButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.usersPostListView)).atPosition(0).perform(click());

        Espresso.onView(withId(R.id.ageView)).check(matches(withText("1691")));
    }




}