package com.example.a3350.logic;

import com.example.a3350.data.AllData;
import com.example.a3350.data.UserDataInterface;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;

import java.util.List;

public class AccessUsers
{
    private UserDataInterface userDataInterface;
    public AccessUsers()
    {
        userDataInterface = AllData.getUserData();
    }

    public AccessUsers(UserDataInterface dataInfo){
        this();
        userDataInterface = dataInfo;
    }

    public List<User> getUsers()
    {
        return userDataInterface.getUsers();
    }

    public void addUser(User user)
    {
        userDataInterface.addUser(user);
    }

    public void updateUser(String oldEmail, String newName, String newEmail, String newPass, Institution newInstitution)
    {
        userDataInterface.updateUser(oldEmail, newName, newEmail, newPass, newInstitution);
    }

    public float getRating(User ofUser)
    {
        return userDataInterface.getRating(ofUser);
    }

    public int getRatedBy(User ofUser)
    {
        return userDataInterface.getRatedBy(ofUser);
    }

    public float getRatingFromUser(User ofUser, String fromUser)
    {
        return userDataInterface.getRatingFromUser(ofUser, fromUser);
    }

    public void addRating(User toUser, float newRating, String fromUser)
    {
        userDataInterface.addRating(toUser,newRating,fromUser);
    }
}
