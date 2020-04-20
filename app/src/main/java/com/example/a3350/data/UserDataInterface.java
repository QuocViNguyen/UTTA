package com.example.a3350.data;

import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;

import java.util.List;

public interface UserDataInterface
{
    List<User> getUsers();

    void addUser(User user);

    void updateUser(String oldEmail, String newName, String newEmail, String newPass, Institution newInstitution);

    float getRating(User ofUser);

    int getRatedBy(User ofUser);

    float getRatingFromUser(User ofUser, String fromUser);

    void addRating(User toUser, float newRating, String fromUser);
}
