package com.api;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertyReader {
    private String result = "";
    private InputStream inputStream;

    private final static Logger logger = Logger.getLogger(PropertyReader.class);

    public String getPropValue(String property) {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result = prop.getProperty(property);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                if(inputStream != null)
                {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return result;
    }
}
