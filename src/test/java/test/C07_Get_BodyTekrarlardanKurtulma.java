package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C07_Get_BodyTekrarlardanKurtulma {

    /*
                https://restful-booker.herokuapp.com/booking/10 url’ine
                bir GET request gonderdigimizde donen Response’un,
                status code’unun 200,
                ve content type’inin application/json; charset=utf-8,
                ve response body’sindeki
                    "firstname“in,"Susan",
                    ve "lastname“in, "Jones",
                    ve "totalprice“in, 792,
                    ve "depositpaid“in,true,
                    ve "additionalneeds“in,"Breakfast"
                oldugunu test edin
         */
    @Test
    public void get01(){

        // 1 - Url hazirla

        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response =given().get(url);

        response.prettyPrint();

        // 4 - Assertion

        /*
        response
                .then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json; charset=utf-8")
                    .body("firstname", Matchers.equalTo("Susan"),
                    "lastname",Matchers.equalTo("Jones"),
                        "totalprice",Matchers.equalTo(792),
                        "depositpaid",Matchers.equalTo(true)
                        "additionalneeds", Matchers.equalTo("Breakfast"));

         */
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("firstname", equalTo("Susan"),
                        "lastname",equalTo("Jones"),
                        "totalprice",equalTo(792),
                        "depositpaid",equalTo(true),
                        "additionalneeds", equalTo("Breakfast"));
//Değerler değiştiği için test geçmiyor. Postman'dan alınıp yapılabilir. Bütün testlerin pass etmesi şart değil.
// Burada önemli olan ve yeni öğrendiğimiz Matchers tekrarından kurtulmak oldu.
    }
}
