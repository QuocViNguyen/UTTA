package com.example.a3350.objects;

import com.example.a3350.logic.Filter;

public class Posting
{
    private int id;
    private Course course;
    private String title;
    private double price;
    private String detail;
    private boolean highlighted;
    private User owner;
    private int howOld; //in months

    public Posting(User owner, Course course, String title, double price, String detail, boolean highlighted, int howOld)
    {
        this.owner = owner;
        this.course = course;
        this.title = title;
        this.price = price;
        this.detail = detail;
        this.highlighted = highlighted;
        this.howOld = howOld;
        id = Filter.postID++;
    }

    public Posting(int id, User owner, Course course, String title, double price, String detail, boolean highlighted, int howOld)
    {
        this.owner = owner;
        this.course = course;
        this.title = title;
        this.price = price;
        this.detail = detail;
        this.highlighted = highlighted;
        this.howOld = howOld;
        this.id = id;
    }

    public User getOwner()
    {
        return owner;
    }

    public Course getCourse()
    {
        return course;
    }

    public String getTitle()
    {
        return title;
    }

    public double getPrice()
    {
        return price;
    }

    public String getDetail()
    {
        return detail;
    }

    public boolean isHighlighted()
    {
        return highlighted;
    }

    public int getHowOld()
    {
        return howOld;
    }

    //Modification functions
    public void setCourse(Course course)
    {
        this.course = course;
    }

    public void setPrice(double newPrice)
    {
        this.price = newPrice;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setHowOld(int howOld)
    {
        this.howOld = howOld;
    }

    public void setHighlighted(boolean highlighted)
    {
        this.highlighted = highlighted;
    }

    @Override
    public String toString()
    {
        return title;
    }

    public int getId()
    {
        return id;
    }
}
