package piloto.output;

import piloto.model.Piloto;

import java.util.UUID;

public interface CrearPilotoRepository {
    UUID crearPiloto(Piloto piloto);
    boolean buscarPiloto(UUID id);
}
