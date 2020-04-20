package com.example.a3350.data.stubs;

import com.example.a3350.data.AllData;
import com.example.a3350.data.UserDataInterface;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;

import java.util.ArrayList;
import java.util.List;

public class UserStub implements UserDataInterface
{
    private List<User> users;

    public UserStub() {
        this.users = new ArrayList<>();

        //We will need to automate creating Institution and adding domains to the list.
        Institution uOfm = AllData.getInstitutionData().getInstitutions().get(0);
        users.add(new User("Lucille", uOfm, "lucilleb@umanitoba.ca","123456"));
        users.add(new User("Michael", uOfm, "mbluth@umanitoba.ca","123456"));
        users.add(new User("Lindsay", uOfm, "lindsab@umanitoba.ca","123456"));
        users.add(new User("Gene Parmesan", uOfm, "parmesan@umanitoba.ca","123456"));
    }
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(User user)
    {
        if(!users.contains(user))
            users.add(user);
    }

    @Override
    public float getRating(User ofUser)
    {
        float sum = 0;
        for (float rating : ofUser.getUsersRatings())
            sum += rating;

        return sum/ofUser.getUsersRatings().size();
    }

    @Override
    public int getRatedBy(User ofUser)
    {
        return ofUser.getRatedBy().size();
    }

    @Override
    public float getRatingFromUser(User ofUser, String fromUser)
    {
        int index = ofUser.getRatedBy().indexOf(fromUser);
        if(index != -1)
            return ofUser.getUsersRatings().get(index);
        else
            return 0;
    }

    @Override
    public void addRating(User toUser, float newRating, String fromUser)
    {
        if(!toUser.getRatedBy().contains(fromUser))
        {
            toUser.getRatedBy().add(fromUser);
            toUser.getUsersRatings().add(newRating);
        }
        else
        {
            int index = toUser.getRatedBy().indexOf(fromUser);
            toUser.getUsersRatings().set(index, newRating);
        }
    }

    @Override
    public void updateUser(String oldEmail, String newName, String newEmail, String newPass, Institution newInstitution)
    {
        User user = Filter.getUserByEmail(oldEmail);
        user.setName(newName);
        user.setEmail(newEmail);
        user.setPassword(newPass);
        user.setInstitution(newInstitution);
    }
}
