package in.simransarin.popularmovies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.security.PolicySpi;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView popMovieGridView;
    Button b;
    ArrayList<Movie> arrayList;
    PopMoviesAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<Movie>();
        popMovieGridView = (GridView) findViewById(R.id.gridview);
        adapter = new PopMoviesAdapter(this, arrayList);

        popMovieGridView.setAdapter(adapter);
        String urlString = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=8e3c9228566e9cf6715ce7b3ffa92f31";
        PopMoviesDownloadTask task = new PopMoviesDownloadTask(this);
        task.execute(urlString);

        popMovieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, MovieActivity.class);
                Movie clickedMovie = (Movie) parent.getAdapter().getItem(position);
                i.putExtra("movie_id", clickedMovie.getId());
                startActivity(i);
            }
        });
    }

    public void processResult(Movie[] movies) {
        arrayList.clear();
        for (Movie m : movies) {
            arrayList.add(m);
        }
        adapter.notifyDataSetChanged();
    }
}

