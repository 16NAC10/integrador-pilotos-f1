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
    public Piloto buscarPilotoPorId(UUID id) throws PilotoInexistenteException {
        Piloto piloto = buscarPilotoRepository.buscarPilotoPorId(id);
        if (piloto == null) {
            throw new PilotoInexistenteException("El piloto no existe.");
        }
        return piloto;
    }

    @Override
    public Piloto buscarPilotoPorNombreCompleto(String fullName) throws PilotoInexistenteException {
        Piloto piloto = buscarPilotoRepository.buscarPilotoPorNombreCompleto(fullName);
        if(piloto == null) {
            throw new PilotoInexistenteException("El piloto no existe.");
        }
        return piloto;
    }

    @Override
    public Piloto buscarPilotoPorAbreviatura(String abreviatura) throws PilotoInexistenteException {
        Piloto piloto = buscarPilotoRepository.buscarPilotoPorAbreviatura(abreviatura);
        if(piloto == null) {
            throw new PilotoInexistenteException("El piloto no existe.");
        }
        return piloto;
    }
}