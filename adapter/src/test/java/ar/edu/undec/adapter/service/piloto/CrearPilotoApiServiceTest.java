package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.service.piloto.controller.CrearPilotoApiController;
import ar.edu.undec.adapter.service.piloto.controller.CrearPilotoController;
import ar.edu.undec.adapter.service.piloto.dto.PilotoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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
    void getJson_respuestaCorrecta_returnString() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);

        String jsonString = """
                [
                    {
                        "full_name": "Max VERSTAPPEN",
                        "first_name": "Max",
                        "last_name": "Verstappen",
                        "name_acronym": "VER",
                        "headshot_url": "https://ejemplo.com/ver.jpg"
                    },
                    {
                        "full_name": "Logan SARGEANT",
                        "first_name": "Logan",
                        "last_name": "Sargeant",
                        "name_acronym": "SAR",
                        "headshot_url": "https://ejemplo.com/sar.jpg"
                    },
                    {
                        "full_name": "Lando NORRIS",
                        "first_name": "Lando",
                        "last_name": "Norris",
                        "name_acronym": "NOR",
                        "headshot_url": "https://ejemplo.com/nor.jpg"
                    },
                    {
                        "full_name": "Pierre GASLY",
                        "first_name": "Pierre",
                        "last_name": "Gasly",
                        "name_acronym": "GAS",
                        "headshot_url": "https://ejemplo.com/gas.jpg"
                    },
                    {
                        "full_name": "Sergio PEREZ",
                        "first_name": "Sergio",
                        "last_name": "Perez",
                        "name_acronym": "PER",
                        "headshot_url": "https://ejemplo.com/per.jpg"
                    }
                ]
                """;

        doReturn(jsonString).when(spyController).getJson();
        String jsonResponse = spyController.getJson();
        assertNotNull(jsonResponse);
        assertEquals(jsonString.trim(), jsonResponse.trim());
    }

    @Test
    void getJson_errorConexion_exception() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);
        doThrow(new RuntimeException("Error al conectar con la API")).when(spyController).getJson();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> spyController.getJson());
        assertEquals("Error al conectar con la API", exception.getMessage());
    }

    @Test
    void crearPorApi_pilotosCreadosCorrectamente() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);

        String jsonString = """
                [
                    {
                        "full_name": "MAX VERSTAPPEN",
                        "first_name": "Max",
                        "last_name": "Verstappen",
                        "name_acronym": "VER",
                        "headshot_url": "https://ejemplo.com/ver.jpg"
                    },
                    {
                        "full_name": "SERGIO PEREZ",
                        "first_name": "Sergio",
                        "last_name": "Perez",
                        "name_acronym": "PER",
                        "headshot_url": "https://ejemplo.com/per.jpg"
                    }
                ]
                """;

        doReturn(jsonString).when(spyController).getJson();
        List<PilotoDto> pilotosCreados = new ArrayList<>();
        doAnswer(invocation -> {
            PilotoDto piloto = invocation.getArgument(0);
            pilotosCreados.add(piloto);
            return null;
        }).when(crearPilotoController).crearPiloto(any(PilotoDto.class));
        ResponseEntity<?> response = spyController.crearPorApi();

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(2, pilotosCreados.size());
        assertEquals("Max", pilotosCreados.get(0).getName());
        assertEquals("Verstappen", pilotosCreados.get(0).getSurname());
        assertEquals("VER", pilotosCreados.get(0).getShortName());
        assertEquals("https://ejemplo.com/ver.jpg", pilotosCreados.get(0).getPictureUrl());
        assertEquals("Sergio", pilotosCreados.get(1).getName());
        assertEquals("Perez", pilotosCreados.get(1).getSurname());
        assertEquals("PER", pilotosCreados.get(1).getShortName());
        assertEquals("https://ejemplo.com/per.jpg", pilotosCreados.get(1).getPictureUrl());
        verify(crearPilotoController, times(2)).crearPiloto(any(PilotoDto.class));
    }
    @Test
    void crearPilotoPorApi_errorAlCrear_exception() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);
        doThrow(new RuntimeException("Error al obtener JSON")).when(spyController).getJson();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> spyController.crearPorApi());
        assertEquals("Error al obtener JSON", exception.getMessage());
    }
}