package id.ac.umn.uts_27962;

import java.io.Serializable;

public class SongDetails implements Serializable {
    private String title;
    private String songURI;

    public SongDetails(String title, String URI){
        this.title = title;
        this.songURI = songURI;
    }

    public String getTitle() { return this.title; }
    public String getSongURI() { return this.songURI; }


    public void setTitle(String title){ this.title = title; }
    public void setSongURI(String songURI) { this.songURI = songURI; }

    public String toString() { return this.getTitle(); }
}
