package com.example.a3350.objects;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private String name;
    private Institution institution;
    private String email;
    private String password;
    private List<String> ratedBy;
    private List<Float> usersRatings; //parallel lists to see which user has rated what for this user

    public User(String name, Institution institution, String email,String Password) {
        this.name = name;
        this.institution = institution;
        this.email = email;
        this.password = Password;
        ratedBy = new ArrayList<>();
        usersRatings = new ArrayList<>();
    }

    public List<Float> getUsersRatings()
    {
        return usersRatings;
    }

    public String getName()
    {
        return name;
    }

    public Institution getInstitution()
    {
        return institution;
    }

    public String getEmail()
    {
        return email;
    }

    public List<String> getRatedBy()
    {
        return ratedBy;
    }

    public void setInstitution(Institution institution)
    {
        this.institution = institution;
    }

    public String getPassword()
    {
        return password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String newPassword)
    {
        this.password = newPassword;
    }

    @NonNull
    @Override
    public String toString() {
        return name+ " - "+ email;
    }
}
