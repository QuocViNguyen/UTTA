package com.example.a3350.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a3350.R;

//should we do this?
import static com.example.a3350.ui.LoginActivity.currentUser;

public class HomepageActivity extends Activity implements View.OnClickListener
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        TextView homepageUsernameTV = findViewById(R.id.homepageUsernameTV);
        String firstName = currentUser.getName();
        if(firstName.contains(" "))
            firstName = firstName.split(" ")[0];

        homepageUsernameTV.setText(firstName);

        Button postingsButton = findViewById(R.id.postingsButton);
        Button addPostButton = findViewById(R.id.addPostButton);
        Button editAccountButton = findViewById(R.id.editAccountButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button myPostButton = findViewById(R.id.myProfileButton);

        postingsButton.setOnClickListener(this);
        addPostButton.setOnClickListener(this);
        editAccountButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        myPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.postingsButton:
                Intent postingTabIntent = new Intent(getApplicationContext(), PostingTabsActivity.class);
                startActivity(postingTabIntent);
                break;
            case R.id.myProfileButton:
                Intent myProfileIntent = new Intent(getApplicationContext(), UserProfileActivity.class);
                int MY_PROFILE = getResources().getInteger(R.integer.MY_PROFILE);
                myProfileIntent.putExtra(getString(R.string.calling_activity),MY_PROFILE);
                startActivity(myProfileIntent);
                break;
            case R.id.addPostButton:
                Intent addPostIntent = new Intent(getApplicationContext(), PostActivity.class);
                int ADD_POST = getResources().getInteger(R.integer.ADD_POST);
                addPostIntent.putExtra(getString(R.string.calling_activity), ADD_POST);
                startActivity(addPostIntent);
                break;
            case R.id.editAccountButton:
                Intent editAccountIntent = new Intent(getApplicationContext(), AccountActivity.class);
                editAccountIntent.putExtra(getString(R.string.edit_account), true);
                startActivity(editAccountIntent);
                break;
            case R.id.logoutButton:
                finish();
                break;
        }
    }
}
