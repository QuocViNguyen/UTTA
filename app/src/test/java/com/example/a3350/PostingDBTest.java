package com.example.a3350;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.a3350.data.database.PostingDatabase;
import com.example.a3350.logic.Filter;
import com.example.a3350.objects.Posting;
import com.example.a3350.objects.User;
import com.example.a3350.objects.Course;
import com.example.a3350.objects.Institution;
import com.example.a3350.utils.TestUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PostingDBTest {
    private PostingDatabase postDB;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.postDB = new PostingDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
        Filter.setPostID();
    }

    @Test
    public void getPostingsTest(){
        assertEquals(1, postDB.getPostings().get(1).getId());
    }

    @Test
    public void addPostingTest(){
        final User owner = postDB.getPostings().get(0).getOwner();
        final Course course = postDB.getPostings().get(0).getCourse();
        assertNotNull(owner.getEmail());
        assertEquals(3030, course.getCourseID());
        postDB.addPosting(owner, course, "", 42.31,
                "Really gets you in the right state to learn.", false, 1);

        assertEquals(28, postDB.getPostings().get(28).getId());
    }

    @Test
    public void removePostingTest(){
        postDB.removePosting(postDB.getPostings().get(0));
        assertEquals("Introduction to Automata Theory, Languages, and Computation", postDB.getPostings().get(0).getTitle());
    }

    @Test
    public void updatePostingTest(){
        Posting post = postDB.getPostings().get(0);
        post.setHighlighted(true);
        post.setHowOld(99);
        postDB.updatePosting(post.getId(),post.getTitle(), post.getDetail(), post.getCourse(), post.isHighlighted(),post.getPrice(),post.getHowOld());
        assertEquals(true, postDB.getPostings().get(0).isHighlighted());
        assertEquals(99, postDB.getPostings().get(0).getHowOld());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}

