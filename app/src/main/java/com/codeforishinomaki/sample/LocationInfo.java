package com.codeforishinomaki.sample;

//
// 場所の情報をもつクラス
//

public class LocationInfo {
    private int imageResourceId;
    private String timei;
    private String location;
    private int pos;
    private String address;



    public LocationInfo(int imageResourceId,int pos, String timei, String location, String address) {
        this.imageResourceId = imageResourceId;
        this.pos =pos;
        this.timei = timei;
        this.location = location;
        this.address = address;

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

    public String getAddress() {
        return address;
    }

}