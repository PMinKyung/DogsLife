package com.example.user.dogslife;

public class DiaryList {

    private String Diary ;
    private String Category ;
    private String Date ;
    private String Time ;
    private int id ;
    private int Image;

    public DiaryList() {
        Diary = null ;
        Category = null ;
        Date = null ;
        Time = null ;
    }

    public DiaryList(String Diary, String Category) {
        this.Diary = Diary ;
        this.Category = Category ;
    }

    public String GetDiary() {
        return Diary ;
    }
    public String GetDate() {
        return Date ;
    }
    public String GetCategory() {
        return Category ;
    }
    public int GetId() { return id ;}
    public int GetImage() { return Image ;}
    public String GetTime() {
        return Time ;
    }

    public void SetDiary(String Diary) {
        this.Diary = Diary ;
    }
    public void SetDate(String Date) {
        this.Date = Date ;
    }
    public void SetCategory(String Category) {
        this.Category = Category ;
    }
    public void SetId(int id) {this.id = id; }
    public void SetTime(String Time) {this.Time = Time; }
}
