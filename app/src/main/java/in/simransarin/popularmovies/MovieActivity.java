package in.simransarin.popularmovies;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Movie> favourite;
    int count = 1;
    Movie movie1;
    TextView summary;
    ImageButton imdb;
    //TextView title;
    TextView userRating;
    TextView releaseDate;
    ImageButton favourites;
    ImageView poster;
    ImageView trailer1;
    ImageView trailer2;
    ImageView trailer3;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        summary = (TextView) findViewById(R.id.overview);
        //title = (TextView)findViewById(R.id.title);
        userRating = (TextView)findViewById(R.id.userRating);
        releaseDate = (TextView)findViewById(R.id.releaseDate);
        favourites = (ImageButton)findViewById(R.id.favourites);
        imdb = (ImageButton)findViewById(R.id.imdb);
        poster = (ImageView)findViewById(R.id.poster);
        trailer1 = (ImageView)findViewById(R.id.trailer1);
        trailer2 = (ImageView)findViewById(R.id.trailer2);
        trailer3 = (ImageView)findViewById(R.id.trailer3);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayoutMain);
        favourite = new ArrayList<Movie>();
        int movie_id = getIntent().getIntExtra("movie_id",-1);
        if(movie_id!=-1) {
            MovieDownloadTask task = new MovieDownloadTask(this);
            task.execute("https://api.themoviedb.org/3/movie/" + movie_id + "?api_key=8e3c9228566e9cf6715ce7b3ffa92f31");
        }

        imdb.setOnClickListener(this);
        favourites.setOnClickListener(this);


        SharedPreferences sp = getSharedPreferences("favourites", Context.MODE_PRIVATE);
        int color = sp.getInt("color", -1);
        if (color != -1) {
            linearLayout.setBackgroundColor(color);
        }
    }

    public void processResults(Movie movie){
        movie1 = movie;
        setTitle(movie1.PopMovieName);
        summary.setText(movie1.overview);
        //title.setText(movie1.PopMovieName);
        userRating.setText("User Rating: " + movie1.userRating);
        releaseDate.setText("Date of Release: " + movie1.dateofRelease);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/" + movie1.posterKey).into(poster);
    }

    @Override
    public void onClick(View v) {
        if(v==trailer1)
        {
            Intent intent = new Intent
                    (Intent.ACTION_VIEW, Uri.parse("https://api.themoviedb.org/3/movie/+"
                            + movie1.getId()+"/videos?api_key=8e3c9228566e9cf6715ce7b3ffa92f31"));
            startActivity(intent);


        }
        else if(v==imdb)
        {Intent intent = new Intent
                (Intent.ACTION_VIEW, Uri.parse("http://www.imdb.com/title/" + movie1.imdb_Id+ "/"));
            startActivity(intent);

        } else if(v==favourites)
        {
            SharedPreferences sp = getSharedPreferences("favourites", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            if (count%2==0) {
                linearLayout.setBackgroundColor(Color.BLACK);
                editor.putInt("color", Color.BLACK);
                if(favourite != null)
                {
                    favourite.remove(movie1.getId());
                }
                editor.commit();
                count++;
                Toast.makeText(this,"Removed from FAVOURITES", Toast.LENGTH_LONG).show();
            } else {
                linearLayout.setBackgroundColor(Color.DKGRAY);
                editor.putInt("color", Color.DKGRAY);
                editor.commit();
                count++;
                Toast.makeText(this,"Added to FAVOURITES", Toast.LENGTH_LONG).show();
                favourite.add(movie1);
            }
        }
    }
}
