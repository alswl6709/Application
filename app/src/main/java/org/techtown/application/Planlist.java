package org.techtown.application;

public class Planlist {
    String place; //지역이름
    String year;
    String month;
    String day;

    public Planlist(String place, String year, String month, String day) {
        this.place = place;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //지역이름
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    //년도
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    //월
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    //일
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
