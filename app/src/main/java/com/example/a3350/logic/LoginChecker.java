package com.example.a3350.logic;

import com.example.a3350.objects.User;

import java.util.List;
import java.util.regex.Pattern;


public class LoginChecker {

    private static final Pattern validPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
    public static boolean check(String userEmail, String password)
    {
        List<User> users =new AccessUsers().getUsers();
        for (User user : users) {
            if (user.getEmail().equals(userEmail) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public static String registerCheck(String username, String email, String pw1, String pw2)
    {
        String result = "";
        if(username.isEmpty() || email.isEmpty() || pw1.isEmpty() || pw2.isEmpty())
            result = "Please enter all the fields";
        else if(!pw1.equals(pw2))
            result = "Passwords don't match";
        else if(pw1.length() < 8)
            result = "Passwords should be of minimum length of 8 characters";
        else if(!validPassword.matcher(pw1).matches())
            result = "Password should have one upper case, lower case and a number.";
        else if(emailAlreadyExists(email))
            result = "Email is already registered";
        else
            result = "Account created successfully.";
        return result;
    }

    public static String editAccountCheck(String username, String userEmail, String newEmail, String newPw1, String newPw2)
    {
        String result = "";
        if(username.isEmpty() || newEmail.isEmpty() || newPw1.isEmpty() || newPw2.isEmpty())
            result = "Please enter all the fields";
        else if(!newPw1.equals(newPw2))
            result = "Passwords don't match";
        else if(newPw1.length() < 8)
            result = "Passwords should be of minimum length of 8 characters";
        else if(!validPassword.matcher(newPw1).matches())
            result = "Password should have one upper case, lower case and a number.";
        else if(!userEmail.equals(newEmail) && emailAlreadyExists(newEmail))
            result = "Email already exists";
        else
            result = "Account created successfully.";
        return  result;
    }

    private static boolean emailAlreadyExists(String email)
    {
        List<User> users = new AccessUsers().getUsers();
        for (User user : users)
        {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }
}
