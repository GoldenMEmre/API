package testData;

import org.json.JSONObject;

public class TestDataJsonPlace {
// Status Code, Content Type, Header değeri gibi response'i temel bilgileri class level'da  instance variable
    public int basariliStatusCode = 200;//olarak tanımlayabilir ve bunlar üzerinden çağırabiliriz.
// public yazmasaydık access modifier "default" olacağından başka bir class'dan obje oluşturarak erişemeyiz.
   public String contentType = "application/json; charset=utf-8";
    public String connectionHeaderDegeri = "keep-alive";
    public JSONObject expectedBodyOlusturJSON(){

        JSONObject expData = new JSONObject();

        expData.put("userId",3);
        expData.put("id",22);
        expData.put("title","dolor sint quo a velit explicabo quia nam");
        expData.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        return expData;
    }

    public JSONObject requestBodyOlusturJSON(){

         /*
        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
         */

        JSONObject reqBody= new JSONObject();

        reqBody.put("title","Ali");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);

        return reqBody;
    }
/*
    public HashMap requestBodyOlusturma(){

        Map<String,Object> reqBody = new HashMap<>();

        reqBody.put("title","Ahmet");
        reqBody.put("body","Ali");
        reqBody.put("title","Ali");
        reqBody.put("title","Ali");
*/
    }



