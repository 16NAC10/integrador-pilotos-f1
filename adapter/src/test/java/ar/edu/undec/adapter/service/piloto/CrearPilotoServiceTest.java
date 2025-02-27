package ar.edu.undec.adapter.service.piloto;

import ar.edu.undec.adapter.service.piloto.controller.CrearPilotoController;
import ar.edu.undec.adapter.service.piloto.dto.PilotoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import piloto.exception.PilotoExisteException;
import piloto.input.CrearPilotoInput;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoServiceTest {

    @Mock
    CrearPilotoInput crearPilotoInput;

    @InjectMocks
    CrearPilotoController crearPilotoController;

    @Test
    public void crearPiloto_pilotoCreado_ReturnHTTP201() {
        UUID id = UUID.randomUUID();
        when(crearPilotoInput.crearPiloto(any(CrearPilotoRequestModel.class))).thenReturn(id);
        ResponseEntity<?> crearPilotoResponse = crearPilotoController.crearPiloto(PilotoDto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://..."));
        Assertions.assertEquals(HttpStatus.CREATED, crearPilotoResponse.getStatusCode());
        Assertions.assertEquals("El piloto se ha guardado correctamente", crearPilotoResponse.getBody());
    }

    @Test
    public void crearPiloto_pilotoExiste_ReturnHTTP409() {
        UUID id = UUID.randomUUID();
        doThrow(PilotoExisteException.class).when(crearPilotoInput).crearPiloto(any(CrearPilotoRequestModel.class));
        ResponseEntity<?> crearPilotoResponse = crearPilotoController.crearPiloto(PilotoDto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://..."));
        Assertions.assertEquals(HttpStatus.CONFLICT, crearPilotoResponse.getStatusCode());
    }

    @Test
    public void crearPiloto_idNull_ReturnHTTP400() {
        UUID id = null;
        ResponseEntity<?> crearPilotoResponse = crearPilotoController.crearPiloto(PilotoDto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://..."));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, crearPilotoResponse.getStatusCode());
    }
}
