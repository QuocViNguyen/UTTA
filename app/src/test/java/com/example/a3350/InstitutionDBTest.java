package com.example.a3350;

import com.example.a3350.data.database.InstitutionDatabase;
import com.example.a3350.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InstitutionDBTest {
    private InstitutionDatabase instDB;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.instDB = new InstitutionDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
    }

    @Test
    public void searchInstitutionTest(){
        assertEquals("@hogwiz.uk", instDB.getInstitutions().get(0).getDomain());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
