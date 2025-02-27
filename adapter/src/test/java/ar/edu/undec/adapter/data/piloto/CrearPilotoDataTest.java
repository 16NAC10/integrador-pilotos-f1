package ar.edu.undec.adapter.data.piloto;

import ar.edu.undec.adapter.data.piloto.crud.CrearPilotoCrud;
import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import ar.edu.undec.adapter.data.piloto.repoimplementation.CrearPilotoRepoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import piloto.model.Piloto;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoDataTest {

    @Mock
    CrearPilotoCrud crearPilotoCrud;

    @InjectMocks
    CrearPilotoRepoImplementation crearPilotoRepoImplementation;

    UUID id = UUID.randomUUID();

    @Test
    public void crearPiloto_pilotoCreado_returnId() {
        Piloto piloto = Piloto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        when(crearPilotoCrud.save(any(PilotoEntidad.class))).thenReturn(new PilotoEntidad(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://..."));
        UUID resultado = crearPilotoRepoImplementation.crearPiloto(piloto);
        Assertions.assertEquals(id, resultado);
    }

    @Test
    public void crearPiloto_pilotoNoCreado_returnNull() {
        Piloto piloto = Piloto.factory(id, "Ignacio", "Páez", "Ignacio Páez", "PAE", "https://...");
        doThrow(RuntimeException.class).when(crearPilotoCrud).save(any(PilotoEntidad.class));
        UUID resultado = crearPilotoRepoImplementation.crearPiloto(piloto);
        Assertions.assertNull(resultado);
    }

    @Test
    void buscarPiloto_pilotoEncontrado_returnTrue() {
        String nombre = "Ignacio Páez";
        when(crearPilotoCrud.existsByFullName(nombre)).thenReturn(true);
        boolean resultado = crearPilotoRepoImplementation.buscarPiloto(nombre);
        Assertions.assertTrue(resultado);
    }

    @Test
    void buscarPiloto_pilotoNoEncontrado_returnFalse() {
        String nombre = "Ignacio Páez";
        when(crearPilotoCrud.existsByFullName(nombre)).thenReturn(false);
        boolean resultado = crearPilotoRepoImplementation.buscarPiloto(nombre);
        Assertions.assertFalse(resultado);
    }

    @Test
    void buscarPilotoPorAbreviatura_pilotoEncontrado_returnTrue() {
        String abreviatura = "PAE";
        when(crearPilotoCrud.existsByShortName(abreviatura)).thenReturn(true);
        boolean resultado = crearPilotoRepoImplementation.buscarPilotoPorAbreviatura(abreviatura);
        Assertions.assertTrue(resultado);
    }

    @Test
    void buscarPilotoPorAbreviatura_debeRetornarFalseSiNoExiste() {
        String abreviatura = "PAE";
        when(crearPilotoCrud.existsByShortName(abreviatura)).thenReturn(false);
        boolean resultado = crearPilotoRepoImplementation.buscarPilotoPorAbreviatura(abreviatura);
        Assertions.assertFalse(resultado);
    }
}