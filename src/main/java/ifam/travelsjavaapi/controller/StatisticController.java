package ifam.travelsjavaapi.controller;

import ifam.travelsjavaapi.model.Statistic;
import ifam.travelsjavaapi.model.Travel;
import ifam.travelsjavaapi.service.StatisticService;
import ifam.travelsjavaapi.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api-travels/statistics")
public class StatisticController {

    private static final Logger logger = Logger.getLogger(String.valueOf(StatisticController.class));

    @Autowired
    private TravelService travelService;

    @Autowired
    private StatisticService statisticsService;

    @GetMapping(produces = { "application/json" })
    public ResponseEntity<Statistic> getStatistics(){

        List<Travel> travels = travelService.find();
        Statistic statistics = statisticsService.create(travels);

        logger.info(statistics.toString());

        return ResponseEntity.ok(statistics);
    }

}
