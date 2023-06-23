package test;

import baseUrl.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_TestDataKullanimi extends HerokuAppBaseURL{

    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde donen response’un status kodunu ve id haric
    body'sinin asagidaki gibi oldugunu test edin.

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

        Expected Body
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
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */

    @Test
    public void post01(){

        // 1 - Url ve Request Body hazirla

        specHerokuApp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp = new TestDataHerokuapp();

        JSONObject reqBody = testDataHerokuapp.bookingOlusturJSON();

        //2 - Expected Data hazirla

        JSONObject expData = testDataHerokuapp.expectedBodyOlusturJSON();

        // 3 - Response'i kaydet

        Response response = given()
                                .spec(specHerokuApp)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(reqBody.toString())
                                .post("/{pp1}");

        response.prettyPrint();

        // 4 - Assertion
//12. class ile aynı.
        JsonPath resJP = response.jsonPath();
//Burada response'in body'sini direk çağıramadığımız için yani response.body dediğimizde, bu body'nin içine herhangi bir
        assertEquals(testDataHerokuapp.basariliStatusCode,response.getStatusCode());
//değer girdiğimizde hata veriyor ve buraya herhangi bir arguman giremezsin diyordu. Onun için response'i jsonPath formatına çeviriyoruz.
        assertEquals(expData.getJSONObject("booking").get("firstname"),resJP.get("booking.firstname"));
        assertEquals(expData.getJSONObject("booking").get("lastname"),resJP.get("booking.lastname"));
        assertEquals(expData.getJSONObject("booking").get("totalprice"),resJP.get("booking.totalprice"));
        assertEquals(expData.getJSONObject("booking").get("depositpaid"),resJP.get("booking.depositpaid"));
        assertEquals(expData.getJSONObject("booking").get("additionalneeds"),resJP.get("booking.additionalneeds"));
        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                                    resJP.get("booking.bookingdates.checkin"));
        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                                    resJP.get("booking.bookingdates.checkout"));

    }//response.jsonPath olmadanokuyamıyoruz. Response üzerinden üzerinden yaptığımız sorguları jsonPath olmadan nasıl
//yapıyoruz? Assertions'ların içinde response'imizin body'sine girmek istiyorsak, response'in body'si salt bir şekilde
}// gelmiyor.O yüzden JsonPath'e dönüştürmek zorundayız.
// response.then().assertThat().body() ---> burada response'in kendi method'unu kullandigimiz icin bunu extra JsonPath'e
//dönüştürmemize gerek kalmıyor. Yani obje üzerinden gitmiyoruz. Örneğin C09'da Assertion kısmında da JsonPath olarak
// kullanıyoruz. Doğrudan firstname'e giremiyoruz booking. yapmamız gerekiyor.Ama başka (JUnit veya TestNG) bir assert'i
//kullanacaksak o zaman RESPONSE OBJESİNİ JSONPATH OBJESİNE DÖNÜŞTÜRÜP O FORMAT ÜZERİNDEN BODY DEĞERLERİNE ULAŞABİLİ-
//YORUM. Yoksa C09 Assertion'da da JSon mantığını kullanıyoruz, ama kendi metotlarının içinde yapacağımız için ayrıyeten
// bir de dönüştürmemize gerek kalmıyor.HANGİSİNİ İSTERSEK ONU KULLANABİLİRİZ. AMA SYNTAX'E DİKKAT EDEREK.