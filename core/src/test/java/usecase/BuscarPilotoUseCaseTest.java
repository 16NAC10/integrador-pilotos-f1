package usecase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.exception.PilotoInexistenteException;
import piloto.input.BuscarPilotoInput;
import piloto.model.Piloto;
import piloto.output.BuscarPilotoRepository;
import piloto.usecase.buscarpilotousecase.BuscarPilotoUseCase;

import java.util.Arrays;
import java.util.List;
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
    void buscarPilotoPorId_pilotoExiste_returnPiloto() {
        Piloto piloto = Piloto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        when(buscarPilotoRepository.buscarPilotoPorId(id)).thenReturn(piloto);
        Assertions.assertEquals(piloto, buscarPilotoInput.buscarPilotoPorId(id));
    }

    @Test
    void buscarPilotoPorId_pilotoNoExiste_Exception() {
        when(buscarPilotoRepository.buscarPilotoPorId(id)).thenThrow(PilotoInexistenteException.class);
        Assertions.assertThrows(PilotoInexistenteException.class, () -> buscarPilotoInput.buscarPilotoPorId(id));
    }

    @Test
    void buscarPilotoPorNombreCompleto_pilotoExiste_returnPiloto() {
        Piloto piloto = Piloto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        when(buscarPilotoRepository.buscarPilotoPorNombreCompleto("Ignacio Páez")).thenReturn(piloto);
        Assertions.assertEquals(piloto, buscarPilotoInput.buscarPilotoPorNombreCompleto("Ignacio Páez"));
    }

    @Test
    void buscarPilotoPorNombreCompleto_pilotoNoExiste_Exception() {
        when(buscarPilotoRepository.buscarPilotoPorNombreCompleto("Ignacio Páez")).thenThrow(PilotoInexistenteException.class);
        Assertions.assertThrows(PilotoInexistenteException.class , () -> buscarPilotoInput.buscarPilotoPorNombreCompleto("Ignacio Páez"));
    }

    @Test
    void buscarPilotoPorAbreviatura_pilotoExiste_returnPiloto() {
        Piloto piloto = Piloto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        when(buscarPilotoRepository.buscarPilotoPorAbreviatura("PAE")).thenReturn(piloto);
        Assertions.assertEquals(piloto, buscarPilotoInput.buscarPilotoPorAbreviatura("PAE"));
    }

    @Test
    void buscarPilotoPorAbreviatura_pilotoNoExiste_Exception() {
        when(buscarPilotoRepository.buscarPilotoPorAbreviatura("PAE")).thenThrow(PilotoInexistenteException.class);
        Assertions.assertThrows(PilotoInexistenteException.class , () -> buscarPilotoInput.buscarPilotoPorAbreviatura("PAE"));
    }

    @Test
    void buscarTodosLosPilotos_pilotoExiste_returnListaPilotos() {
        Piloto piloto1 = Piloto.factory(UUID.randomUUID(), "Lewis", "Hamilton", "Lewis Hamilton", "HAM", "url_foto");
        Piloto piloto2 = Piloto.factory(UUID.randomUUID(), "Max", "Verstappen", "Max Verstappen", "VER", "url_foto");
        List<Piloto> listaPilotos = Arrays.asList(piloto1, piloto2);
        when(buscarPilotoRepository.buscarTodosLosPilotos()).thenReturn(listaPilotos);
        List<Piloto> resultado = buscarPilotoRepository.buscarTodosLosPilotos();
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    void buscarTodosLosPilotos_ListaVacia_LanzaExcepcion() {
        when(buscarPilotoRepository.buscarTodosLosPilotos()).thenThrow(PilotoInexistenteException.class);
        Assertions.assertThrows(PilotoInexistenteException.class, () -> buscarPilotoRepository.buscarTodosLosPilotos());
    }
}