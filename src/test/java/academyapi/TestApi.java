package academyapi;

import Data.DataGenerator;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static org.testng.AssertJUnit.assertEquals;

public class TestApi extends BaseTest {

    public static Logger log = LogManager.getLogger(TestApi.class);


    @Test(priority = 1)
    public static void VerifyEndpointDelete() {
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


        assertEquals(0, getApi().getBody().as(new TypeRef<List<Map<String, Object>>>() {
        }).size());
    }

    @Test(priority = 2)
    public static void initializePoJoAndSendPost() {
        DataGenerator data = new DataGenerator();
        CoreAPI core = new CoreAPI();
        ArrayList<Users> initializePojoList = data.usersGenerator(1);
        Response response = getApi();
        assertEquals(response.getStatusCode(), 200);
        ResponseBody body = response.body();
        List<Map<String, Object>> usersApiMap = body.as(new TypeRef<List<Map<String, Object>>>() {
        });
        for (Users pojo : initializePojoList) {
            if (usersApiMap.size() < 0) {
                for (int i = 0; i < usersApiMap.size(); i++) {
                    if (usersApiMap.get(i).get("email").equals(pojo.getEmail())) {
                        log.info("user exists - statusCode: " + response);
                    } else {
                        Response responsePost = postApi(core.createUsersMap(pojo));
                        assertEquals(201, responsePost.getStatusCode());
                    }
                }
            } else {
                Response responsePost = postApi(core.createUsersMap(pojo));
                assertEquals(201, responsePost.getStatusCode());

            }


        }


    }

    @Test(priority = 3)
    public static void createUserAlreadyExist() {


        DataGenerator data = new DataGenerator();
        CoreAPI core = new CoreAPI();
        Users initializePojo = data.usersGenerator(1).get(0);
        Map<String, String> mapForPost = core.createUsersMap(initializePojo);
        postApi(mapForPost);
        Response response = getApi();
        ResponseBody bodyApi = response.body();
        assertEquals(200, response.getStatusCode());
        List<Map<String, Object>> usersResponseApi = bodyApi.as(new TypeRef<List<Map<String, Object>>>() {});
        boolean exists = core.emailExists(usersResponseApi, initializePojo);

        if (exists) {
            log.info("The email already exists");
        } else {

            log.info("The email don't exists");
        }


    }

    @Test(priority = 4)
    public static void updateUserAlreadyExist() {
        DataGenerator data = new DataGenerator();
        CoreAPI core = new CoreAPI();
        Users initializePojo = data.usersGenerator(1).get(0);
        Map<String, String> mapForPost = core.createUsersMap(initializePojo);
        postApi(mapForPost);
        Users updatePojo = data.usersGenerator(1).get(0);
        Map<String, String> mapForPut = core.createUsersMap(updatePojo);
        Response putResponse = putApi(mapForPut, "1");
        assertEquals(200, putResponse.getStatusCode());



    }











}
