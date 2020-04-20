package com.example.a3350.objects;

public class Institution
{
    private String name;
    private String domain;

    public Institution(String name, String domain)
    {
        this.name = name;
        this.domain = domain;
    }

    public String getName()
    {
        return name;
    }

    public String getDomain()
    {
        return domain;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public boolean equals(Institution institution)
    {
        return this.name.equals(institution.getName()) && this.domain.equals(institution.getDomain());
    }
}
