package academyapi;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Base Project TestNG + Rest Assured
 * Core API Methods
 *
 * @author Brayhan.Criollo
 */
public class CoreAPI {

    private static Map<String, String> userMap;
    private Logger log = LogManager.getLogger(CoreAPI.class);
    ;

    public Map<String, String> createUsersMap(Users user) {
        userMap = new HashMap<String, String>();
        userMap.put("first_name", user.getFirstName());
        userMap.put("last_name", user.getLastName());
        userMap.put("email", user.getEmail());
        userMap.put("country", user.getCountry());
        userMap.put("telephone", user.getTelephone());
        userMap.put("active", user.getActive().toString());
        userMap.put("job_title", user.getJobTitle());
        return userMap;
    }

    public boolean emailExists(List<Map<String, Object>> apiMap, Users usersPojo) {
        boolean exists = false;

        if (apiMap.size() > 0) {
            for (int i = 0; i < apiMap.size(); i++) {

                if (apiMap.get(i).get("email").equals(usersPojo.getEmail())) {
                    exists = true;
                } else {

                    exists = false;
                }

            }

        }
        return exists;
    }
}