package piloto.output;

import piloto.model.Piloto;

import java.util.UUID;

public interface BuscarPilotoRepository {
    Piloto buscarPiloto(UUID id);
}
