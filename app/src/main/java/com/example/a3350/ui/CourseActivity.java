package com.example.a3350.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a3350.R;
import com.example.a3350.logic.AccessCourses;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Course;

import java.util.List;

import static com.example.a3350.ui.LoginActivity.currentUser;

public class CourseActivity extends Activity
{
    AccessCourses accessCourses;
    List<Course> courses;
    List<String> faculties;
    ListView listView;
    String show;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Intent intent = getIntent();
        show = intent.getStringExtra(getString(R.string.whatToShowNext));
        accessCourses = new AccessCourses();

        courses = accessCourses.getCourses();
        faculties = accessCourses.getFaculties();
        courses = Filter.getCoursesByInstitution(courses, currentUser.getInstitution());
        faculties = Filter.getFacultiesFromCourses(courses);
        TextView showingTextView = findViewById(R.id.showingTextView);
        showingTextView.setText(show);

        listView = findViewById(R.id.listView);

        //list of either courses or faculty is shown, depending on which is requested by the user
        final ArrayAdapter adapter;
        if(show != null && show.equals(getString(R.string.courses)))
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        else
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculties);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),PostingActivity.class);
                if(show != null && show.equals(getString(R.string.courses)))
                {
                    intent.putExtra("COURSE FACULTY", courses.get(position).getFaculty());
                    intent.putExtra("COURSE ID", courses.get(position).getCourseID());
                }
                else
                {
                    intent.putExtra("FACULTY", faculties.get(position));
                }
                intent.putExtra(getString(R.string.filterBy), show);
                startActivity(intent);
            }
        });
    }
}