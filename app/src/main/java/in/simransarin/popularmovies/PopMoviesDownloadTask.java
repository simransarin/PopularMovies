package in.simransarin.popularmovies;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by simra on 29-06-2016.
 */

public class PopMoviesDownloadTask extends AsyncTask<String, Void, Movie[]> {
    MainActivity mActivity;
    ProgressDialog progressDialog;
    private Context mContext;
    public PopMoviesDownloadTask (MainActivity activity){
        mActivity = activity;
    }


    @Override
    protected void onPreExecute() {
        progressDialog= new ProgressDialog(mActivity);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        super.onPreExecute();
    }


    private Movie[] parseJson(String json){
        Movie[] output;
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray Movie = obj.getJSONArray("results");
            output = new Movie[Movie.length()];
            for(int i=0; i < Movie.length(); i++) {
                JSONObject MovieJSONObject = Movie.getJSONObject(i);
                String name = MovieJSONObject.getString("original_title");
                String poster_key = MovieJSONObject.getString("poster_path");
                int id = MovieJSONObject.getInt("id");
                output[i] = new Movie(name,id,poster_key);
            }
        } catch (JSONException e) {
            return null;
        }
        return output;
    }

    @Override
    protected Movie[] doInBackground(String... params) {
        if(params.length == 0)
            return null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if(inputStream==null)
            {
                return null;
            }
            Scanner s = new Scanner(inputStream);
            while (s.hasNext()){
                buffer.append(s.nextLine());
            }
            Log.i("jsondata",buffer.toString());
        } catch (MalformedURLException e) {
            return null;
        } catch (ProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return parseJson(buffer.toString());
    }

    @Override
    protected void onPostExecute(Movie[] movie) {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        mActivity.processResult(movie);
    }

}
