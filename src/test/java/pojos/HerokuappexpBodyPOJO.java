package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HerokuappexpBodyPOJO {

    private int bookingid;

    private BookingPOJO booking;
}
