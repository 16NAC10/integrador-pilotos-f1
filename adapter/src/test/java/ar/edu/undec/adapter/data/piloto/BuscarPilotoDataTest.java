package ar.edu.undec.adapter.data.piloto;

import ar.edu.undec.adapter.data.piloto.crud.BuscarPilotoCrud;
import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import ar.edu.undec.adapter.data.piloto.repoimplementation.BuscarPilotoRepoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import piloto.model.Piloto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPilotoDataTest {

    @Mock
    private BuscarPilotoCrud buscarPilotoCrud;

    @InjectMocks
    private BuscarPilotoRepoImplementation buscarPilotoRepoImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPilotoPorId_pilotoEncontrado_returnPiloto() {
        UUID id = UUID.randomUUID();
        PilotoEntidad pilotoEntidad = new PilotoEntidad(id, "Max", "Verstappen", "Max Verstappen", "VER", "https://example.com/ver.jpg");
        when(buscarPilotoCrud.searchPilotoEntidadById(id)).thenReturn(Optional.of(pilotoEntidad));
        Piloto resultado = buscarPilotoRepoImplementation.buscarPilotoPorId(id);
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void buscarPilotoPorId_pilotoNoEncontrado_returnNull() {
        UUID id = UUID.randomUUID();
        when(buscarPilotoCrud.searchPilotoEntidadById(id)).thenReturn(Optional.empty());
        Piloto resultado = buscarPilotoRepoImplementation.buscarPilotoPorId(id);
        assertNull(resultado);
    }

    @Test
    void buscarPilotoPorNombreCompleto_pilotoEncontrado_returnPiloto() {
        String nombre = "Lewis Hamilton";
        PilotoEntidad pilotoEntidad = new PilotoEntidad(UUID.randomUUID(), "Lewis", "Hamilton", nombre, "HAM", "https://example.com/ham.jpg");
        when(buscarPilotoCrud.searchPilotoEntidadByFullName(nombre)).thenReturn(Optional.of(pilotoEntidad));
        Piloto resultado = buscarPilotoRepoImplementation.buscarPilotoPorNombreCompleto(nombre);
        assertNotNull(resultado);
        assertEquals("Lewis", resultado.getName());
        assertEquals("Hamilton", resultado.getSurname());
    }

    @Test
    void buscarPilotoPorNombreCompleto_pilotoNoEncontrado_returnNull() {
        String nombre = "Sebastian Vettel";
        when(buscarPilotoCrud.searchPilotoEntidadByFullName(nombre)).thenReturn(Optional.empty());
        Piloto resultado = buscarPilotoRepoImplementation.buscarPilotoPorNombreCompleto(nombre);
        assertNull(resultado);
    }

    @Test
    void buscarPilotoPorAbreviatura_pilotoEncontrado_returnPiloto() {
        String abreviatura = "ALO";
        PilotoEntidad pilotoEntidad = new PilotoEntidad(UUID.randomUUID(), "Fernando", "Alonso", "Fernando Alonso", "ALO", "https://example.com/alo.jpg");
        when(buscarPilotoCrud.searchPilotoEntidadByShortName(abreviatura)).thenReturn(Optional.of(pilotoEntidad));
        Piloto resultado = buscarPilotoRepoImplementation.buscarPilotoPorAbreviatura(abreviatura);
        assertNotNull(resultado);
        assertEquals("Fernando", resultado.getName());
        assertEquals("Alonso", resultado.getSurname());
    }

    @Test
    void buscarPilotoPorAbreviatura_pilotoNoEncontrado_returnNull() {
        String abreviatura = "RAI";
        when(buscarPilotoCrud.searchPilotoEntidadByShortName(abreviatura)).thenReturn(Optional.empty());
        Piloto resultado = buscarPilotoRepoImplementation.buscarPilotoPorAbreviatura(abreviatura);
        assertNull(resultado);
    }

    @Test
    void buscarTodosLosPilotos_pilotoEncontrado_returnListaPilotos() {
        PilotoEntidad piloto1 = new PilotoEntidad(UUID.randomUUID(), "Carlos", "Sainz", "Carlos Sainz", "SAI", "https://example.com/sai.jpg");
        PilotoEntidad piloto2 = new PilotoEntidad(UUID.randomUUID(), "Charles", "Leclerc", "Charles Leclerc", "LEC", "https://example.com/lec.jpg");
        when(buscarPilotoCrud.findAll()).thenReturn(Arrays.asList(piloto1, piloto2));
        List<Piloto> resultado = buscarPilotoRepoImplementation.buscarTodosLosPilotos();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Carlos", resultado.get(0).getName());
        assertEquals("Charles", resultado.get(1).getName());
    }

    @Test
    void buscarTodosLosPilotos_pilotoNoExiste_returnListaPilotosVacia() {
        when(buscarPilotoCrud.findAll()).thenReturn(Arrays.asList());
        List<Piloto> resultado = buscarPilotoRepoImplementation.buscarTodosLosPilotos();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}