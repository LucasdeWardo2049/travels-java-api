package ifam.travelsjavaapi.factory;

import ifam.travelsjavaapi.model.Travel;

public interface TravelFactory {

    Travel createTravel (String type);
}
