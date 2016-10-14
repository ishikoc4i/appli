package com.codeforishinomaki.sample;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

//    VideoView videoView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        videoView = (VideoView) findViewById(R.id.vV);
//        videoView.setVideoPath("R.raw.movie.mp4");
//        videoView.setVideoURI(Uri.parse("android.resource://" +
//                this.getPackageName() + "/" + R.raw.movie));
//
//        findViewById(R.id.rotate_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=38.511985,141.194352(前谷地駅ホーム)&z=20")));
//                if (getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                } else {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                }
//            }
//        });


        listView = (ListView) findViewById(R.id.list);
        if (listView != null) {
            listView.setAdapter(new ImageListAdapter());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    ListAdapter adapter = listView.getAdapter();
                    ImageListAdapter.LocationInfo item = (ImageListAdapter.LocationInfo) adapter.getItem(position);

//                    String mapUri = createMapUri(adapter, position);
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapUri)));
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                    String seltimei = item.kariTimei;
                    int selimage = item.imageResourceId;
                    String sellocation = item.location;
                    String selldetail = item.detail;

                    intent.putExtra("timei", seltimei);
                    intent.putExtra("image", selimage);
                    intent.putExtra("location", sellocation);
                    intent.putExtra("detail", selldetail);

                    startActivity(intent);
                    overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);


                }
            });
        }

//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                videoView.seekTo(0);
//                videoView.start();
//            }
//        });

//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_UP){
//                    if (videoView.isPlaying()) {
//                        videoView.pause();
//                    }else{
//                        videoView.start();
//                    }
//                }
//
//                return true;
//            }
//        });

        //videoView.setMediaController(new MediaController(this));
    }

    private String createMapUri(ListAdapter adapter, int position) {
        final String locationName = getLocationName(adapter, position);
        final String location = ((ImageListAdapter.LocationInfo) adapter.getItem(position)).getLocation();

        return String.format("geo:0,0?q=%1$s(%2$s)&z=16", location, locationName);
    }


    private static String getLocationName(ListAdapter adapter, int position) {
        for (int i = position; 0 <= i; i--) {
            final ImageListAdapter.LocationInfo item = (ImageListAdapter.LocationInfo) adapter.getItem(i);
            if (!item.getTimei().isEmpty()) {
                return item.getTimei();
            }
        }
        return "";
    }

    @Override
    protected void onResume() {
        super.onResume();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                videoView.seekTo(0);
//                videoView.start();
//            }
//        }, 500);
    }

    // http://stackoverflow.com/questions/10380989/how-do-i-get-the-current-orientation-activityinfo-screen-orientation-of-an-a
    private int getScreenOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0
                || rotation == Surface.ROTATION_180) && height > width ||
                (rotation == Surface.ROTATION_90
                        || rotation == Surface.ROTATION_270) && width > height) {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        // if the device's natural orientation is landscape or if the device
        // is square:
        else {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }

    private static class ImageListAdapter extends BaseAdapter {

        private static class LocationInfo {
            private int imageResourceId;
            private String kariTimei;
            private String timei;
            private String location;
            private String detail;


            public LocationInfo(int imageResourceId, String karitimei, String timei, String location, String detailt) {
                this.imageResourceId = imageResourceId;
                this.kariTimei = karitimei;
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

      //  "geo:0,0?q=38.511985,141.194352(前谷地駅ホーム)&z=20"
        private LocationInfo[] locations = {
                new LocationInfo( R.drawable.pic1, " 河南西中学校", " 河南西中学校", "geo:0,0?q=38.496178,141.200487(河南西中学校)&z=20",
                        "   撮影場所:河南西中の住所\n   撮影時期:夏"),
//                new LocationInfo(R.drawable.pic2, " 河南西中学校", "", "geo:0,0?q=38.496178,141.200487(河南西中学校)&z=20",
//                        "   撮影場所:河南西中の住所\n   撮影時期:夏"),
//                new LocationInfo(R.drawable.pic3, " 前谷地駅", " 前谷地駅", "geo:0,0?q=38.511985,141.194352(前谷地駅)&z=20",
//                        "   撮影場所:前谷地駅の住所\n   撮影時期:夏"),
//                new LocationInfo(R.drawable.pic4, " 前谷地駅前", " 前谷地駅前", "geo:0,0?q=38.511985,141.194352(前谷地駅前)&z=20",
//                        "   撮影場所:前谷地駅の住所\n   撮影時期:夏"),
//                new LocationInfo(R.drawable.pic5, " 前谷地駅ホーム", " 前谷地駅ホーム", "geo:0,0?q=38.511985,141.194352(前谷地駅ホーム)&z=20",
//                        "   撮影場所:前谷地駅の住所\n   撮影時期:夏"),
//                new LocationInfo(R.drawable.pic6, " 前谷地駅ホーム", "", "geo:0,0?q=38.511985,141.194352(前谷地駅ホーム)&z=20",
//                        "   撮影場所:前谷地駅の住所\n   撮影時期:夏"),
//                new LocationInfo(R.drawable.pic7, " 前谷地駅ホーム", "", "geo:0,0?q=38.511985,141.194352(前谷地駅ホーム)&z=20",
//                        "   撮影場所:前谷地駅の住所\n   撮影時期:夏"),
//                new LocationInfo(R.drawable.pic8, " 前谷地駅ホーム", "", "geo:0,0?q=38.511985,141.194352(前谷地駅ホーム)&z=20",
//                        "   撮影場所:前谷地駅の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic9, " 旭山", " 旭山", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic10, " 旭山", "", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic11, " 旭山",  "", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic12, " 旭山", "", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic13, " 旭山", "", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic14, " 旭山", "", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic15, " 旭山", "", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏"),
                new LocationInfo(R.drawable.pic16, " 旭山", "", "geo:0,0?q=38.490462,141.180866(旭山)&z=20",
                        "   撮影場所:旭山の住所\n   撮影時期:夏")
        };


        @Override
        public int getCount() {
            return locations.length;
        }

        @Override
        public Object getItem(int i) {
            return locations[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_image, parent, false);
                ImageView iv = (ImageView) convertView.findViewById(R.id.image);
                TextView timei = (TextView) convertView.findViewById(R.id.timei);
                holder = new ViewHolder(iv,timei);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            LocationInfo location = locations[position];
            holder.image.setImageResource(location.getImageResourceId());
            if (location.getTimei().isEmpty()) {
                holder.text.setVisibility(View.GONE);
            } else {
                holder.text.setVisibility(View.VISIBLE);
            }
            holder.text.setText(location.getTimei());
            return convertView;


        }

        private static class ViewHolder {
            public final ImageView image;
            public final TextView text;


            ViewHolder(ImageView iv, TextView timei) {
                this.image = iv;
                this.text = timei;
            }
        }
    }

}
