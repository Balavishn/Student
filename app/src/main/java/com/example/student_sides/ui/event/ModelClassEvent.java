package com.example.student_sides.ui.event;




public class ModelClassEvent {
    private String  Title,Description,Date,Time;

    public ModelClassEvent(String title, String description, String date, String time) {
        Title = title;
        Description = description;
        Date = date;
        Time = time;
    }
    public ModelClassEvent(){}

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
