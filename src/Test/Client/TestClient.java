package Test.Client;
import Client.Client;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestClient {
    @Test
    @DisplayName("test name client is exist")
    void testName_AddClient(){}

    @Test
    @DisplayName("test address client is exist and have area, street, house number, <= 100 sym.")
    void testAddress_AddClient(){}
}
