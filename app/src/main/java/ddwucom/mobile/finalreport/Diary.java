package ddwucom.mobile.finalreport;

import android.widget.ImageView;

import java.io.Serializable;

public class Diary implements Serializable{
    private long _id;
    private String date;
    private String weather;
    private String title;
    private String place;
    private String feeling;
    private ImageView feeling_pic;
    private String story;


    public Diary(String date, String weather, String title, String place, String feeling, String story) {
        this.date = date;
        this.weather = weather;
        this.title = title;
        this.place = place;
        this.feeling = feeling;
//        this.feeling_pic = feeling_pic;
        this.story = story;
    }

    public Diary(long _id, String date, String weather, String title, String place, String feeling, String story) {
        this._id = _id;
        this.date = date;
        this.weather = weather;
        this.title = title;
        this.place = place;
        this.feeling = feeling;
//        this.feeling_pic = feeling_pic;
        this.story = story;
    }

    public Diary(long _id, String date, String weather, String title, String place, String feeling, ImageView feeling_pic, String story) {
        this._id = _id;
        this.date = date;
        this.weather = weather;
        this.title = title;
        this.place = place;
        this.feeling = feeling;
        this.feeling_pic = feeling_pic;
        this.story = story;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public ImageView getFeeling_pic() {
        return feeling_pic;
    }

    public void setFeeling_pic(ImageView feeling_pic) {
        this.feeling_pic = feeling_pic;
    }
}
