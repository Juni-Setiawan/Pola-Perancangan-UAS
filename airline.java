import java.util.ArrayList;
import java.util.List;

class AirlineSystem {
    // ... (unchanged)
}

class MoneyTrackerManager {
    private List<AirlineSystem> airlineSystemList;

    public MoneyTrackerManager() {
        this.airlineSystemList = new ArrayList<>();
    }

    public void addAirlineSystem(AirlineSystem airlineSystem) {
        airlineSystemList.add(airlineSystem);
    }

    public List<AirlineSystem> getAirlineSystemList() {
        return airlineSystemList;
    }
}

// Rest of the code remains unchanged
