package util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Vendors;

import java.io.File;
import java.io.IOException;

/**
 * Created by ou on 07.07.17.
 */
public class VendorsParser {

    public Vendors getAllVendors() {
        JsonParser parser = null;
        try {
            parser = getJsonParser("vendors.json");
        } catch (IOException io) {
            System.err.print("Error Parsing vendors.json");
            io.printStackTrace();
        }

        Vendors vendors = null;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            vendors = objectMapper.readValue(parser, Vendors.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vendors;
    }

    private JsonParser getJsonParser(String filename) throws IOException {
        JsonFactory factory = new JsonFactory();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        JsonParser parser = factory.createJsonParser(file);
        return parser;
    }
}
