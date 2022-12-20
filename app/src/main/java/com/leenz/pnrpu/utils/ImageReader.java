package com.leenz.pnrpu.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;

public class ImageReader {
    public static class RequestTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... urlString) {
            try {
                URL url = new URL(urlString[0]);

                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return  bmp;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}