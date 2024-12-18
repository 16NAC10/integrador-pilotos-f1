package piloto.usecase.buscarpilotousecase;

import piloto.exception.PilotoExisteException;
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

    @Override
    public boolean buscarPiloto(String fullName) {
        boolean piloto = buscarPilotoRepository.buscarPiloto(fullName);
        if(piloto) {
            throw new PilotoExisteException("El piloto ya está registrado.");
        }
        return false;
    }
}
