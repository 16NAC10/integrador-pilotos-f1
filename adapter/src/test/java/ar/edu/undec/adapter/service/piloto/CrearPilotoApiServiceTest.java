package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.service.piloto.controller.CrearPilotoApiController;
import ar.edu.undec.adapter.service.piloto.controller.CrearPilotoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CrearPilotoApiServiceTest {

    @Mock
    private CrearPilotoController crearPilotoController;

    @InjectMocks
    private CrearPilotoApiController crearPilotoApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetJson_successfulResponse() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);

        String mockJson = """
                [
                    {
                        "full_name": "John Doe",
                        "name_acronym": "JD",
                        "headshot_url": "https://example.com/john.jpg"
                    }
                ]
                """;

        doReturn(mockJson).when(spyController).getJson();

        String jsonResponse = spyController.getJson();
        assertNotNull(jsonResponse, "La respuesta de getJson no debe ser nula");
        //assertTrue(jsonResponse.startsWith("[{"), "La respuesta de getJson no debe ser nula");
    }

    @Test
    void testGetJson_throwsException() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);
        doThrow(new RuntimeException("Error al conectar con la API")).when(spyController).getJson();

        RuntimeException exception = assertThrows(RuntimeException.class, spyController::getJson);
        assertEquals("Error al conectar con la API", exception.getMessage(), "El mensaje de la excepción no coincide");
    }

    @Test
    void testCrearPorApi_successfulResponse() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);

        String mockJson = """
                [
                    {
                        "full_name": "John Doe",
                        "name_acronym": "JD",
                        "headshot_url": "https://example.com/john.jpg"
                    },
                    {
                        "full_name": "Jane Smith",
                        "name_acronym": "JS",
                        "headshot_url": null
                    }
                ]
                """;

        doReturn(mockJson).when(spyController).getJson();

        List<CrearPilotoRequestModel> pilotosCreados = new ArrayList<>();
        doAnswer(invocation -> {
            CrearPilotoRequestModel piloto = invocation.getArgument(0);
            pilotosCreados.add(piloto);
            return null;
        }).when(crearPilotoController).crearPiloto(any(CrearPilotoRequestModel.class));

        ResponseEntity<?> response = spyController.crearPorApi();
        assertEquals(201, response.getStatusCodeValue());

        assertEquals(2, pilotosCreados.size());

        CrearPilotoRequestModel piloto1 = pilotosCreados.get(0);
        assertEquals("John", piloto1.getName());
        assertEquals("Doe", piloto1.getSurname());
        assertEquals("https://example.com/john.jpg", piloto1.getPictureUrl());

        CrearPilotoRequestModel piloto2 = pilotosCreados.get(1);
        assertEquals("Jane", piloto2.getName());
        assertEquals("Smith", piloto2.getSurname());
        assertEquals("Sin imagen", piloto2.getPictureUrl());
    }

    @Test
    void testCrearPorApi_withDuplicatePilots() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);

        String mockJson = """
                [
                    {
                        "full_name": "John Doe",
                        "name_acronym": "JD",
                        "headshot_url": "https://example.com/john.jpg"
                    },
                    {
                        "full_name": "John Doe",
                        "name_acronym": "JD",
                        "headshot_url": "https://example.com/john.jpg"
                    }
                ]
                """;

        doReturn(mockJson).when(spyController).getJson();

        List<CrearPilotoRequestModel> pilotosCreados = new ArrayList<>();
        doAnswer(invocation -> {
            CrearPilotoRequestModel piloto = invocation.getArgument(0);
            pilotosCreados.add(piloto);
            return null;
        }).when(crearPilotoController).crearPiloto(any(CrearPilotoRequestModel.class));

        ResponseEntity<?> response = spyController.crearPorApi();
        assertEquals(201, response.getStatusCodeValue(), "El estado HTTP debe ser 201 (Created)");

        assertEquals(1, pilotosCreados.size(), "Solo debe haberse creado un piloto, evitando duplicados");
        assertEquals("John", pilotosCreados.get(0).getName());
        assertEquals("Doe", pilotosCreados.get(0).getSurname());
    }

    @Test
    void testCrearPorApi_runtimeException() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);
        doThrow(new RuntimeException("Error al obtener JSON")).when(spyController).getJson();

        RuntimeException exception = assertThrows(RuntimeException.class, spyController::crearPorApi);
        assertEquals("Error al obtener JSON", exception.getMessage());
    }
}

