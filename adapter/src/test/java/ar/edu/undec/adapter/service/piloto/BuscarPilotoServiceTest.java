package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.service.piloto.controller.BuscarPilotoController;
import ar.edu.undec.adapter.service.piloto.dto.PilotoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import piloto.input.BuscarPilotoInput;
import piloto.model.Piloto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPilotoServiceTest {

    @Mock
    private BuscarPilotoInput buscarPilotoInput;

    @InjectMocks
    private BuscarPilotoController buscarPilotoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPilotoPorId_pilotoEncontrado_returnPiloto() {
        UUID id = UUID.randomUUID();
        Piloto piloto = Piloto.factory(id, "Max", "Verstappen", "Max Verstappen", "VER", "https://ejemplo.com/ver.jpg");
        when(buscarPilotoInput.buscarPilotoPorId(id)).thenReturn(piloto);
        ResponseEntity<?> response = buscarPilotoController.buscarPilotoPorId(id.toString());
        assertEquals(200, response.getStatusCodeValue());
        PilotoDto pilotoDto = (PilotoDto) response.getBody();
        assertEquals("Max", pilotoDto.getName());
        assertEquals("Verstappen", pilotoDto.getSurname());
        assertEquals("VER", pilotoDto.getShortName());
    }

    @Test
    void buscarPilotoPorId_pilotoNoEncontrado_returnHTTP404() {
        UUID id = UUID.randomUUID();
        when(buscarPilotoInput.buscarPilotoPorId(id)).thenThrow(new RuntimeException("El piloto no existe."));
        ResponseEntity<?> response = buscarPilotoController.buscarPilotoPorId(id.toString());
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("El piloto no existe.", response.getBody());
    }

    @Test
    void buscarPilotoPorNombreCompleto_pilotoEncontrado_returnPiloto() {
        String fullName = "Lewis Hamilton";
        Piloto piloto = Piloto.factory(UUID.randomUUID(), "Lewis", "Hamilton", fullName, "HAM", "https://ejemplo.com/ham.jpg");
        when(buscarPilotoInput.buscarPilotoPorNombreCompleto(fullName)).thenReturn(piloto);
        ResponseEntity<?> response = buscarPilotoController.buscarPilotoPorNombreCompleto(fullName);
        assertEquals(200, response.getStatusCodeValue());
        PilotoDto pilotoDto = (PilotoDto) response.getBody();
        assertEquals("Lewis", pilotoDto.getName());
        assertEquals("Hamilton", pilotoDto.getSurname());
    }

    @Test
    void buscarPilotoPorNombreCompleto_pilotoNoEncontrado_returnHTTP404() {
        String fullName = "Sebastian Vettel";
        when(buscarPilotoInput.buscarPilotoPorNombreCompleto(fullName)).thenThrow(new RuntimeException("El piloto no existe."));
        ResponseEntity<?> response = buscarPilotoController.buscarPilotoPorNombreCompleto(fullName);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("El piloto no existe.", response.getBody());
    }

    @Test
    void buscarPilotoPorAbreviatura_pilotoEncontrado_returnPiloto() {
        String shortName = "LEC";
        Piloto piloto = Piloto.factory(UUID.randomUUID(), "Charles", "Leclerc", "Charles Leclerc", shortName, "https://ejemplo.com/lec.jpg");
        when(buscarPilotoInput.buscarPilotoPorAbreviatura(shortName)).thenReturn(piloto);
        ResponseEntity<?> response = buscarPilotoController.buscarPilotoPorAbreviatura(shortName);
        assertEquals(200, response.getStatusCodeValue());
        PilotoDto pilotoDto = (PilotoDto) response.getBody();
        assertEquals("Charles", pilotoDto.getName());
        assertEquals("Leclerc", pilotoDto.getSurname());
    }

    @Test
    void buscarPilotoPorAbreviatura_pilotoNoEncontrado_returnHTTP404() {
        String shortName = "ALO";
        when(buscarPilotoInput.buscarPilotoPorAbreviatura(shortName)).thenThrow(new RuntimeException("El piloto no existe."));
        ResponseEntity<?> response = buscarPilotoController.buscarPilotoPorAbreviatura(shortName);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("El piloto no existe.", response.getBody());
    }

    @Test
    void buscarPilotos_pilotoEncontrado_returnListaDePilotos() {
        List<Piloto> pilotos = Arrays.asList(
                Piloto.factory(UUID.randomUUID(), "Fernando", "Alonso", "Fernando Alonso", "ALO", "https://ejemplo.com/alo.jpg"),
                Piloto.factory(UUID.randomUUID(), "Carlos", "Sainz", "Carlos Sainz", "SAI", "https://ejemplo.com/sai.jpg")
        );
        when(buscarPilotoInput.buscarTodosLosPilotos()).thenReturn(pilotos);
        ResponseEntity<?> response = buscarPilotoController.buscarPilotos();
        assertEquals(200, response.getStatusCodeValue());
        List<PilotoDto> pilotoDtos = (List<PilotoDto>) response.getBody();
        assertEquals(2, pilotoDtos.size());
        assertEquals("Fernando", pilotoDtos.get(0).getName());
        assertEquals("Carlos", pilotoDtos.get(1).getName());
    }

    @Test
    void buscarPilotos_pilotoNoExiste_returnHTTP404() {
        when(buscarPilotoInput.buscarTodosLosPilotos()).thenThrow(new RuntimeException("No hay pilotos registrados."));
        ResponseEntity<?> response = buscarPilotoController.buscarPilotos();
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("No hay pilotos registrados.", response.getBody());
    }
}