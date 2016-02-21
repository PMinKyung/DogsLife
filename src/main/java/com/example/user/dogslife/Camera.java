package com.example.user.dogslife;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.net.Uri;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera extends Activity {

    private static final String TAG = "MjpegActivity";

    private MjpegView mv;
   // LinearLayout container;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //container = (LinearLayout)findViewById(R.id.linear);

        //sample public cam
        String URL = "http://203.246.112.252:50059/stream/video.mjpeg";

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mv = new MjpegView(this);
        setContentView(mv);

        new DoRead().execute(URL);

    }

    public void onPause() {
        super.onPause();
        mv.stopPlayback();
    }

    public class DoRead extends AsyncTask<String, Void, MjpegInputStream> {
        protected MjpegInputStream doInBackground(String... url) {
            //TODO: if camera has authentication deal with it and don't just not work
            HttpResponse res = null;
            DefaultHttpClient httpclient = new DefaultHttpClient();
            Log.d(TAG, "1. Sending http request");
            try {
                res = httpclient.execute(new HttpGet(URI.create(url[0])));
                Log.d(TAG, "2. Request finished, status = " + res.getStatusLine().getStatusCode());
                if(res.getStatusLine().getStatusCode()==401){
                    //You must turn off camera User Access Control before this will work
                    //Toast.makeText(getApplicationContext(), "영상을 불러오는데 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_LONG).show();
                    return null;
                }
                //Toast.makeText(getApplicationContext(), "영상을 불러오는데 성공했습니다. 잠시만 기다려 주세요.", Toast.LENGTH_LONG).show();
                return new MjpegInputStream(res.getEntity().getContent());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(), "영상을 불러오는데 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Request failed-ClientProtocolException", e);
                //Error connecting to camera
            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(), "영상을 불러오는데 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Request failed-IOException", e);
                //Error connecting to camera
            }

            return null;
        }

        protected void onPostExecute(MjpegInputStream result) {
            mv.setSource(result);
            mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
            mv.showFps(true);
        }
    }
}
