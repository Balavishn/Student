package com.example.student_sides;
public class uploadPDF{
    public String name;
    public String url;
    public String unitname;
    public String subheading;

    public uploadPDF() {
    }

    public uploadPDF(String name, String url,String unitname,String subheading) {
        this.name = name;
        this.url = url;
        this.unitname=unitname;
        this.subheading=subheading;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

}

