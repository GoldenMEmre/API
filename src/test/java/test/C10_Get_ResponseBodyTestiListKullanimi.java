package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C10_Get_ResponseBodyTestiListKullanimi {

    /*
            http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
            donen Response'in
            status code'unun 200,
            ve content type'inin application/json,
            ve response body'sindeki
                employees sayisinin 24
                ve employee'lerden birinin "Ashton Cox"
                ve girilen yaslar icinde 61,40 ve 30 degerlerinin oldugunu test edin
            test edin.
     */
//Rquest body oluşturma zorunluluğu Post,Put ve Patch için geçerli. Delete ve Get'te zorunlu değil.
    @Test
    public void get01(){

        // 1 - Url hazirla

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given().when().get(url);//Body göndermediğimiz için precondition yok.Body gönderseydik
        //Content Type JSon göndermek zorundaydık.Body'yi Json Object olarak gönderdiğimiz için
        response.prettyPrint();//(Json object bir Java elemanı olmadığından) ToString'le gönderiyoruz.

        // 4 - Assertion (TEST AŞAMASI)

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id", hasSize(24),//data=key, id=key 0 1...24=value, size, index=0...
                    "data.employee_name",hasItem("Ashton Cox"),
                        "data.employee_age",hasItems(61,30,40));


    }
}
