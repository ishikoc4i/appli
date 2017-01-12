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
    private String VRimage;
    private int detailImage1;
    private int detailImage2;
    private int detailImage3;
    private int detailImage4;
    private int detailImage5;
    private int detailImage6;


    public LocationInfo(int imageResourceId,int pos, String timei, String location, String address,
                        String VRimage, int detailImage1, int detailImage2, int detailImage3,
                        int detailImage4, int detailImage5, int detailImage6) {
        this.imageResourceId = imageResourceId;
        this.pos =pos;
        this.timei = timei;
        this.location = location;
        this.address = address;
        this.VRimage = VRimage;
        this.detailImage1 = detailImage1;
        this.detailImage2 = detailImage2;
        this.detailImage3 = detailImage3;
        this.detailImage4 = detailImage4;
        this.detailImage5 = detailImage5;
        this.detailImage6 = detailImage6;

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

    public  String getVRimage(){
        return VRimage;
    }

    public int getDetailImage1(){
        return detailImage1;
    }
    public int getDetailImage2(){
        return detailImage2;
    }
    public int getDetailImage3(){
        return detailImage3;
    }
    public int getDetailImage4(){
        return detailImage4;
    }
    public int getDetailImage5(){
        return detailImage5;
    }
    public int getDetailImage6(){
        return detailImage6;
    }
}