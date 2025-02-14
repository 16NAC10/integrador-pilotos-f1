package piloto.output;
import piloto.model.Piloto;
import java.util.UUID;

public interface BuscarPilotoRepository {
    Piloto buscarPilotoPorId(UUID id);
    Piloto buscarPilotoPorNombreCompleto(String nombre);
    Piloto buscarPilotoPorAbreviatura(String abreviatura);
}