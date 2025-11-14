package FacadeExample;

class PaymentService {
    public void makePayment(String accountId, double amount) {
        System.out.println("Payment made of $" + amount + " by account: " + accountId);
    }
}

class SeatReservationService {
    public void reserveSeat(String movieId, String seatNo) {
        System.out.println("Seat no. " + seatNo + " booked for movie: " + movieId);
    }
}

class NotificationService {
    public void notifyUser(String userEmail) {
        System.out.println("Booking email sent to the user on email: " + userEmail);
    }
}

class LoyaltyPointsService {
    public void generateLoyaltyPoints(String accountId, int points) {
        System.out.println(points + " points generated for account: " + accountId);
    }
}

class TicketBookingService {
    public void bookTicket(String movieId, String seatNo, String accountId) {
        System.out.println("Ticket booked on seat: " + seatNo + " booked for movie: " + movieId + " by account: " + accountId);
    }
}

class MovieBookingFacade {
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final LoyaltyPointsService loyaltyPointsService;
    private final TicketBookingService ticketBookingService;
    private final SeatReservationService seatReservationService;

    public MovieBookingFacade() {
        this.paymentService = new PaymentService();
        this.notificationService = new NotificationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.ticketBookingService = new TicketBookingService();
        this.seatReservationService = new SeatReservationService();
    }
    

    public void bookMovie(
        String accountId,
        String movieId,
        String seatNo,
        double amount,
        String userEmail,
        int points
    ) {
        this.paymentService.makePayment(accountId, amount);
        this.seatReservationService.reserveSeat(movieId, seatNo);
        this.ticketBookingService.bookTicket(movieId, seatNo, accountId);
        this.notificationService.notifyUser(userEmail);
        this.loyaltyPointsService.generateLoyaltyPoints(accountId, points);

        System.out.println("Movie ticket booking completed successfully!");
    }
}

public class Main {
    public static void main(String[] args) {
        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();
        movieBookingFacade.bookMovie(
            "account123",
            "movie123",
            "A10",
            405.81,
            "shahharshil1998@gmail.com",
            40
        );
    }
}
