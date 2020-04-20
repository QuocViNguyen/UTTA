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
import com.example.a3350.logic.AccessPostings;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Posting;

import java.util.List;

import static com.example.a3350.ui.LoginActivity.currentUser;

public class PostingActivity extends Activity
{
    AccessPostings accessPostings;
    ListView postingListView;
    List<Posting> postings;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);
        accessPostings = new AccessPostings();
        postings = accessPostings.getPostings();
        postings = Filter.getPostingsByUserInstitution(postings,currentUser.getInstitution());

        final Intent intent = getIntent();
        final String courseName = intent.getStringExtra("COURSE FACULTY");
        final int courseID = intent.getIntExtra("COURSE ID", 0);

        final String faculty = intent.getStringExtra("FACULTY");
        final String filterBy = intent.getStringExtra(getString(R.string.filterBy));

        TextView filteredByTextView = findViewById(R.id.filteredByTextView);

        //postings will be shown according to which button called this activity
        if(filterBy != null && filterBy.equals(getString(R.string.courses)))
        {
            postings = Filter.byCourses(postings,courseName, courseID);
            String course = faculty + courseID;
            filteredByTextView.setText(course);
        }
        else if (filterBy != null && filterBy.equals(getString(R.string.faculties)))
        {
            postings = Filter.byFaculty(postings, faculty);
            filteredByTextView.setText(faculty);
        }

        postingListView = findViewById(R.id.postingListView);

        final ArrayAdapter<Posting> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postings);
        postingListView.setAdapter(adapter);

        postingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                Bundle extras = new Bundle();
                int VIEW_POST = getResources().getInteger(R.integer.VIEW_POST);
                extras.putInt(getString(R.string.calling_activity), VIEW_POST);

                intent = new Intent(getApplicationContext(), PostActivity.class);

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
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}