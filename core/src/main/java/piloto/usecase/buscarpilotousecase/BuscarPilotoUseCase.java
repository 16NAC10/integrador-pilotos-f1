package piloto.usecase.buscarpilotousecase;

import piloto.exception.PilotoInexistenteException;
import piloto.input.BuscarPilotoInput;
import piloto.model.Piloto;
import piloto.output.BuscarPilotoRepository;
import utils.NombreParser;

import java.util.List;
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
            throw new PilotoInexistenteException("No existe piloto con id: " + id);
        }
        return piloto;
    }

    @Override
    public Piloto buscarPilotoPorNombreCompleto(String fullName) throws PilotoInexistenteException {
        fullName = NombreParser.parse(fullName);
        Piloto piloto = buscarPilotoRepository.buscarPilotoPorNombreCompleto(fullName);
        if(piloto == null) {
            throw new PilotoInexistenteException("No existe el piloto: " + fullName);
        }
        return piloto;
    }

    @Override
    public Piloto buscarPilotoPorAbreviatura(String abreviatura) throws PilotoInexistenteException {
        Piloto piloto = buscarPilotoRepository.buscarPilotoPorAbreviatura(abreviatura.toUpperCase());
        if(piloto == null) {
            throw new PilotoInexistenteException("No existe el piloto con abreviatura: " + abreviatura);
        }
        return piloto;
    }

    @Override
    public List<Piloto> buscarTodosLosPilotos() throws PilotoInexistenteException{
        List<Piloto> pilotos = buscarPilotoRepository.buscarTodosLosPilotos();
        if(pilotos.isEmpty()) {
            throw new PilotoInexistenteException("No existe ningún piloto");
        }
        return pilotos;
    }
}