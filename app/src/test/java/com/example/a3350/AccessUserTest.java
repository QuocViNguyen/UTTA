package com.example.a3350;

import androidx.core.widget.TextViewCompat;

import com.example.a3350.data.UserDataInterface;
import com.example.a3350.logic.AccessUsers;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AccessUserTest {
    private AccessUsers userAcc;
    private AccessUsers userAcc1;
    private UserDataInterface dataInt;
    private Institution institution1 = new Institution("UofU", "@UU.ca");
    private User user = new User("John Doe", institution1, "John@UU.ca", "Password");
    private User user1 = new User("Not Doe", institution1, "Not@UU.ca", "Password");

    @Before
    public void setup(){
        dataInt = mock(UserDataInterface.class);
        userAcc = new AccessUsers(dataInt);
    }

    @Test
    public void getUsersTest(){
        List<User> userTest = Arrays.asList(user);
        when(dataInt.getUsers()).thenReturn(userTest);
        userAcc1 = new AccessUsers(dataInt);
        List<User> checkTest = userAcc1.getUsers();
        assertEquals("the two list should be equal",checkTest,userTest);
    }

    @Test
    public void addUsersTest(){
        userAcc.addUser(user1);
        verify(dataInt).addUser(user1);
    }

    @Test
    public void updateUserTest(){
        userAcc.addUser(user);
        userAcc.updateUser(user.getEmail(),"new name","Not@UU.ca","newPass",user.getInstitution());
        verify(dataInt).updateUser(user.getEmail(),"new name","Not@UU.ca","newPass",user.getInstitution());
    }

    @Test
    public void getRatingTest(){
        userAcc.addUser(user1);
        Float mockRate = 1.f;
        when(dataInt.getRating(user1)).thenReturn(mockRate);
        Float rating = userAcc.getRating(user1);
        assertEquals("should be the same",rating,mockRate);
    }

    @Test
    public void getRatedByTest(){
        userAcc.addUser(user1);
        int mockRate = 1;
        when(dataInt.getRatedBy(user1)).thenReturn(mockRate);
        int rating = userAcc.getRatedBy(user1);
        assertEquals("should be the same",rating,mockRate);
    }

    @Test
    public void getRatingFromTest(){
        userAcc.addUser(user1);
        Float mockRate = 1.f;
        when(dataInt.getRatingFromUser(user1,user.getName())).thenReturn(mockRate);
        Float rating = userAcc.getRatingFromUser(user1,user.getName());
        assertEquals("should be the same",rating,mockRate);
    }

    @Test
    public void addRatingTest(){
        userAcc.addRating(user1,1.f,user.getName());
        verify(dataInt).addRating(user1,1.f,user.getName());
    }
}
