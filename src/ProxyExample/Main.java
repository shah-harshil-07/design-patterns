package ProxyExample;

import java.util.HashMap;

interface VideDownloader {
    public String downloadVideo(String videoUrl);
}

class RealVideoDownloader implements VideDownloader {
    @Override
    public String downloadVideo(String videoUrl) {
        System.out.println("Downloading video from URL: " + videoUrl);
        String content = "Video content from " + videoUrl;
        System.out.println("Downloaded Content: " + content);
        return content;
    }
}

class CachedVideoDownloader implements VideDownloader {
    private final RealVideoDownloader videoDownloader;
    private final HashMap<String, String> videoUrlToContentMap;

    public CachedVideoDownloader() {
        this.videoUrlToContentMap = new HashMap<>();
        this.videoDownloader = new RealVideoDownloader();
    }

    @Override
    public String downloadVideo(String videoUrl) {
        if (this.videoUrlToContentMap.containsKey(videoUrl)) {
            System.out.println("Returning cached video for: " + videoUrl);
            return this.videoUrlToContentMap.get(videoUrl);
        }

        String videoContent = this.videoDownloader.downloadVideo(videoUrl);
        this.videoUrlToContentMap.put(videoUrl, videoContent);
        System.out.println(this.videoUrlToContentMap.get(videoUrl));
        return videoContent;
    }
}

public class Main {
    public static void main(String[] args) {
        VideDownloader cachedVideDownloader = new CachedVideoDownloader();

        System.out.println("User 1 tries to download the video.");
        cachedVideDownloader.downloadVideo("https://video.com/proxy-pattern");

        System.out.println();

        System.out.println("User 2 tries to download the same video again.");
        cachedVideDownloader.downloadVideo("https://video.com/proxy-pattern");
    }
}
