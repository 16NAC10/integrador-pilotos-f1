package piloto.usecase.buscarpilotousecase;

import piloto.exception.PilotoInexistenteException;
import piloto.input.BuscarPilotoInput;
import piloto.model.Piloto;
import piloto.output.BuscarPilotoRepository;

import java.util.UUID;

public class BuscarPilotoUseCase implements BuscarPilotoInput {

    BuscarPilotoRepository buscarPilotoRepository;

    public BuscarPilotoUseCase(BuscarPilotoRepository buscarPilotoRepository) {
        this.buscarPilotoRepository = buscarPilotoRepository;
    }

    @Override
    public Piloto buscarPiloto(UUID id) {
        Piloto piloto = buscarPilotoRepository.buscarPiloto(id);
        if (piloto == null) {
            throw new PilotoInexistenteException("El piloto no existe.");
        }
        return piloto;
    }
}
