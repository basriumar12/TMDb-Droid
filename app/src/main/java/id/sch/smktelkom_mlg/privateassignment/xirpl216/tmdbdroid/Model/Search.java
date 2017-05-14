package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model;

/**
 * Created by nerdywoffy on 5/14/17.
 */

public class Search {
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Search(String image, String title, String year, String id, String overview) {
        this.image = image;
        this.title = title;
        this.year = year;
        this.id = id;
        this.overview = overview;
    }

    public String image;
    public String title;
    public String year;
    public String id;
    public String overview;
}
