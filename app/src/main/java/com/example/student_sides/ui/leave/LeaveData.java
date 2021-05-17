package com.example.student_sides.ui.leave;

class LeaveData {
    String name,id,leave,days;

    public LeaveData(String name, String id, String leave, String days) {
        this.name = name;
        this.id = id;
        this.leave = leave;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
