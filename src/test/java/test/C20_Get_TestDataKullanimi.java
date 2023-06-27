package test;

import baseUrl.DummyBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataDummy;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C20_Get_TestDataKullanimi extends DummyBaseURL{
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un status code’unun 200,
    content Type’inin application/json ve body’sinin asagidaki gibi oldugunu test edin.

	Expected Body
    {
    "status":"success",
    "data": {
            "id": 3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    @Test
    public void get01(){

        // 1 - Url hazirla

        specDummy.pathParams("pp1","api","pp2", "v1","pp3","employee","pp4",3);

        // 2 - Expected Data hazirla

        TestDataDummy testDataDummy = new TestDataDummy();

        JSONObject expData = testDataDummy.expectedBodyOlusturJson();

       // 3 - Response'i kaydet

        Response response = given()
                                    .spec(specDummy)
                                    .contentType(ContentType.JSON)
                                .when()
                                    .get("/{pp1}/{pp2}/{pp3}/{pp4}");
        response.prettyPrint();

        // 4 - Assertion

        JsonPath respJP = response.jsonPath();

        assertEquals(expData.get("status"), respJP.get("status"));
        assertEquals(expData.get("message"), respJP.get("message"));

        assertEquals(expData.getJSONObject("data").get("id"), respJP.get("data.id"));
        assertEquals(expData.getJSONObject("data").get("employee_name"), respJP.get("data.employee_name"));
        assertEquals(expData.getJSONObject("data").get("employee_salary"), respJP.get("data.employee_salary"));
        assertEquals(expData.getJSONObject("data").get("employee_age"), respJP.get("data.employee_age"));
        assertEquals(expData.getJSONObject("data").get("profile_image"), respJP.get("data.profile_image"));










    }




}
