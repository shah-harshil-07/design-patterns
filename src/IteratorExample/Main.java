package IteratorExample;

import java.util.*;

class Video {
    String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

interface Playlist {
    public Iterator createIterator();
    public void addVideo(Video video);
    public ArrayList<Video> getVideos();
}

class YouTubePlaylist implements Playlist {
    private final ArrayList<Video> videos = new ArrayList<>();

    @Override
    public void addVideo(Video video) {
        videos.add(video);
    }

    @Override
    public ArrayList<Video> getVideos() {
        return videos;
    }

    @Override
    public Iterator createIterator() {
        return new YouTubeIterator(this.videos);
    }
}

interface Iterator {
    public Video next();
    public boolean hasNext();
}

class YouTubeIterator implements Iterator {
    private int position = 0;
    private final ArrayList<Video> videos;

    public YouTubeIterator(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }

    @Override
    public boolean hasNext() {
        return position < this.videos.size();
    }

    @Override
    public Video next() {
        return this.hasNext() ? this.videos.get(position++) : null;
    }
}

public class Main {
    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        Iterator iterator = playlist.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }
}
