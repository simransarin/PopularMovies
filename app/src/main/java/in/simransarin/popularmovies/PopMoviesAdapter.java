package in.simransarin.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by simra on 20-06-2016.
 */
public class PopMoviesAdapter extends ArrayAdapter<Movie> {
    Context context;
    ArrayList<Movie> arrayList = new ArrayList<Movie>();

    public PopMoviesAdapter(Context context, ArrayList<Movie> objects) {
        super(context, 0, objects);
        arrayList = objects;
        this.context=context;
    }

    private class PopMoviesHolder{
        ImageView PopMovieImageView;
        TextView PopMovieTextView;

        public PopMoviesHolder(ImageView popMovieImageView, TextView popMovieTextView) {
            PopMovieImageView = popMovieImageView;
            PopMovieTextView = popMovieTextView;
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(this.context).inflate(R.layout.popmovies, parent, false);
            ImageView PopMovieImageView = (ImageView) v.findViewById(R.id.popMovie);
            TextView PopMovieTextView = (TextView) v.findViewById(R.id.text);
            PopMoviesHolder holder = new PopMoviesHolder(PopMovieImageView, PopMovieTextView);
            v.setTag(holder);
        }
        PopMoviesHolder holder = (PopMoviesHolder) v.getTag();
        Movie currentPopMovie = arrayList.get(position);
        holder.PopMovieTextView.setText(currentPopMovie.PopMovieName);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + currentPopMovie.posterKey).noFade().centerCrop().resize(150,150).into(holder.PopMovieImageView);
        return v;
    }
}
