package com.example.a3350.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a3350.R;

public class PostingTabsActivity extends Activity implements View.OnClickListener{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postingtabs);

        Button courseTab = findViewById(R.id.courseTab);
        Button facultyTab = findViewById(R.id.facultyTab);
        Button allPostingTab = findViewById(R.id.allPostingsTab);

        courseTab.setOnClickListener(this);
        facultyTab.setOnClickListener(this);
        allPostingTab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.courseTab:
                Intent courseIntent = new Intent(getApplicationContext(), CourseActivity.class);
                courseIntent.putExtra(getString(R.string.whatToShowNext), getString(R.string.courses));
                startActivity(courseIntent);
                break;
            case R.id.facultyTab:
                Intent facultyIntent = new Intent(getApplicationContext(), CourseActivity.class);
                facultyIntent.putExtra(getString(R.string.whatToShowNext), getString(R.string.faculties));
                startActivity(facultyIntent);
                break;
            case R.id.allPostingsTab:
                Intent allPostingsIntent = new Intent(getApplicationContext(), PostingActivity.class);
                allPostingsIntent.putExtra(getString(R.string.filterBy), getString(R.string.postings));
                startActivity(allPostingsIntent);
                break;
        }
    }
}
