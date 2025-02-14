package piloto.input;
import piloto.model.Piloto;
import java.util.UUID;

public interface BuscarPilotoInput {
    Piloto buscarPilotoPorId(UUID id);
    Piloto buscarPilotoPorNombreCompleto(String fullName);
    Piloto buscarPilotoPorAbreviatura(String abreviatura);
}