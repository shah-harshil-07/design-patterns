package Observer;

import java.util.*;

interface Subscriber {
    void notify(String videoTitle);
}

class EmailSubscriber implements Subscriber {
    private final String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void notify(String videoTitle) {
        System.out.println("Email sent on " + this.email + " for subscriber notification, of new video: " + videoTitle);
    }
}

class MobileSubscriber implements Subscriber {
    private final String phoneNo;

    public MobileSubscriber(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public void notify(String videoTitle) {
        System.out.println("In-app push notification sent on " + this.phoneNo + " for subscriber notification, of new video: " + videoTitle);
    }
}

interface Channel {
    public void uploadNewVideo(String videoTitle);
    public void subscribe(Subscriber subscriber);
}

class YouTubeChannel implements Channel {
    private final ArrayList<Subscriber> subscribers;

    public YouTubeChannel() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void notifySubscribers(String videoTitle) {
        for (Subscriber subscriber: this.subscribers) {
            subscriber.notify(videoTitle);
        }
    }

    @Override
    public void uploadNewVideo(String videoTitle) {
        System.out.println("Uploading: " + videoTitle + "\n");
        this.notifySubscribers(videoTitle);
    }
}

public class Main {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();
        Subscriber emailSubscriber = new EmailSubscriber("shahharshil1998@gmail.com");
        Subscriber mobileSubscriber = new MobileSubscriber("9904168501");

        channel.subscribe(emailSubscriber);
        channel.subscribe(mobileSubscriber);

        channel.uploadNewVideo("Design Patterns in Java");
    }
}
