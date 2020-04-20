package com.example.a3350;

import com.example.a3350.data.InstitutionDataInterface;
import com.example.a3350.logic.AccessInstitutions;
import com.example.a3350.objects.Institution;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccessInstitutionsTest {
    private AccessInstitutions accessInst;
    private Institution inst;
    private InstitutionDataInterface dataInterface;

    @Before
    public void setup(){
        dataInterface = mock(InstitutionDataInterface.class);
        inst = mock(Institution.class);
    }

    @Test
    public void getInstitutionsTest(){
        List<Institution> institutionTest = Arrays.asList(inst);
        when(dataInterface.getInstitutions()).thenReturn(institutionTest);
        accessInst = new AccessInstitutions(dataInterface);
        List<Institution> checkTest = accessInst.getInstitutions();
        assertEquals("the two list should be equal",checkTest,institutionTest);
    }
}
