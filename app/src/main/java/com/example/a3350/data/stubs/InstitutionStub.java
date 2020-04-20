package com.example.a3350.data.stubs;

import com.example.a3350.data.InstitutionDataInterface;
import com.example.a3350.objects.Institution;

import java.util.ArrayList;
import java.util.List;

public class InstitutionStub implements InstitutionDataInterface
{
    private List<Institution> institutions;
    public InstitutionStub()
    {
        institutions = new ArrayList<>();
        institutions.add(new Institution("University of Manitoba", "@umanitoba.ca"));
        institutions.add(new Institution("Red River", "@redriver.ca"));
        institutions.add(new Institution("MITT", "@mitt.ca"));
    }

    public List<Institution> getInstitutions() {
        return institutions;
    }
}
