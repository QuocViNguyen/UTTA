package com.example.a3350.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.a3350.R;
import com.example.a3350.logic.AccessCourses;
import com.example.a3350.logic.AccessPostings;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Posting;

import java.util.ArrayList;
import java.util.List;

import static com.example.a3350.ui.LoginActivity.currentUser;

public class PostActivity extends Activity implements View.OnClickListener
{
    private AccessCourses accessCourses;
    private List<Course> courses;
    private List<String> faculties;
    private AccessPostings accessPostings;
    private EditText titleView, detailView, priceView, ageView;
    private TextView institutionView, ownerView;
    private Spinner courseView, facultyView, isHighlightedView;
    private String title, detail, faculty, course, price, age, highlighted;
    private String posterUsername, posterInstitution, posterEmail;
    private ArrayAdapter<Course> courseArrayAdapter;
    private ArrayAdapter<String> facultyArrayAdapter, highlightedArrayAdapter;
    private Intent intent;
    private Button okayButton;
    private Button buttonBack;
    private int callingActivity, postID;
    private boolean fromUserProfile;
    int ADD_POST, VIEW_POST, EDIT_POST, USER_PROFILE;
    String success_toast = "Post created successfully.";
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ADD_POST = getResources().getInteger(R.integer.ADD_POST);
        VIEW_POST = getResources().getInteger(R.integer.VIEW_POST);
        EDIT_POST = getResources().getInteger(R.integer.EDIT_POST);
        USER_PROFILE = getResources().getInteger(R.integer.USER_PROFILE);

        intent = getIntent();
        callingActivity = intent.getIntExtra(getString(R.string.calling_activity), 0);

        accessCourses = new AccessCourses();
        accessPostings = new AccessPostings();

        //the reason we need a shallow copy is because the courses list changes every time new
        //faculty is selected
        courses = new ArrayList<>(accessCourses.getCourses());

        faculties = accessCourses.getFaculties();
        courses = Filter.getCoursesByInstitution(courses, currentUser.getInstitution());
        faculties = Filter.getFacultiesFromCourses(courses);
        final String[] booleans = {"true", "false"};

        okayButton = findViewById(R.id.okayButton);
        okayButton.setOnClickListener(this);
        titleView = findViewById(R.id.titleView);
        detailView = findViewById(R.id.detailView);
        priceView = findViewById(R.id.priceView);
        ageView = findViewById(R.id.ageView);
        institutionView = findViewById(R.id.institutionView);
        ownerView = findViewById(R.id.ownerVIew);
        ownerView.setOnClickListener(this);
        buttonBack = findViewById(R.id.backButton);
        buttonBack.setOnClickListener(this);

        courseView = findViewById(R.id.courseView);
        facultyView = findViewById(R.id.facultyVIew);
        isHighlightedView = findViewById(R.id.isHighlightedVIew);

        courseArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        facultyArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculties);
        highlightedArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, booleans);

        courseArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highlightedArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courseView.setAdapter(courseArrayAdapter);
        facultyView.setAdapter(facultyArrayAdapter);
        isHighlightedView.setAdapter(highlightedArrayAdapter);

        setActivityValues();
    }

    private void setActivityValues()
    {
        if(callingActivity == VIEW_POST)
        {
            okayButton.setText(R.string.contact_owner);
            loadValues();
            viewPost();
            setValues();
        }
        else if(callingActivity == EDIT_POST || callingActivity == ADD_POST)
        {
            buttonBack.setText(R.string.delete);
            facultyView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                {
                    String faculty = adapterView.getSelectedItem().toString();

                    courses.clear();
                    courses.addAll(Filter.getCoursesByFacAndIn(accessCourses.getCourses(),faculty, currentUser.getInstitution()));
                    courseArrayAdapter.notifyDataSetChanged();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });


            if(callingActivity == EDIT_POST)
            {
                okayButton.setText(R.string.update_post);
                loadValues();
                setValues();
            }
            else
                okayButton.setText(R.string.upload_post);

            institutionView.setText(currentUser.getInstitution().getName());
            ownerView.setText(currentUser.getName());
        }
    }

    private void loadValues()
    {
        title = intent.getStringExtra(getString(R.string.title));
        detail = intent.getStringExtra(getString(R.string.detail));
        price = String.valueOf(intent.getDoubleExtra(getString(R.string.price), 0));
        age = String.valueOf(intent.getIntExtra(getString(R.string.age), 0));
        highlighted = String.valueOf(intent.getBooleanExtra(getString(R.string.highlighted), false));
        course = intent.getStringExtra(getString(R.string.courses));
        faculty = intent.getStringExtra(getString(R.string.faculties));
        posterUsername = intent.getStringExtra(getString(R.string.username));
        posterInstitution = intent.getStringExtra(getString(R.string.institution));
        postID = intent.getIntExtra(getString(R.string.id), -1);
        posterEmail = intent.getStringExtra(getString(R.string.email));
        fromUserProfile = intent.getBooleanExtra(getString(R.string.from), false);
    }

    private void viewPost()
    {
        titleView.setFocusable(false);
        detailView.setFocusable(false);
        priceView.setFocusable(false);
        ageView.setFocusable(false);
        courseView.setEnabled(false);
        facultyView.setEnabled(false);
        isHighlightedView.setEnabled(false);
    }

    private void setValues()
    {
        titleView.setText(title);
        detailView.setText(detail);
        priceView.setText(price);
        ageView.setText(age);
        institutionView.setText(posterInstitution);
        ownerView.setText(posterUsername);
        isHighlightedView.setSelection(highlightedArrayAdapter.getPosition(highlighted));

        Course c = Filter.getCourseByName(courses,course);
        int pos = courseArrayAdapter.getPosition(c);
        courseView.setSelection(pos);
        facultyView.setSelection(facultyArrayAdapter.getPosition(faculty));
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.okayButton:
                onClickOkayButton();
                break;
            case R.id.backButton:
                onClickBackButton();
                break;
            case R.id.ownerVIew:
                if(callingActivity == VIEW_POST && !fromUserProfile)
                    onClickUserName();
                break;
        }
    }

    private void onClickOkayButton()
    {
        //Could not use a switch case because VIEW_POST, ADD_POST and EDIT_POST are not compile time constants
        if (callingActivity == VIEW_POST)
            viewContactInfo();
        else if (callingActivity == ADD_POST)
            addPost();
        else if (callingActivity == EDIT_POST)
            editPost();
    }

    private void onClickBackButton()
    {
        //Could not use a switch case because VIEW_POST, ADD_POST and EDIT_POST are not compile time constants
        if (callingActivity == ADD_POST || callingActivity == VIEW_POST)
            finish();
        else if (callingActivity == EDIT_POST) //delete post
        {
            final Posting posting = Filter.getPostByID(accessPostings.getPostings(),postID);

            AlertDialog.Builder alert = new AlertDialog.Builder(PostActivity.this);
            alert.setTitle("Confirm Delete");
            alert.setMessage("Are you sure you want to delete?");

            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    //remove the post
                    accessPostings.removePosting(posting);
                    Toast.makeText(PostActivity.this, "Post deleted. Please go to homepage and come back to see changes!!", Toast.LENGTH_LONG).show();
                    finish();
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    //don't do anything
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
            Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
        }
    }

    private void onClickUserName()
    {
        if(currentUser.getEmail().equals(posterEmail))
            Toast.makeText(this, "Go to homepage to see your profile.", Toast.LENGTH_SHORT).show();
        else
        {
            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
            intent.putExtra(getString(R.string.calling_activity), USER_PROFILE);
            intent.putExtra(getString(R.string.username), posterUsername);
            intent.putExtra(getString(R.string.email), posterEmail);
            startActivity(intent);
        }
    }

    private void viewContactInfo()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(PostActivity.this);
        alert.setTitle("Contact Information");
        alert.setMessage("Email: " + posterEmail);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //don't do anything
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
    }

    private void addPost()
    {
        String newTitle = titleView.getText().toString();
        String newDetail = detailView.getText().toString();
        String newPrice = priceView.getText().toString();
        String newAge = ageView.getText().toString();
        String courseName, newHighlighted;
        Course course;
        if (newTitle.isEmpty() || newDetail.isEmpty() || newPrice.isEmpty() || newAge.isEmpty())
            Toast.makeText(PostActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
        else {
            courseName = courseView.getSelectedItem().toString();
            newHighlighted = isHighlightedView.getSelectedItem().toString();
            course = Filter.getCourseByName(accessCourses.getCourses(), courseName);
            accessPostings.addPosting(currentUser, course, newTitle, Double.parseDouble(newPrice), newDetail, Boolean.parseBoolean(newHighlighted), Integer.parseInt(newAge));
            Toast.makeText(PostActivity.this, "Post created successfully.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void editPost()
    {
        String updatedTitle = titleView.getText().toString();
        String updatedDetail = detailView.getText().toString();
        String updatedPrice = priceView.getText().toString();
        String updateAge = ageView.getText().toString();
        String courseName, newHighlighted;
        Course course;
        if (updatedTitle.isEmpty() || updatedDetail.isEmpty() || updatedPrice.isEmpty() || updateAge.isEmpty())
            Toast.makeText(PostActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
        else {
            courseName = courseView.getSelectedItem().toString();
            newHighlighted = isHighlightedView.getSelectedItem().toString();
            course = Filter.getCourseByName(accessCourses.getCourses(), courseName);
            accessPostings.updatePosting(postID, updatedTitle, updatedDetail, course, Boolean.parseBoolean(newHighlighted), Double.parseDouble(updatedPrice), Integer.parseInt(updateAge));
            Toast.makeText(PostActivity.this, "Post updated. Please go to homepage and come back to see changes!!", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
