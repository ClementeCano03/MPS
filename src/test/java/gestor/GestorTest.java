
package gestor;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GestorTest {
    GestorSensores gestor;
    @BeforeEach
    void setUp() {
        // code to setup the test
        gestor = new GestorSensores();
    }

    @Test
    @DisplayName("Testing size of sensors")
    void TestSize(){
        // code to test the size of sensors
        String sensor = "Hola";

        gestor.agregarSensor(sensor);

        assert(gestor.getNumeroSensores() == 1);
    }

    @Test 
    @DisplayName("Testing adding a sensor")
    void TestAddNotNullSensor(){
        // code to test adding a sensor
        String sensor = "Prueba";
        int n = gestor.getNumeroSensores();

        gestor.agregarSensor(sensor);

        assert(gestor.getNumeroSensores() > n);
    }

    @Test
    @DisplayName("Testing adding a repeated sensor")
    void TestAddRepeatedSensor(){
        // code to test adding a repeated sensor
        String sensor = "Prueba";

        gestor.agregarSensor(sensor);

        assertThrows(IllegalArgumentException.class, () -> gestor.agregarSensor(sensor));
    }

    @Test
    @DisplayName("Testing deleting an existing sensor")
    void TestDeleteExistingSensor(){
        // code to test deleting an existing sensor
        String sensor = "Prueba";

        gestor.agregarSensor(sensor);

        assert(gestor.borrarSensor(sensor));
    }

    @Test
    @DisplayName("Testing deleting a non-existing sensor")
    void TestDeleteNonExistingSensor(){
        // code to test deleting a non-existing sensor
        String sensor = "Prueba";

        assertThrows(IllegalArgumentException.class, () -> gestor.borrarSensor(sensor));
    }
}