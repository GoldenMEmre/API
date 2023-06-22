package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookingPOJO {

    private String firstname;

    private String lastname;

    private int totalprice;

    private boolean depositpaid;

    private BookingdatesPOJO bookingdates;

    private String additionalneeds;

}
