package com.example.a3350.data.stubs;

import com.example.a3350.data.AllData;
import com.example.a3350.data.PostingDataInterface;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;

import java.util.ArrayList;
import java.util.List;

public class PostingStub implements PostingDataInterface {

    private List<Posting> postings;

    public PostingStub()
    {
        //Initializing hardcoded courses and posting
        postings = new ArrayList<>();


        final User gene = AllData.getUserData().getUsers().get(3);
        final User michael = AllData.getUserData().getUsers().get(1);

        final Course comp3350 = AllData.getCourseData().getCourses().get(1);
        final Course comp3170 = AllData.getCourseData().getCourses().get(5);
        final Course comp3490 = AllData.getCourseData().getCourses().get(2);
        final Course eng3130 = AllData.getCourseData().getCourses().get(4);

        postings.add(new Posting(gene,comp3350, "Book about Soft Eng", 120, "Software Engineering notebook", true, 4));
        postings.add(new Posting(gene,comp3170, "Analyse Algorithms", 40, "How to analyse data structures and algorithms", true, 12));
        postings.add(new Posting(gene,comp3490, "Graphics Book", 230, "Introduction to Computer Graphics", false, 1));
        postings.add(new Posting(gene, eng3130, "Something about engineering", 330, "More details", true, 3));
        postings.add(new Posting(michael, eng3130, "Something about engineering by michael", 1330, "No details", false, 1));
        postings.add(new Posting(michael, comp3350, "Software Development", 30, "Software development life cycle", true, 6));
    }

    @Override
    public List<Posting> getPostings() {
        return postings;
    }

    @Override
    public void addPosting(User owner, Course course, String title, double price, String detail, boolean highlighted, int howOld)
    {
        Posting posting = new Posting(owner,course,title,price,detail,highlighted,howOld);
        if(!postings.contains(posting))
            postings.add(posting);
    }

    @Override
    public void updatePosting(int pID, String newTitle, String newDetail, Course newCourse, boolean newHighlighted, double newPrice, int newHowOld)
    {
        Posting posting = Filter.getPostByID(postings,pID);
        posting.setTitle(newTitle);
        posting.setDetail(newDetail);
        posting.setHowOld(newHowOld);
        posting.setPrice(newPrice);
        posting.setCourse(newCourse);
        posting.setHighlighted(newHighlighted);
    }

    @Override
    public void removePosting(Posting posting)
    {
        postings.remove(posting);
    }
}
