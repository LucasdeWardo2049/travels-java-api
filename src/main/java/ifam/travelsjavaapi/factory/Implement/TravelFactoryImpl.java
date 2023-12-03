package ifam.travelsjavaapi.factory.Implement;

import ifam.travelsjavaapi.enumeration.TravelTypeEnum;
import ifam.travelsjavaapi.factory.TravelFactory;
import ifam.travelsjavaapi.model.Travel;

public class TravelFactoryImpl implements TravelFactory {

    @Override
    public Travel createTravel (String type){

        if(TravelTypeEnum.ONE_WAY.getValue().equals(type)){
            return new Travel(TravelTypeEnum.ONE_WAY);
        } else if (TravelTypeEnum.MULTI_CITY.getValue().equals(type)){
            return new Travel(TravelTypeEnum.MULTI_CITY);
        }

        return new Travel(TravelTypeEnum.RETURN);
    }
}
