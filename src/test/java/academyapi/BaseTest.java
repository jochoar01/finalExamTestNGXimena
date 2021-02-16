package academyapi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class BaseTest {
    public static Logger log = LogManager.getLogger(TestApi.class);
    protected static Response response;
    protected static final String url = "https://6029ac37289eb50017cf7d7a.mockapi.io/testing/finalExam/Bank";

    public static Response getApi(){
        response=given().get(url);
        return response;
    }
    public static Response getApi(String id){
        response=given().get(url+"/"+id);
        return response;
    }

    public static Response deleteApi(String id){
        response=given().delete(url+"/"+id);
        return response;
    }

    public static Response postApi(Map<String, String> Users){
        response = given().body(Users).contentType("application/json").when().post(url);
        return response;
    }
    public static Response putApi(Map<String, String> Users,String id){
        response = given().body(Users).contentType("application/json").when().put(url+"/"+id);
        return response;
    }

    @BeforeMethod
    public static void cleaner(){

        Response response = getApi();
        assertEquals(response.getStatusCode(), 200);
        ResponseBody body = response.body();
        List<Map<String, Object>> users = body.as(new TypeRef<List<Map<String, Object>>>() {
        });
        log.info("statusCode: " + response);
        if (users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                Response responseDelete = deleteApi(String.valueOf(users.get(i).get("id")));
                assertEquals(200, responseDelete.getStatusCode());
            }
        }
    }




}
