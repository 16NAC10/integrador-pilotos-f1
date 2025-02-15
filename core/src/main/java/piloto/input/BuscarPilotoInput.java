package piloto.input;
import piloto.model.Piloto;

import java.util.List;
import java.util.UUID;

public interface BuscarPilotoInput {
    Piloto buscarPilotoPorId(UUID id);
    Piloto buscarPilotoPorNombreCompleto(String fullName);
    Piloto buscarPilotoPorAbreviatura(String abreviatura);
    List<Piloto> buscarTodosLosPilotos();
}