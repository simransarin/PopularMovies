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
public class MovieDownloadTask extends AsyncTask<String, Void, Movie> {
    MovieActivity mActivity;

    public MovieDownloadTask (MovieActivity activity) {
        mActivity = activity;
    }

    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog= new ProgressDialog(mActivity);
        progressDialog.setMessage("Fetching data");
        progressDialog.show();
        super.onPreExecute();
    }

    private Movie parseJson(String json){
        Movie output;
        try {
            Log.i("Log_MovieDownloadTask", "Try");
            JSONObject obj = new JSONObject(json);
            String overview = obj.getString("overview");
            String imdb_id = obj.getString("imdb_id");
            String userRating = obj.getString("vote_average");
            String dateOfRelease = obj.getString("release_date");
            String poster_key = obj.getString("poster_path");
            String popMovieName = obj.getString("original_title");
            output = new Movie(popMovieName,overview,imdb_id, poster_key, userRating, dateOfRelease);
            Log.i("Log_MovieDownloadTask", output.getPopMovieName());
            return output;

        } catch (JSONException e) {
            Log.i("MovieDownloadTask", "JSONException");
            return null;
        }

    }

    @Override
    protected Movie doInBackground(String... params) {
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
            Log.i("MovieDownloadTask", "MalformedURLException");
            return null;
        } catch (ProtocolException e) {
            Log.i("MovieDownloadTask", "ProtocolException");
            return null;
        } catch (IOException e) {
            Log.i("MovieDownloadTask", "IOException");
            return null;
        }
        return parseJson(buffer.toString());
    }

    @Override
    protected void onPostExecute(Movie movie) {

        if (progressDialog.isShowing())
            progressDialog.dismiss();
        mActivity.processResults(movie);

    }
}
