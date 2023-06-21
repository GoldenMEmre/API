package test;

import baseUrl.HerokuAppBaseURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends HerokuAppBaseURL{
    /*
    Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
    1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
    gonderdigimizde donen response’un status code’unun 200 oldugunu ve
    Response’ta 12 booking oldugunu test edin

    2- https://restful-booker.herokuapp.com/booking
    endpointine asagidaki body’ye sahip bir POST
    request gonderdigimizde donen response’un
    status code’unun 200 oldugunu ve “firstname”
    degerinin “Ahmet” oldugunu test edin

{
"firstname" : "Ahmet",
"lastname" : “Bulut",
"totalprice" : 500,
"depositpaid" : false,
"bookingdates" : {
    "checkin" : "2021-06-01",
    "checkout" : "2021-06-10"
},
"additionalneeds" : "wi-fi"
}

     */

    @Test
    public void get01(){

        /*
        1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
    gonderdigimizde donen response’un status code’unun 200 oldugunu ve
    Response’ta 12 booking oldugunu test edin
         */

        // Url hazirla

        specHerokuApp.pathParam("pp1","booking");

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given().spec(specHerokuApp).when().get("/{pp1}");

        response.prettyPrint();





    }
}
