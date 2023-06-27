package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlace;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseURL{
     /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    body’e sahip bir PUT request yolladigimizda donen response’in
    response body’sinin asagida verilen ile ayni oldugunu test ediniz

    Request Body

        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }

    Expected Data :

        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
     */

    @Test
    public void put01(){

        // 1 - Url ve request body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlace testDataJsonPlace = new TestDataJsonPlace();

        HashMap <String, Object> reqBody = testDataJsonPlace.requestBodyOlusturMap();

        // 2 - Expected Data hazirla

        HashMap <String, Object> expData = testDataJsonPlace.requestBodyOlusturMap();
//JSOn Object bir Java objesi olmadığı için request'imizi gönderirken request'imizi toString'e çevirmemiz
        // 3 - Response'i kaydet
// gerekiyordu. Ama Map zaten bir Java Objesi olduğu için toString'e çevirmemize gerek yok.Array olduğunda
        Response response = given()// ise array'in içini görmek için toString'i kullanıyoruz.
                                .spec(specJsonPlace)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(reqBody)//put request olduğu için body gönderiyoruz.
                                .put("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4 - Assertion
//Dönen response'in içine girebilmek için daha önce response'i Json Path'e dönüştürüyorduk.
        HashMap<String,Object> respMap = response.as(HashMap.class);
//Şimdi ise HashMap'e dönüştüreceğiz.Çünkü expected data'yı da HasMap olarak hazırladık.
        assertEquals(expData.get("title"), respMap.get("title"));
        assertEquals(expData.get("body"), respMap.get("body"));
        assertEquals(expData.get("userId"), respMap.get("userId"));
        assertEquals(expData.get("id"), respMap.get("id"));

    }

}
