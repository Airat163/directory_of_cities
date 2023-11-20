package entityes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    private Address address1;
    private Address address2;
    private Address address3;

    @BeforeEach
    void setUp() {
        address1 = new Address("Москва", "Тверская", "10", "5");
        address2 = new Address("Москва", "Тверская", "10", "5");
        address3 = new Address("Саранск", "Советская", "1", "1");
    }

    @Test
    void getCityTest() {
        assertEquals("Москва", address1.getCity());
    }

    @Test
    void getFloorTest() {
        assertEquals("5", address1.getFloor());
    }

    @Test
    void equalsTest() {
        assertEquals(address1, address2);
        assertNotEquals(address1, address3);
    }

    @Test
    void hashCodeTest() {
        assertEquals(address1.hashCode(), address2.hashCode());
        assertNotEquals(address1.hashCode(), address3.hashCode());
    }

    @Test
    void toStringTest() {
        String expected = "город='Москва', улица='Тверская', дом=10, этажность=5";
        assertEquals(expected, address1.toString());
    }
}