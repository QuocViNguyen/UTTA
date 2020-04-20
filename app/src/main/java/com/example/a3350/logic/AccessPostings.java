package com.example.a3350.logic;

import com.example.a3350.data.AllData;
import com.example.a3350.data.PostingDataInterface;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;

import java.util.List;

public class AccessPostings
{
    private PostingDataInterface postingDataInterface;

    public AccessPostings()
    {
        postingDataInterface = AllData.getPostingData();
    }

    public AccessPostings (PostingDataInterface dataInfo){
        this();
        postingDataInterface = dataInfo;
    }

    public List<Posting> getPostings()
    {
        return postingDataInterface.getPostings();
    }

    public void addPosting(User owner, Course course, String title, double price, String detail, boolean highlighted, int howOld)
    {
        postingDataInterface.addPosting(owner,course,title,price,detail,highlighted,howOld);
    }

    public void updatePosting(int pID, String newTitle, String newDetail, Course newCourse, boolean newHighlighted, double newPrice, int newHowOld)
    {
        postingDataInterface.updatePosting(pID,newTitle,newDetail,newCourse,newHighlighted,newPrice,newHowOld);
    }

    public void removePosting(Posting posting)
    {
        postingDataInterface.removePosting(posting);
    }
}
