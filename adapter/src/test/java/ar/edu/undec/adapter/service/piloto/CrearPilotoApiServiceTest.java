package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.service.piloto.controller.CrearPilotoApiController;
import ar.edu.undec.adapter.service.piloto.controller.CrearPilotoController;
import ar.edu.undec.adapter.service.piloto.dto.PilotoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoApiServiceTest {

    @Mock
    private CrearPilotoController crearPilotoController;

    @InjectMocks
    private CrearPilotoApiController crearPilotoApiController;

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
        Assertions.assertNotNull(jsonResponse);
        Assertions.assertEquals(jsonString.trim(), jsonResponse.trim());
    }

    @Test
    void getJson_errorConexion_exception() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);
        doThrow(new RuntimeException("Error al conectar con la API")).when(spyController).getJson();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> spyController.getJson());
        Assertions.assertEquals("Error al conectar con la API", exception.getMessage());
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

        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals(2, pilotosCreados.size());
        Assertions.assertEquals("Max", pilotosCreados.get(0).getName());
        Assertions.assertEquals("Verstappen", pilotosCreados.get(0).getSurname());
        Assertions.assertEquals("VER", pilotosCreados.get(0).getShortName());
        Assertions.assertEquals("https://ejemplo.com/ver.jpg", pilotosCreados.get(0).getPictureUrl());
        Assertions.assertEquals("Sergio", pilotosCreados.get(1).getName());
        Assertions.assertEquals("Perez", pilotosCreados.get(1).getSurname());
        Assertions.assertEquals("PER", pilotosCreados.get(1).getShortName());
        Assertions.assertEquals("https://ejemplo.com/per.jpg", pilotosCreados.get(1).getPictureUrl());
        verify(crearPilotoController, times(2)).crearPiloto(any(PilotoDto.class));
    }

    @Test
    void crearPilotoPorApi_errorAlCrear_exception() {
        CrearPilotoApiController spyController = spy(crearPilotoApiController);
        doThrow(new RuntimeException("Error al obtener JSON")).when(spyController).getJson();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> spyController.crearPorApi());
        Assertions.assertEquals("Error al obtener JSON", exception.getMessage());
    }
}