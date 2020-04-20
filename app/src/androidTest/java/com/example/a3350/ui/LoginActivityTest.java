package com.example.a3350.ui;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.a3350.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;


public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private String nPassword = "123456!@#$%^";
    private String nUserName ="mbluth123456!@#$%^@myumanitoba.ca";
    private String welcome = "Welcome!! Login success";
    private String fail  = "Login failed!!";
    private String institution = "University of Manitoba";

    public void setUp() throws Exception {
        Intent intent =  new Intent();
        mLoginActivityActivityTestRule.launchActivity(intent);

    }

    @Test
    public void testCorrectInputScenario(){

        //Since there is a pre-set account there already, you don't have to input an account
        Espresso.onView(withId(R.id.loginButton)).perform(click());

        //Check if the welcome toast is enabled.
        Espresso.onView(withId(R.id.welcome)).check(matches(isEnabled()));
    }

    @Test
    public void TestWrongUserNameInputScenario(){
        Espresso.onView(withId(R.id.usernameEditText)).perform(clearText(),closeSoftKeyboard());
        Espresso.onView(withId(R.id.passwordEditText)).perform(clearText(),closeSoftKeyboard());

        //input non-existing account and try to login
        Espresso.onView(withId(R.id.usernameEditText))
                .perform(typeText(nUserName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.passwordEditText))
                .perform(typeText(nPassword), closeSoftKeyboard());

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        Espresso.onView(withId(R.id.registerButton)).check(matches(isEnabled()));
    }

    @Test
    public void TestEmptyInputScenario(){
        Espresso.onView(withId(R.id.usernameEditText)).perform(clearText(),closeSoftKeyboard());
        Espresso.onView(withId(R.id.passwordEditText)).perform(clearText(),closeSoftKeyboard());

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        Espresso.onView(withId(R.id.registerButton)).check(matches(isEnabled()));
    }

    @Test
    public void TestEmptyUserNameScenario(){
        Espresso.onView(withId(R.id.usernameEditText)).perform(clearText(),closeSoftKeyboard());
        Espresso.onView(withId(R.id.passwordEditText)).perform(clearText(),closeSoftKeyboard());

        Espresso.onView(withId(R.id.passwordEditText))
                .perform(typeText("123456"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        Espresso.onView(withId(R.id.registerButton)).check(matches(isEnabled()));
    }

    @Test
    public void TestEmptyPasswordScenario(){
        Espresso.onView(withId(R.id.usernameEditText)).perform(clearText(),closeSoftKeyboard());
        Espresso.onView(withId(R.id.passwordEditText)).perform(clearText(),closeSoftKeyboard());

        //input non-existing account's username and try to login
        Espresso.onView(withId(R.id.usernameEditText))
                .perform(typeText("mbluth@myumanitoba.ca"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        Espresso.onView(withId(R.id.registerButton)).check(matches(isEnabled()));
    }

    @Test
    public void TestCancelButtonScenario(){
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        Espresso.onView(withId(R.id.usernameTextView)).perform(typeText("Hey, i'm about to cancel what i'm intended to do"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.cancelButton)).perform(click());
        Espresso.onView(withId(R.id.registerButton)).check(matches(isEnabled()));

    }


    @Test
    public void TestRegisterScenario(){
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        Espresso.onView(withId(R.id.usernameTextView)).perform(typeText("Luis Nguyen"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.emailTextView)).perform(typeText("luisnguyen"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.passwordTextView)).perform(typeText("Luis123456"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.confirmPassTextView)).perform(typeText("Luis123456"),closeSoftKeyboard());

        Espresso.onView(withId(R.id.institutionSpinner)).perform(click());


        Espresso.onData(anything()).atPosition(3).perform(click(),closeSoftKeyboard());

        Espresso.onView(withId(R.id.accountButton)).perform(click());
        Espresso.onView(withId(R.id.welcome)).noActivity();
    }
}