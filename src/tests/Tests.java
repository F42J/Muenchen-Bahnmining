package tests;

import API.APIHandler;
import config.ConfigHandler;
import config.Session;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class Tests {

    @Test
    void checkConfigLoad() {
        try {
            ConfigHandler config = ConfigHandler.getInstance();
            Session api = config.getSession("API.Timetable");
            assertTrue(api.getSecret().equals("") && api.getSecret() != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("File error");
        }
    }

    @Test
    void checkFetchPlan() {
        try {
            ConfigHandler config=ConfigHandler.getInstance();
            APIHandler handler =new APIHandler(config.getSession("Timetable").getHost(),config.getSession("Timetable").getUser(), config.getSession("Timetable").getSecret());
            handler.fetchPlan(8006671,221008, 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
}
