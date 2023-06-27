package test;

import baseUrl.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPOJO;
import pojos.BookingdatesPOJO;
import pojos.HerokuappExpBodyPOJO;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Post_Pojo extends HerokuAppBaseURL {

    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
                        Request body
                   {
                        "firstname" : "Ali",
                        "lastname" : “Bak",
                        "totalprice" : 500,
                        "depositpaid" : false,
                        "bookingdates" : {
                                 "checkin" : "2021-06-01",
                                 "checkout" : "2021-06-10"
                                          },
                        "additionalneeds" : "wi-fi"
                    }
                        Response Body = Expected Data
                        {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ali",
                        "lastname":"Bak",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }
         */
    @Test
    public void post01(){

        specHerokuApp.pathParam("pp1","booking");

        BookingdatesPOJO bookingdatesPOJO = new BookingdatesPOJO("2021-06-01","2021-06-10");

        BookingPOJO reqBody = new BookingPOJO("Ali","Bak", 500, false,bookingdatesPOJO,"wi-fi");

        HerokuappExpBodyPOJO expData = new HerokuappExpBodyPOJO(24,reqBody);

        Response response = given()
                                     .spec(specHerokuApp)
                                     .contentType(ContentType.JSON)
                            .when()
                                     .body(reqBody)
                                     .post("/{pp1}");

        response.prettyPrint();

        HerokuappExpBodyPOJO resPojo = response.as(HerokuappExpBodyPOJO.class);

        assertEquals(expData.getBooking().getFirstname(),resPojo.getBooking().getFirstname());
        assertEquals(expData.getBooking().getLastname(),resPojo.getBooking().getLastname());
        assertEquals(expData.getBooking().getTotalprice(),resPojo.getBooking().getTotalprice());
        assertEquals(expData.getBooking().isDepositpaid(),resPojo.getBooking().isDepositpaid());

        assertEquals(expData.getBooking().getBookingdates().getCheckin(),
                resPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expData.getBooking().getBookingdates().getCheckout(),
                resPojo.getBooking().getBookingdates().getCheckout() );

        assertEquals(expData.getBooking().getAdditionalneeds(), resPojo.getBooking().getAdditionalneeds());



    }
}
