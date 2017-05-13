package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class AppVariable {
    public static String TMDB_APIKEY = "04cb09ef9225cd14e21a36c226cd084c";
    public static String TMDB_QUALITY = "w185";
    public static String TMDB_BASEPATH_IMG = "http://image.tmdb.org/t/p/" + TMDB_QUALITY;
    public enum Type{MOVIE,TV}
    public enum Kind{TRENDING,NOW,UPCOMING}
}
