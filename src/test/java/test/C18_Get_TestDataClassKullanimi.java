package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataJsonPlace;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C18_Get_TestDataClassKullanimi extends JsonPlaceHolderBaseURL{

    /*

    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
    request yolladigimizda donen response’in status kodunun 200 ve
    response body’sinin asagida verilen ile ayni oldugunu test ediniz

    Response body = Expected Body
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */

    @Test
    public void get01(){

        // 1 - Url hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",22);

        // 2- Expected Data hazirla
        //Static yaptığımızda herkes son veriyi değiştirebilir. Bu yüzden burada yapmıyoruz.
        TestDataJsonPlace testDataJsonPlace = new TestDataJsonPlace();
//Method Call tek başına yeterli olmadığından aşağıda olduğu gibi bir atama yapmamız gerekiyor.
        JSONObject expData =testDataJsonPlace.expectedBodyOlusturJSON();//method TestData package'dan geliyor.
//Method JSON Object döndürdüğü için atama da JSON Object'e yapılıyor//Eşitliğin sağ tarafı method call
        // 3 - Response'i kaydet

        Response response= given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4 - Assertion
//JUnit'le assert etmek için JSON Object'i jsonPath'e çevirmemiz gerekiyor. JSON Object'i direk kullanamıyoz
        JsonPath respJP = response.jsonPath();
// AssertThat'ten sonra statusCode() kullanılıyor. Burada getStatusCode() kullanılıyor çünkü direk ulaşmak
        assertEquals(testDataJsonPlace.basariliStatusCode,response.getStatusCode());//istiyoruz.

        assertEquals(expData.get("userId"),respJP.get("userId"));
        assertEquals(expData.get("id"),respJP.get("id"));
        assertEquals(expData.get("title"),respJP.get("title"));
        assertEquals(expData.get("body"),respJP.get("body"));

    }



}
