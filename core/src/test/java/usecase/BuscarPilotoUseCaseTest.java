package usecase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exception.PilotoExisteException;
import piloto.exception.PilotoInexistenteException;
import piloto.input.BuscarPilotoInput;
import piloto.model.Piloto;
import piloto.output.BuscarPilotoRepository;
import piloto.usecase.buscarpilotousecase.BuscarPilotoUseCase;
import java.util.UUID;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarPilotoUseCaseTest {

    BuscarPilotoInput buscarPilotoInput;

    @Mock
    BuscarPilotoRepository buscarPilotoRepository;

    @BeforeEach
    void setUp() {buscarPilotoInput = new BuscarPilotoUseCase(buscarPilotoRepository);}

    UUID id = UUID.randomUUID();

    @Test
    void buscarPiloto_pilotoExiste_returnPiloto() {
        Piloto piloto = Piloto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        when(buscarPilotoRepository.buscarPiloto(id)).thenReturn(piloto);
        Assertions.assertEquals(piloto, buscarPilotoInput.buscarPiloto(id));
    }

    @Test
    void buscarPilotoPorId_pilotoNoExiste_Exception() {
        when(buscarPilotoRepository.buscarPiloto(id)).thenThrow(PilotoInexistenteException.class);
        Assertions.assertThrows(PilotoInexistenteException.class, () -> buscarPilotoInput.buscarPiloto(id));
    }
}