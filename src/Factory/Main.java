package Factory;

interface Logistics {
    public void transport();
}

class RoadLogistics implements Logistics {
    @Override
    public void transport() {
        System.out.println("Providing the logistics service via truck!");
    }
}

class WaterLogistics implements Logistics {
    @Override
    public void transport() {
        System.out.println("Providing the logistics service via ship!");
    }
}

class AirLogistics implements Logistics {
    @Override
    public void transport() {
        System.out.println("Providing the logistics service via plane!");
    }
}

class LogisticsFactory {
    public static Logistics getLogistics(String mode) {
        switch (mode) {
            case "water" -> {
                return new WaterLogistics();
            }
            case "air" -> {
                return new AirLogistics();
            }
            case "road" -> {
                return new RoadLogistics();
            }
            default -> throw new IllegalArgumentException("Invalid logistics mode: " + mode);
        }
    }
}

class LogisticsService {
    private Logistics logistics = null;

    public LogisticsService(String transportMode) {
        this.logistics = LogisticsFactory.getLogistics(transportMode);
    }

    public void transport() {
        logistics.transport();
    }
}

public class Main {
    public static void main(String[] args) {
        LogisticsService waterService = new LogisticsService("water");
        waterService.transport();

        LogisticsService airService = new LogisticsService("air");
        airService.transport();

        LogisticsService roadService = new LogisticsService("road");
        roadService.transport();

        LogisticsService invalidService = new LogisticsService("invalid");
        invalidService.transport();
    }
}
