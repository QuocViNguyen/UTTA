package com.example.a3350.data;

import com.example.a3350.objects.Course;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;

import java.util.List;

public interface PostingDataInterface
{
    List<Posting> getPostings();

    void addPosting(User owner, Course course, String title, double price, String detail, boolean highlighted, int howOld);

    void updatePosting(int pID, String newTitle, String newDetail, Course newCourse, boolean newHighlighted, double newPrice, int newHowOld);

    void removePosting(Posting posting);
}
