import config.ConfigHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;

public class Main {
    private static final Logger logger = LogManager.getLogger("Muenchen Bahnmining");
    private static ConfigHandler config;




        public static void main (String[] args){
            try {
                logger.info("Loading Configuration File...");
                config=ConfigHandler.getInstance();
            } catch (FileNotFoundException e) {
                logger.fatal("Failed to load configuration");
            }
        }
    }

