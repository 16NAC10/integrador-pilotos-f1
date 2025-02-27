package piloto.output;
import piloto.model.Piloto;

import java.util.List;
import java.util.UUID;

public interface BuscarPilotoRepository {
    Piloto buscarPilotoPorId(UUID id);
    Piloto buscarPilotoPorNombreCompleto(String nombre);
    Piloto buscarPilotoPorAbreviatura(String abreviatura);
    List<Piloto> buscarTodosLosPilotos();
}