package ifam.travelsjavaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ifam.travelsjavaapi.enumeration.TravelTypeEnum;
import ifam.travelsjavaapi.factory.Implement.TravelFactoryImpl;
import ifam.travelsjavaapi.factory.TravelFactory;
import ifam.travelsjavaapi.model.Travel;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService {

    private TravelFactory factory;
    private List<Travel> travels;

    // Cria uma instância da TravelFactory se ela ainda não foi inicializada
    public void createTravelFactory(){
        if(factory == null){
            factory = new TravelFactoryImpl();
            System.out.println("Factory: " + factory);
        }
    }

    // Cria uma instância de 'ArrayList' para armazenar as viagens ('travels') se essa lista ainda não foi inicializada
    public void createTravelList(){
        if(travels == null){
            travels = new ArrayList<>();
        }
    }

    // Verifica se uma string JSON é válida utilizando o a biblioteca Jackson ('ObjectMapper')
    public boolean isJSONValid(String jsonInString){
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e){
            return false;
        }
    }

    private long parseId(JSONObject travel){
        return Long.valueOf((int) travel.get("id"));
    }

    private BigDecimal parseAmount(JSONObject travel){
        return new BigDecimal((String) travel.get("amount"));
    }

    private LocalDateTime parseStartDate(JSONObject travel){
        String startDate = null;
        startDate = (String) travel.get("startDate");
        return ZonedDateTime.parse(startDate).toLocalDateTime();
    }

    private LocalDateTime parseEndDate(JSONObject travel){
        String endDate = null;
        endDate = (String) travel.get("startDate");
        return ZonedDateTime.parse(endDate).toLocalDateTime();
    }

    public boolean isStartDateGreatherThanEndDate(Travel travel){
        if(travel.getEndDate() == null) return false;
        return travel.getStartDate().isAfter(travel.getEndDate());
    }

    private void setTravelValues(JSONObject jsonTravel, Travel travel){
        String orderNumber = (String) jsonTravel.get("orderNumber");
        String type = (String) jsonTravel.get("type");

        travel.setOrderNumber(orderNumber != null ? orderNumber : travel.getOrderNumber());
        travel.setAmount(jsonTravel.get("amount") != null ? parseAmount(jsonTravel) : travel.getAmount());
        travel.setStartDate(jsonTravel.get("startDate") != null ? parseStartDate(jsonTravel) : travel.getStartDate());
        travel.setEndDate(jsonTravel.get("endDate") != null ? parseEndDate(jsonTravel) : travel.getEndDate());
        travel.setType(type != null ? TravelTypeEnum.getEnum(type) : travel.getType());
    }


    public Travel create(JSONObject jsonTravel) throws JSONException {
        System.out.println("Type: " + jsonTravel.get("endDate"));
        createTravelFactory();

        Travel travel = factory.createTravel((String) jsonTravel.get("type"));
        System.out.println("Travel: " + travel);
        travel.setId(parseId(jsonTravel));
        setTravelValues(jsonTravel, travel);

        return travel;
    }

    public Travel update(Travel travel, JSONObject jsonTravel) throws JSONException {

        setTravelValues(jsonTravel, travel);
        return travel;
    }

    public void add(Travel travel){
        createTravelList();
        travels.add(travel);
    }

    public List<Travel> find(){
        createTravelList();
        return travels;
    }

    public Travel findById(long id){
        return travels.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
    }

    public void delete(){
        travels.clear();
    }

    public void clearObjects(){
        travels = null;
        factory = null;
    }

}
