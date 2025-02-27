package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piloto.exception.PilotoInvalidoException;
import piloto.model.Piloto;

import java.util.UUID;

public class PilotoUnitTest {

    @Test
    public void instanciar_atributosCorrectos_instancia() {
        Piloto piloto = Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        Assertions.assertNotNull(piloto);
    }

    @Test
    public void instanciar_nombreIncorrecto_exception() {
        Exception exceptionVacio = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "", "Páez", "Ignacio Páez", "PAE", "https://..."));
        Exception exceptionNulo = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), null, "Páez", "Ignacio Páez", "PAE", "https://..."));
        Exception exceptionBlank = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), " ", "Páez", "Ignacio Páez", "PAE", "https://..."));
        Assertions.assertEquals("Nombre no válido", exceptionVacio.getMessage());
        Assertions.assertEquals("Nombre no válido", exceptionNulo.getMessage());
        Assertions.assertEquals("Nombre no válido", exceptionBlank.getMessage());
    }

    @Test
    public void instanciar_apellidoIncorrecto_exception() {
        Exception exceptionVacio = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "", "Ignacio Páez", "PAE", "https://..."));
        Exception exceptionNulo = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", null, "Ignacio Páez","PAE", "https://..."));
        Exception exceptionBlank = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", " ", "Ignacio Páez", "PAE", "https://..."));
        Assertions.assertEquals("Apellido no válido", exceptionVacio.getMessage());
        Assertions.assertEquals("Apellido no válido", exceptionNulo.getMessage());
        Assertions.assertEquals("Apellido no válido", exceptionBlank.getMessage());
    }

    @Test
    public void instanciar_nombreCompletoIncorrecto_exception() {
        Exception exceptionVacio = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "", "PAE", "https://..."));
        Exception exceptionNulo = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", null,"PAE", "https://..."));
        Exception exceptionBlank = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", " ", "PAE", "https://..."));
        Assertions.assertEquals("Nombre completo no válido", exceptionVacio.getMessage());
        Assertions.assertEquals("Nombre completo no válido", exceptionNulo.getMessage());
        Assertions.assertEquals("Nombre completo no válido", exceptionBlank.getMessage());
    }

    @Test
    public void instanciar_nombreAbreviadoIncorrecto_exception() {
        Exception exceptionVacio = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "Ignacio Páez", "", "https://..."));
        Exception exceptionNulo = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "Ignacio Páez", null, "https://..."));
        Exception exceptionBlank = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "Ignacio Páez", " ", "https://..."));
        Assertions.assertEquals("Nombre abreviado no válido", exceptionVacio.getMessage());
        Assertions.assertEquals("Nombre abreviado no válido", exceptionNulo.getMessage());
        Assertions.assertEquals("Nombre abreviado no válido", exceptionBlank.getMessage());
    }

    @Test
    public void instanciar_urlImagenIncorrecto_exception() {
        Exception exceptionVacio = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "Ignacio Páez", "PAE", ""));
        Exception exceptionNulo = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "Ignacio Páez", "PAE", null));
        Exception exceptionBlank = Assertions.assertThrows(PilotoInvalidoException.class, () -> Piloto.factory(UUID.randomUUID(), "Ignacio", "Páez", "Ignacio Páez", "PAE", " "));
        Assertions.assertEquals("URL de imagen no válida", exceptionVacio.getMessage());
        Assertions.assertEquals("URL de imagen no válida", exceptionNulo.getMessage());
        Assertions.assertEquals("URL de imagen no válida", exceptionBlank.getMessage());
    }
}
