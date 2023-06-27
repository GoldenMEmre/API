package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//getter, setter, toString
@NoArgsConstructor//parametresiz constructor
@AllArgsConstructor//tüm parametreleri içeren constructor
public class BookingdatesPOJO {
    /*
    {
                                 "checkin" : "2021-06-01",
                                 "checkout" : "2021-06-10"
                                         }
     */

    private String checkin;
    private String checkout;
}
