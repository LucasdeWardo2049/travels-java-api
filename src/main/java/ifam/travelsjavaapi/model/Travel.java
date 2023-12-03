package ifam.travelsjavaapi.model;

import ifam.travelsjavaapi.enumeration.TravelTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Travel {

    private Long id;
    private String orderNumber;
    private BigDecimal amount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private TravelTypeEnum type;

    public Travel(TravelTypeEnum type){
        this.type = type;
    }

}

