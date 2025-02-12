package piloto.input;

import piloto.exception.PilotoExisteException;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;

import java.util.UUID;

public interface CrearPilotoInput {
    UUID crearPiloto(CrearPilotoRequestModel crearPilotoRequestModel) throws PilotoExisteException;
}
