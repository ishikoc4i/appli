package com.codeforishinomaki.sample;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.VideoView;

public class MainActivity extends Activity {

    VideoView videoView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.vV);
        videoView.setVideoPath("R.raw.movie.mp4");
        videoView.setVideoURI(Uri.parse("android.resource://" +
                this.getPackageName() + "/" + R.raw.movie));

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ImageListAdapter());


        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (videoView.isPlaying()) {
                        videoView.pause();
                    }else{
                        videoView.start();
                    }
                }

                return true;
            }
        });

        //videoView.setMediaController(new MediaController(this));
        
    }

    @Override
    protected void onResume() {
        super.onResume();

        videoView.start();

    }

    private static class ImageListAdapter extends BaseAdapter {

        private int[] imageResourceIds = {R.drawable.pic1,R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8};


        @Override
        public int getCount() {
            return imageResourceIds.length;
        }

        @Override
        public Object getItem(int i) {
            return imageResourceIds[i];
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
                holder = new ViewHolder(iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.image.setImageResource(imageResourceIds[position]);
            return convertView;
        }

        private static class ViewHolder {
            public final ImageView image;

            ViewHolder(ImageView iv) {
                this.image = iv;
            }
        }
    }

}
