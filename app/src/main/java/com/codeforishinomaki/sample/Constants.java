package com.codeforishinomaki.sample;


public class Constants {

    public Constants() {

    }

    // 場所の情報をここでセットする
    public static final LocationInfo[] locations = {
            new LocationInfo( R.mipmap.pic1, 1, "馬っこ山", "geo:0,0?q=38.468041,141.303752(トヤケ森山)&z=20",
                    " 宮城県石巻市南境鳥屋森山", "hageyama.jpg", R.drawable.picture1, R.drawable.picture2,
                    R.drawable.picture3, R.drawable.picture4, R.drawable.picture5,  R.drawable.picture6),
            new LocationInfo(R.drawable.asahi6, 2,"旭山", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                    " 宮城県石巻市北村", "asahiyama.JPG", R.drawable.asahi1, R.drawable.asahi2,
                    R.drawable.asahi3, R.drawable.asahi4, R.drawable.asahi5,  R.drawable.asahi6),
            new LocationInfo(R.mipmap.pic6, 3, "上品山", "geo:0,0?q=38.501161,141.35868(上品山)&z=20",
                    " 宮城県石巻市三輪田", "joubonsan.jpg", R.drawable.joubon1, R.drawable.joubon2,
                    R.drawable.joubon3, R.drawable.joubon4, R.drawable.joubon5,  R.drawable.joubon6)
    };

    public static int getLocationsLength() {
        return locations.length;
    }
}
