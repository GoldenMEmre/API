package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseURL {

    protected RequestSpecification specJsonPlace;//Child class'lardan ulaşabilmek için protected yaptık.

    @Before
    public void setUp(){

        specJsonPlace = new RequestSpecBuilder()
                                    .setBaseUri("https://jsonplaceholder.typicode.com")
                                    .build();
    }
}
