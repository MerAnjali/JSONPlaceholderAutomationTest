package config;

import common.MyLogger;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class configProvider {
    InputStream iStream;
    public static String BASE_URL;
    public static String ENDPOINT_USERS;
    public static String ENDPOINT_POSTS;
    public static String ENDPOINT_COMMENTS;
    public static String ENDPOINT_ALBUMS;
    public static String ENDPOINT_PHOTOS;
    public static String ENDPOINT_TODOS;
    private final Logger log = MyLogger.log;

    public configProvider() {
        String configFilePath = "config.properties";
        try {
            Properties props = this.getPropValues(configFilePath);
            configProvider.BASE_URL = props.getProperty("BASE_URL");
            configProvider.ENDPOINT_USERS = props.getProperty("ENDPOINT_USERS");
            configProvider.ENDPOINT_POSTS = props.getProperty("ENDPOINT_POSTS");
            configProvider.ENDPOINT_COMMENTS = props.getProperty("ENDPOINT_COMMENTS");
            configProvider.ENDPOINT_ALBUMS = props.getProperty("ENDPOINT_ALBUMS");
            configProvider.ENDPOINT_PHOTOS = props.getProperty("ENDPOINT_PHOTOS");
            configProvider.ENDPOINT_TODOS = props.getProperty("ENDPOINT_TODOS");

        } catch (IOException exc) {
            log.info("Could not read configuration file");
        }
    }

    public Properties getPropValues(String configFilePath) throws IOException {
        Properties prop = new Properties();
        try {
            iStream = getClass().getClassLoader().getResourceAsStream(configFilePath);
            if (iStream != null) {
                prop.load(iStream);
            } else {
                throw new FileNotFoundException("property file '" + configFilePath + "' not found in the classpath");
            }
        } catch (Exception e) {
            log.error("Exception: " + e);
        } finally {
            iStream.close();
        }
        return prop;
    }
}
