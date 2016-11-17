package com.codeforishinomaki.sample;

/**
 * Created by syuto on 2016/11/02.
 */


//
// 場所の情報をもつクラス
//

public class LocationInfo {
    private int imageResourceId;
    private int pos;
    private String timei;
    private String location;
    private String detail;


    public LocationInfo(int imageResourceId,int pos, String timei, String location) {
        this.imageResourceId = imageResourceId;
        this.pos = pos;
        this.timei = timei;
        this.location = location;
        //this.detail = detailt;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int pos() {
        return pos;
    }

    public String getTimei() {
        return timei;
    }

    public String getLocation() {
        return location;
    }

//    public String getDetail(){
//        return detail;
//    }
}