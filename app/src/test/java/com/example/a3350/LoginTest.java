package com.example.a3350;

import android.content.res.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.a3350.data.AllData;
import com.example.a3350.logic.AccessUsers;
import com.example.a3350.logic.LoginChecker;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;

import static org.junit.Assert.*;


public class LoginTest {
    private AccessUsers accessUsers;

    Institution inst = new Institution("RSU", "realschool.ca");
    User testUser = new User("X Ample", inst, "x@realschool.ca", "123456xA");

    @Test
    public void invalidUserCheckTest(){
        assertFalse(LoginChecker.check("", "111111"));
    }


    //Registration Tests
    @Test
    public void emptyFieldRegTest(){
        assertEquals("Please enter all the fields", LoginChecker.registerCheck("", "x@umanitoba.ca", "123456", "123456"));
    }

    @Test
    public void diffPasswordsRegTest(){
        assertEquals("Passwords don't match", LoginChecker.registerCheck("X Ample", "x@umanitoba.ca", "123456", "1234567"));
    }

    @Test
    public void shortPassRegTest(){
        assertEquals("Passwords should be of minimum length of 8 characters", LoginChecker.registerCheck("X Ample", "x@umanitoba.ca", "1234567", "1234567"));
    }

    @Test
    public void insecurePassRegTest(){
        assertEquals("Password should have one upper case, lower case and a number.", LoginChecker.registerCheck("X Ample", "x@umanitoba.ca", "1234567X", "1234567X"));
    }


    //Account Editing Tests
    @Test
    public void emptyFieldEditTest(){
        assertEquals("Please enter all the fields", LoginChecker.editAccountCheck("", testUser.getEmail(), "x@umanitoba.ca",  "123456", "123456"));
    }

    @Test
    public void diffPasswordsEditTest(){
        assertEquals("Passwords don't match", LoginChecker.editAccountCheck(testUser.getName(), testUser.getEmail(), "x@umanitoba.ca",  "123456", "1234567"));
    }

    @Test
    public void shortPassEditTest(){
        assertEquals("Passwords should be of minimum length of 8 characters", LoginChecker.editAccountCheck("Lucille", testUser.getEmail(), "x@umanitoba.ca",  "1234567", "1234567"));
    }

    @Test
    public void insecurePassEditTest(){
        assertEquals("Password should have one upper case, lower case and a number.", LoginChecker.editAccountCheck("Lucille", testUser.getEmail(), "x@umanitoba.ca",  "1234567l", "1234567l"));
    }


}
