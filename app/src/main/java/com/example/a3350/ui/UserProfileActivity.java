package com.example.a3350.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.a3350.R;
import com.example.a3350.logic.AccessPostings;
import com.example.a3350.logic.AccessUsers;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;

import java.util.ArrayList;
import java.util.List;

import static com.example.a3350.ui.LoginActivity.currentUser;

public class UserProfileActivity extends Activity
{
    RatingBar usersRatingBar, newUsersRating;
    TextView ratedByTextView, nameUserProfile, emailUserProfile;
    TextView userRatingTextView, usersPosts, rateUserTextView;
    Button submitRatingButton;
    User poster;
    String posterName, posterEmail, firstName;
    ListView usersPostListView;
    AccessPostings accessPostings;
    AccessUsers accessUsers;
    List<Posting> postings;
    List<User> users;
    int MY_PROFILE, VIEW_POST, EDIT_POST, USER_PROFILE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        VIEW_POST = getResources().getInteger(R.integer.VIEW_POST);
        EDIT_POST = getResources().getInteger(R.integer.EDIT_POST);
        USER_PROFILE = getResources().getInteger(R.integer.USER_PROFILE);
        MY_PROFILE = getResources().getInteger(R.integer.MY_PROFILE);

        accessPostings = new AccessPostings();
        accessUsers = new AccessUsers();
        //a shallow copy because we don't want to change the original list
        postings = new ArrayList<>(accessPostings.getPostings());
        users = accessUsers.getUsers();
        Intent intent = getIntent();

        nameUserProfile = findViewById(R.id.nameUserProfile);
        emailUserProfile = findViewById(R.id.emailUserProfile);
        ratedByTextView = findViewById(R.id.ratedByTextView);
        submitRatingButton = findViewById(R.id.submitRatingButton);
        usersPostListView = findViewById(R.id.usersPostListView);
        usersRatingBar = findViewById(R.id.usersRatingBar);
        newUsersRating = findViewById(R.id.usersNewRating);
        userRatingTextView = findViewById(R.id.userRatingTextView);
        usersPosts = findViewById(R.id.usersPosts);
        rateUserTextView = findViewById(R.id.rateUserTextView);

        final int callingActivity = intent.getIntExtra(getString(R.string.calling_activity),0);
        if(callingActivity == USER_PROFILE)
        {
            posterName = intent.getStringExtra(getString(R.string.username));
            posterEmail = intent.getStringExtra(getString(R.string.email));
            poster = Filter.getUserByEmail(posterEmail);
        }
        //poster and current user are the same if MY PROFILE button is clicked
        else if(callingActivity == MY_PROFILE)
        {
            posterName = currentUser.getName();
            posterEmail = currentUser.getEmail();
            poster = currentUser;
            submitRatingButton.setEnabled(false);
        }
        if(posterName.contains(" "))
            firstName = posterName.split(" ")[0];

        postings = Filter.byUserEmail(postings, posterEmail);
        final ArrayAdapter<Posting> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postings);
        usersPostListView.setAdapter(adapter);

        usersPostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent1 = new Intent(getApplicationContext(), PostActivity.class);
                Bundle extras = new Bundle();
                if(callingActivity == USER_PROFILE)
                {
                    extras.putInt(getString(R.string.calling_activity), VIEW_POST);
                    extras.putBoolean(getString(R.string.from), true);
                }
                else
                    extras.putInt(getString(R.string.calling_activity), EDIT_POST);

                extras.putInt(getString(R.string.id), postings.get(i).getId());
                extras.putString(getString(R.string.title), postings.get(i).getTitle());
                extras.putString(getString(R.string.detail), postings.get(i).getDetail());
                extras.putBoolean(getString(R.string.highlighted), postings.get(i).isHighlighted());
                extras.putDouble(getString(R.string.price), postings.get(i).getPrice());
                extras.putString(getString(R.string.courses), postings.get(i).getCourse().toString());
                extras.putString(getString(R.string.faculties), postings.get(i).getCourse().getFaculty());
                extras.putInt(getString(R.string.age), postings.get(i).getHowOld());
                extras.putString(getString(R.string.username),postings.get(i).getOwner().getName());
                extras.putString(getString(R.string.institution),postings.get(i).getOwner().getInstitution().getName());
                extras.putString(getString(R.string.email), postings.get(i).getOwner().getEmail());
                intent1.putExtras(extras);
                startActivity(intent1);
            }
        });


        setValues();
        submitRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                float newRating = newUsersRating.getRating();
                accessUsers.addRating(poster,newRating,currentUser.getEmail());
                setValues();
            }
        });
    }
    private void setValues()
    {
        nameUserProfile.setText(posterName);
        emailUserProfile.setText(posterEmail);
        String userRatingText = firstName + "'s rating:";
        String userPostText = firstName + "'s posts:";
        String rateUserText = "Rate " + firstName + ":";
        userRatingTextView.setText(userRatingText);
        usersPosts.setText(userPostText);
        rateUserTextView.setText(rateUserText);

        usersRatingBar.setRating(accessUsers.getRating(poster));
        float ratingForPoster = accessUsers.getRatingFromUser(poster,currentUser.getEmail());
        newUsersRating.setRating(ratingForPoster);
        ratedByTextView.setText(String.valueOf(accessUsers.getRatedBy(poster)));
    }
}
