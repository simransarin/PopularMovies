package in.simransarin.popularmovies;

/**
 * Created by simra on 29-06-2016.
 */
public class Movie {
    String overview;
    String imdb_Id;
    String posterKey;
    String PopMovieName;
    int movie_id;
    String userRating;
    String dateofRelease;

    public Movie( String popMovieName, String overview, String imdb_Id, String posterKey, String userRating, String dateofRelease) {
        this.overview = overview;
        this.imdb_Id = imdb_Id;
        this.posterKey = posterKey;
        PopMovieName = popMovieName;
        this.userRating = userRating;
        this.dateofRelease = dateofRelease;
    }

    public Movie(String popMovieName, int movie_id, String posterKey) {
        PopMovieName = popMovieName;
        this.movie_id = movie_id;
        this.posterKey = posterKey;
    }

    public Movie( String popMovieName,String overview, String imdb_Id, String posterKey, int movie_id, String userRating, String dateofRelease) {
        this.overview = overview;
        this.imdb_Id = imdb_Id;
        this.posterKey = posterKey;
        PopMovieName = popMovieName;
        this.movie_id = movie_id;
        this.userRating = userRating;
        this.dateofRelease = dateofRelease;
    }

    public Movie(String popMovieName, int movie_id) {
        PopMovieName = popMovieName;
        this.movie_id = movie_id;
    }

    public Movie(String overview, String imdb_Id, String posterKey) {
        this.overview = overview;
        this.imdb_Id = imdb_Id;
        this.posterKey = posterKey;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImdb_Id() {
        return imdb_Id;
    }

    public void setImdb_Id(String imdb_Id) {
        this.imdb_Id = imdb_Id;
    }

    public String getPosterKey() {
        return posterKey;
    }

    public void setPosterKey(String posterKey) {
        this.posterKey = posterKey;
    }

    public String getPopMovieName() {
        return PopMovieName;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getDateofRelease() {
        return dateofRelease;
    }

    public void setDateofRelease(String dateofRelease) {
        this.dateofRelease = dateofRelease;
    }

    public void setPopMovieName(String popMovieName) {
        PopMovieName = popMovieName;
    }

    public int getId() {
        return movie_id;
    }

    public void setId(int id) {
        this.movie_id = movie_id;
    }
}
