package com.example.a3350.logic;

import com.example.a3350.data.AllData;
import com.example.a3350.data.InstitutionDataInterface;
import com.example.a3350.objects.Institution;

import java.util.List;

public class AccessInstitutions
{
    private InstitutionDataInterface institutionDataInterface;

    public AccessInstitutions()
    {
        institutionDataInterface = AllData.getInstitutionData();
    }

    public AccessInstitutions(InstitutionDataInterface dataInfo) {
        this();
        institutionDataInterface = dataInfo;
    }

    public List<Institution> getInstitutions()
    {
        return institutionDataInterface.getInstitutions();
    }
}
