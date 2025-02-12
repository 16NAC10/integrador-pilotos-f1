package piloto.usecase.crearpilotousecase;

import piloto.exception.PilotoExisteException;
import piloto.input.CrearPilotoInput;
import piloto.model.Piloto;
import piloto.output.CrearPilotoRepository;

import java.util.UUID;

public class CrearPilotoUseCase implements CrearPilotoInput {

    private CrearPilotoRepository crearPilotoRepository;

    public CrearPilotoUseCase(CrearPilotoRepository crearPilotoRepository) {
        this.crearPilotoRepository = crearPilotoRepository;
    }

    @Override
    public UUID crearPiloto(CrearPilotoRequestModel crearPilotoRequestModel) throws PilotoExisteException {
        if(crearPilotoRepository.buscarPiloto(crearPilotoRequestModel.getId())){
            throw new PilotoExisteException("El piloto ya existe.");
        }
        Piloto piloto = Piloto.factory(crearPilotoRequestModel.getId(), crearPilotoRequestModel.getName(), crearPilotoRequestModel.getSurname(), crearPilotoRequestModel.getFullName(), crearPilotoRequestModel.getShortName(), crearPilotoRequestModel.getPictureUrl());
        return crearPilotoRepository.crearPiloto(piloto);
    }
}
