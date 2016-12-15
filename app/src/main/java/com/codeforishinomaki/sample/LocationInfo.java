package com.codeforishinomaki.sample;

//
// 場所の情報をもつクラス
//

public class LocationInfo {
    private int imageResourceId;
    private String kariTimei;
    private String timei;
    private String location;
    private int pos;
    private String detail;


    public LocationInfo(int imageResourceId,int pos, String karitimei, String timei, String location, String detailt) {
        this.imageResourceId = imageResourceId;
        this.kariTimei = karitimei;
        this.pos =pos;
        this.timei = timei;
        this.location = location;
        this.detail = detailt;
    }

    public String getKaritimei(){
        return kariTimei;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int pos(){
        return pos;
    }

    public String getTimei() {
        return timei;
    }

    public String getLocation() {
        return location;
    }

    public String getDetail(){
        return detail;
    }
}