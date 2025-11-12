package StrategyExample;

interface Strategy {
    public String match(String location);
}

class NearestDriverStrategy implements Strategy {
    @Override
    public String match(String location) {
        return "with nearest driver at " + location;
    }
}

class SurgePricingStrategy implements Strategy {
    @Override
    public String match(String location) {
        return "based on surge pricing priority at " + location;
    }
}

class AirportQueueStrategy implements Strategy {
    @Override
    public String match(String location) {
        return "from airport queue at " + location;
    }
}

class RideMatchingService {
    private final Strategy strategy;

    public RideMatchingService(Strategy strategy) {
        this.strategy = strategy;
    }

    public void matchRider(String riderLocation) {
        System.out.println("Matching rider at " + riderLocation + " " + this.strategy.match(riderLocation));
    }
}

public class Main {
    public static void main(String[] args) {
        Strategy strategy1 = new NearestDriverStrategy();
        Strategy strategy2 = new SurgePricingStrategy();
        Strategy strategy3 = new AirportQueueStrategy();

        RideMatchingService nearestDriverService = new RideMatchingService(strategy1);
        RideMatchingService surgePricingService = new RideMatchingService(strategy2);
        RideMatchingService airportQueueService = new RideMatchingService(strategy3);

        nearestDriverService.matchRider("Downtown");
        surgePricingService.matchRider("City Center");
        airportQueueService.matchRider("Airport Terminal 1");
    }
}

