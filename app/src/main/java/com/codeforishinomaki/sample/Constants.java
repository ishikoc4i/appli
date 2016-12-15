package com.codeforishinomaki.sample;


public class Constants {

    public Constants() {

    }

    // 場所の情報をここでセットする
    public static final LocationInfo[] locations = {
            new LocationInfo( R.mipmap.pic1, 1, " 河南西中学校", "geo:0,0?q=38.496178,141.200487(河南西中学校)&z=20",
                    " 宮城県石巻市北村小崎一37番地2"),
            new LocationInfo(R.mipmap.pic3, 2, " 前谷地駅", "geo:0,0?q=38.511985,141.194352(前谷地駅)&z=20",
                    " 宮城県石巻市前谷地中埣"),
            new LocationInfo(R.mipmap.pic4, 3, " 前谷地駅前", "geo:0,0?q=38.511985,141.194352(前谷地駅前)&z=20",
                    " 宮城県石巻市前谷地中埣"),
            new LocationInfo(R.mipmap.pic5, 4, " 前谷地駅ホーム", "geo:0,0?q=38.511985,141.194352(前谷地駅ホーム)&z=20",
                    " 宮城県石巻市前谷地中埣"),
            new LocationInfo(R.mipmap.pic11, 5," 旭山", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                    " 宮城県石巻市北村")
    };

    public static int getLocationsLength() {
        return locations.length;
    }
}
