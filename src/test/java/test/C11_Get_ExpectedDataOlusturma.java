package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C11_Get_ExpectedDataOlusturma {

     /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine
    bir GET request yolladigimizda donen response body’sinin
    asagida verilen ile ayni oldugunu test ediniz

   Response body :
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */

    @Test
    public void get01(){

        // 1 - Url hazirla (End point)

        String url = "https://jsonplaceholder.typicode.com/posts/22";

        // 2 - Expected Data hazirla (EXPECTED DATA)
        // Yukarıdaki bilgilerle hazırlanıyor. API ile ilgili değil. Java kullanılıyor.
        JSONObject expData = new JSONObject();

        expData.put("userId",3);
        expData.put("id",22);
        expData.put("title","dolor sint quo a velit explicabo quia nam");
        expData.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        System.out.println("expData = " + expData);

        // 3 - Response'i kaydet (ACTUAL DATA)
        // API response class'ı ile hazırlanıyor.
        Response response = given().when().get(url);

        response.prettyPeek(); //prettyPrintten farkli olarak size response ile ilgili tum degerleri dondurur

        // 4 - Assertion (EXPECTED DATA VS. ACTUAL DATA)
// Dönen response Json formatta olsa da dönen response değerleri direk kullanılamaz.Bunun için dönen response
        JsonPath respJP = response.jsonPath();//body'sini kullanılabilecek metotlarla dönüştürmemiz gerekiyor.
// Yani direk JSon Object olarak değerlendiremiyoruz.
        assertEquals(expData.get("userId"), respJP.get("userId"));
        assertEquals(expData.get("id"), respJP.get("id"));
        assertEquals(expData.get("title"), respJP.get("title"));
        assertEquals(expData.get("body"), respJP.get("body"));



    }
}
