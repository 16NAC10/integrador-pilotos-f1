package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exception.PilotoExisteException;
import piloto.input.CrearPilotoInput;
import piloto.model.Piloto;
import piloto.output.CrearPilotoRepository;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;
import piloto.usecase.crearpilotousecase.CrearPilotoUseCase;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoUseCaseTest {
    CrearPilotoInput crearPilotoInput;

    @Mock
    CrearPilotoRepository crearPilotoRepository;

    @BeforeEach
    void setUp() {crearPilotoInput = new CrearPilotoUseCase(crearPilotoRepository);}

    UUID id = UUID.randomUUID();

    @Test
    void crearPiloto_pilotoNoExiste_crearPiloto() {
        CrearPilotoRequestModel piloto = CrearPilotoRequestModel.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        when(crearPilotoRepository.buscarPiloto(piloto.getFullName())).thenReturn(false);
        when(crearPilotoRepository.crearPiloto(any(Piloto.class))).thenReturn(id);
        Assertions.assertEquals(id, crearPilotoInput.crearPiloto(piloto));
    }

    @Test
    void crearPiloto_pilotoExiste_exception() {
        CrearPilotoRequestModel piloto = CrearPilotoRequestModel.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        when(crearPilotoRepository.buscarPiloto(piloto.getFullName())).thenReturn(true);
        Assertions.assertThrows(PilotoExisteException.class, () -> crearPilotoInput.crearPiloto(piloto));
    }
}
