package tests;

import API.APIHandler;
import API.SQLConnector;
import config.ConfigHandler;
import config.Session;
import org.jdom.Document;
import org.jdom.Element;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class Tests {

    @Test
    void checkConfigLoad() {
        try {
            ConfigHandler config = ConfigHandler.getInstance();
            Session api = config.getSession("Timetable");
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
            System.out.println(handler.fetchPlan(8006671,221010, 20));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void testDB() {
        new SQLConnector();
        assert(true);
    }

    @Test
    void testPlanData() {
        try {
            SQLConnector sql = new SQLConnector();
            ConfigHandler config=ConfigHandler.getInstance();
            APIHandler handler =new APIHandler(config.getSession("Timetable").getHost(),config.getSession("Timetable").getUser(), config.getSession("Timetable").getSecret());
            Document doc= handler.fetchPlan(8006671,221010, 20);
            Element timetable=doc.getRootElement();
            List<Element> stops=timetable.getChildren();
            for (Element stop:stops) {
                sql.insertPlannedStop(timetable.getAttribute("station").getValue(),stop);
            }
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
