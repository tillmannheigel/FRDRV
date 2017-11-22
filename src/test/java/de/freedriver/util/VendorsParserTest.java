package de.freedriver.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.freedriver.models.Vendors;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class VendorsParserTest extends Mockito {

    private static final String VENDORS_JSON = "vendors.json";
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @InjectMocks
    private VendorsParser vendorsParser;
    @Mock
    private JsonParser jsonParser;
    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void getAllVendorsForZeroVendors() throws Exception {
        // given
        //when(objectMapper.readValues(jsonParser, Vendors.class)).thenReturn(null);

        // when
        Vendors vendors = vendorsParser.getAllVendors();

        // then
        Assert.assertNull(vendors);
    }

    @Test
    @Ignore
    public void getAllVendorsThrowsIOException() throws IOException {
        // given
        IOException ioException = mock(IOException.class);
        when(objectMapper.readValues(jsonParser, Vendors.class)).thenThrow(ioException);

        // when
        Vendors vendors = vendorsParser.getAllVendors();

        // then
        exception.expect(IOException.class);
    }
}