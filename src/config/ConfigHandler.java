package config;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigHandler {

    private static final String CONFIG_PATH = "config.yml";

    private static ConfigHandler configHandler;

    Config config;

    /**
     * Load Config to ConfigHandler instance
     * @return ConfigHandler instance with current config
     * @throws FileNotFoundException The config file couldn't be found
     */
    public static ConfigHandler getInstance() throws FileNotFoundException {
        return getInstance(CONFIG_PATH);
    }

    /**
     * Load Config to ConfigHandler instance
     * @param configPath Path to config file
     * @return ConfigHandler instance with current config
     * @throws FileNotFoundException The config file couldn't be found
     */
    public static ConfigHandler getInstance(String configPath) throws FileNotFoundException {
        if(configHandler == null) {
            configHandler = new ConfigHandler(configPath);
        }
        return configHandler;
    }


    private ConfigHandler(String configPath) throws FileNotFoundException {
        this.config = loadConfig(configPath);
    }


    private Config loadConfig(String configPath) throws FileNotFoundException {
        try {
            Constructor constructor = new Constructor(Config.class);
            Yaml yaml = new Yaml(constructor);
            return yaml.load(new FileInputStream(getClass().getResource(configPath).getPath()));
        } catch (NullPointerException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Dump current Config to file
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public void dumpConfig() throws IllegalArgumentException, IOException {
        dumpConfig(this.config, CONFIG_PATH);
    }


    /**
     * Dump current Config to file
     * @param config The config object
     * @param configPath File to write into
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public void dumpConfig(Config config, String configPath) throws IllegalArgumentException, IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yml = new Yaml(options);
        try {
            yml.dump(config, new FileWriter(getClass().getResource(configPath).getPath()));
        } catch (NullPointerException e) {
            throw new IOException();
        }
    }

    /**
     * Getter for Config object
     * @return returns Config object
     */
    public Config getConfig() {
        return this.config;
    }

    /**
     * Fetch a specific session object
     * @param sessionName session name
     * @return the specified session object
     */
    public Session getSession(String sessionName) {
        return this.config.getSession().get(sessionName);
    }

}

