package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid.Model;

/**
 * Created by nerdywoffy on 5/13/17.
 */

public class MainGrid {
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MainGrid(String picture, String title, String Id) {
        this.picture = picture;
        this.title = title;
        this.Id = Id;
    }

    String picture;
    String title;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    String Id;
}
