package BridgeExample;

interface VideoQuality {
    public void load(String quality);
}

class SDQuality implements VideoQuality {
    @Override
    public void load(String quality) {
        System.out.println("SD quality of movie loaded.");
    }
}

class HDQuality implements VideoQuality {
    @Override
    public void load(String quality) {
        System.out.println("HD quality of movie loaded.");
    }
}

class UltraHDQuality implements VideoQuality {
    @Override
    public void load(String quality) {
        System.out.println("Ultra HD quality of movie loaded.");
    }
}

abstract class VideoPlayer {
    protected VideoQuality videoQuality;

    public VideoPlayer(VideoQuality videoQuality) {
        this.videoQuality = videoQuality;
    }

    public abstract void play(String title);
}

class WebPlayer extends VideoPlayer {
    public WebPlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("Web Player: Playing " + title + " in " + videoQuality);
    }
}

class MobilePlayer extends VideoPlayer {
    public MobilePlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("Mobile Player: Playing " + title + " in " + videoQuality);
    }
}

class SmartTVPlayer extends VideoPlayer {
    public SmartTVPlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("Smart TV Player: Playing " + title + " in " + videoQuality);
    }
}

public class Main {
    public static void main(String[] args) {
        VideoQuality sdQuality = new SDQuality();
        VideoQuality hdQuality = new HDQuality();
        VideoQuality ultraHDQuality = new UltraHDQuality();

        VideoPlayer player1 = new WebPlayer(sdQuality);
        player1.play("Interstellar");

        VideoPlayer player2 = new MobilePlayer(hdQuality);
        player2.play("Inception");

        VideoPlayer player3 = new SmartTVPlayer(ultraHDQuality);
        player3.play("Dark Knight");
    }
}

