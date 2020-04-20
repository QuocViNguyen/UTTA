package com.example.a3350;

import androidx.core.widget.TextViewCompat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.a3350.data.database.UserDatabase;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;
import com.example.a3350.utils.TestUtils;


import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDBTest {
    private UserDatabase userDB;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.userDB = new UserDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
    }


    @Test
    public void addUserTest(){
        final Institution uOfm = new Institution("University of Manitoba", "umanitoba.ca");

        final User user = new User("Sam", uOfm, "samp@umanitoba.ca", "123456");
        userDB.addUser(user);
        assertEquals("Sam", userDB.getUsers().get(11).getName());
    }

    @Test
    public void updateUserTest(){
        final Institution uOfm = new Institution("University of Manitoba", "umanitoba.ca");

        assertEquals("Irene Adler", userDB.getUsers().get(0).getName());
        userDB.updateUser("adlerire@oxoxox.uk", "Irene Neunam", "adlerire@oxoxox.uk", "123456", uOfm);
        assertEquals("Irene Neunam", userDB.getUsers().get(0).getName());
    }

    @Test
    public void getRatingTest(){
        assertEquals(3.00, userDB.getRating(userDB.getUsers().get(0)), 0.01);
    }

    @Test
    public void getRatedByTest(){
        assertEquals(3, userDB.getRatedBy(userDB.getUsers().get(0)));
    }

    @Test
    public void getRatingFromUserTest(){
        assertEquals(1.00, userDB.getRatingFromUser(userDB.getUsers().get(0), "lucilleb@hogwiz.uk"), 0.01);
    }

    @Test
    public void addRatingNewTest(){
        userDB.addRating(userDB.getUsers().get(0), (float)4.13, "mbluth@myumanitoba.ca");
        assertEquals(4.13, userDB.getRatingFromUser(userDB.getUsers().get(0), "mbluth@myumanitoba.ca"), 0.01);
    }

    @Test
    public void addRatingUpdateTest(){
        userDB.addRating(userDB.getUsers().get(0), (float)4.13, "lucilleb@hogwiz.uk");
        assertEquals(4.13, userDB.getRatingFromUser(userDB.getUsers().get(0), "lucilleb@hogwiz.uk"), 0.01);
    }


    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
