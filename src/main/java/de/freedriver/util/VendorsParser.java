package de.freedriver.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.freedriver.models.Vendors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VendorsParser {

    @Autowired
    private JsonParser jsonParser;
    @Autowired
    private ObjectMapper objectMapper;

    public Vendors getAllVendors() throws IOException {
        return objectMapper.readValue(jsonParser, Vendors.class);
    }
}
