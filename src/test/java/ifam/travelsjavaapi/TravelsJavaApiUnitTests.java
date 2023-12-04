package ifam.travelsjavaapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ifam.travelsjavaapi.enumeration.TravelTypeEnum;
import ifam.travelsjavaapi.model.Statistic;
import ifam.travelsjavaapi.model.Travel;
import ifam.travelsjavaapi.service.StatisticService;
import ifam.travelsjavaapi.service.TravelService;


import java.time.ZonedDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TravelsJavaApiUnitTests {

    @Autowired
    private TravelService travelService;

    @Autowired
    private StatisticService statisticService;

    private void printGreen(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m"); // Cor verde ANSI escape code
    }

    @BeforeAll
    public void setUp() {
        travelService.createTravelFactory();
        travelService.createTravelList();
    }

    @Test
    public void shouldReturnNotNullTravelService() {
        assertNotNull(travelService);

        printGreen("Teste executado e concluído com sucesso: shouldReturnNotNullTravelService");
    }

    @Test
    public void shouldReturnNotNullStatisticsService() throws Exception {
        assertNotNull(statisticService);

        printGreen("Teste executado e concluído com sucesso: shouldReturnNotNullTravelService");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnTravelCreatedWithSuccess() throws Exception {

         String startDate = "2019-11-21T09:59:51.312Z";
         String endDate = "2019-12-01T21:08:45.202Z";

        JSONObject jsonTravel = new JSONObject();
        jsonTravel.put("id", 1);
        jsonTravel.put("orderNumber", "220788");
        jsonTravel.put("amount", "22.88");
        jsonTravel.put("type", TravelTypeEnum.RETURN.getValue());
        jsonTravel.put("startDate", startDate);
        jsonTravel.put("endDate", endDate);

        Travel travel = travelService.create(jsonTravel);

        assertNotNull(travel);
//        System.out.println(travel.getId());
        assertEquals(travel.getId().intValue(), jsonTravel.get("id"));
//        System.out.println(travel.getOrderNumber());
        assertEquals(travel.getOrderNumber(), jsonTravel.get("orderNumber"));
//        System.out.println(travel.getAmount());
        assertEquals(travel.getAmount().toString(), jsonTravel.get("amount"));
//        System.out.println(travel.getType());
        assertEquals(travel.getType().toString(), jsonTravel.get("type"));
//        System.out.println(travel.getStartDate());
        assertEquals(travel.getStartDate(), ZonedDateTime.parse(startDate).toLocalDateTime());
//        System.out.println(travel.getEndDate());
        assertEquals(travel.getEndDate(), ZonedDateTime.parse(endDate).toLocalDateTime());

        printGreen("Teste executado e concluído com sucesso: shouldReturnTravelCreatedWithSuccess");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnTravelCreatedInStartDateIsGreaterThanEndDate() throws Exception {

        JSONObject jsonTravel = new JSONObject();
        jsonTravel.put("id", 2);
        jsonTravel.put("orderNumber", "220788");
        jsonTravel.put("amount", "22.88");
        jsonTravel.put("type", TravelTypeEnum.RETURN.getValue());
        jsonTravel.put("startDate", "2020-09-20T09:59:51.312Z");
        jsonTravel.put("endDate", "2020-09-11T09:59:51.312Z");

        Travel travel = travelService.create(jsonTravel);
        boolean travelInFuture = travelService.isStartDateGreatherThanEndDate(travel);

        assertTrue(travelInFuture);

        printGreen("Teste executado e concluído com sucesso: shouldReturnTravelCreatedInStartDateIsGreaterThanEndDate");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnTravelsStatisticsCalculated() throws Exception {

        travelService.delete();

        String startDate = "2019-11-21T09:59:51.312Z";
        String endDate = "2019-12-01T21:08:45.202Z";

        JSONObject jsonTravel220788 = new JSONObject();
        jsonTravel220788.put("id", 1);
        jsonTravel220788.put("orderCode", "220788");
        jsonTravel220788.put("amount", "22.88");
        jsonTravel220788.put("type", TravelTypeEnum.RETURN.getValue());
        jsonTravel220788.put("startDate", startDate);
        jsonTravel220788.put("endDate", endDate);

        Travel travel = travelService.create(jsonTravel220788);
        travelService.add(travel);

        JSONObject jsonTravel300691 = new JSONObject();
        jsonTravel300691.put("id", 2);
        jsonTravel300691.put("orderCode", "300691");
        jsonTravel300691.put("amount", "120.0");
        jsonTravel300691.put("type", TravelTypeEnum.ONE_WAY.getValue());
        jsonTravel300691.put("startDate", startDate);

        travel = travelService.create(jsonTravel300691);
        travelService.add(travel);

        Statistic statistic = statisticService.create(travelService.find());

        assertNotNull(statistic);
        assertEquals("142.88", statistic.getSum().toString());
        assertEquals("71.44", statistic.getAvg().toString());
        assertEquals("22.88", statistic.getMin().toString());
        assertEquals("120.00", statistic.getMax().toString());
        assertEquals(2, statistic.getCount());

        printGreen("Teste executado e concluído com sucesso: shouldReturnTravelsStatisticsCalculated");
    }

    @AfterAll
    public void tearDown() {
        travelService.clearObjects();
    }

}
