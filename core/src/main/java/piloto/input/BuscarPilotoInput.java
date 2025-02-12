package piloto.input;
import piloto.model.Piloto;
import java.util.UUID;

public interface BuscarPilotoInput {
    Piloto buscarPiloto(UUID id);
    boolean buscarPiloto(String fullName);
}