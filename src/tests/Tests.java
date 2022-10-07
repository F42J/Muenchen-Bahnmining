package tests;

import config.ConfigHandler;
import config.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Tests {

    @Test
    void checkConfigLoad() {
        try {
            ConfigHandler config = ConfigHandler.getInstance();
            Session api = config.getSession("Timetable");
            assertTrue(api.getSecret().equals("") && api.getSecret() != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assertions.fail("File error");
        }
    }
}
