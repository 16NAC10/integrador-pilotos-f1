package piloto.usecase.crearpilotousecase;

import piloto.exception.PilotoExisteException;
import piloto.input.CrearPilotoInput;
import piloto.model.Piloto;
import piloto.output.CrearPilotoRepository;
import utils.NombreParser;

import java.util.UUID;

public class CrearPilotoUseCase implements CrearPilotoInput {

    private CrearPilotoRepository crearPilotoRepository;

    public CrearPilotoUseCase(CrearPilotoRepository crearPilotoRepository) {
        this.crearPilotoRepository = crearPilotoRepository;
    }

    @Override
    public UUID crearPiloto(CrearPilotoRequestModel crearPilotoRequestModel) throws PilotoExisteException {
        String fullName = crearPilotoRequestModel.getFullName() == null ? null : NombreParser.parse(crearPilotoRequestModel.getFullName());
        String shortName = crearPilotoRequestModel.getShortName() == null ? null : crearPilotoRequestModel.getShortName().toUpperCase();
        Piloto piloto = Piloto.factory(crearPilotoRequestModel.getId(), crearPilotoRequestModel.getName(), crearPilotoRequestModel.getSurname(), fullName, shortName, crearPilotoRequestModel.getPictureUrl());
        if(crearPilotoRepository.buscarPiloto(fullName)){
            throw new PilotoExisteException("El piloto " + fullName + " ya existe.");
        }
        if(crearPilotoRepository.buscarPilotoPorAbreviatura(shortName)){
            throw new PilotoExisteException("El piloto con abreviatura " + shortName + " ya existe.");
        }
        return crearPilotoRepository.crearPiloto(piloto);
    }
}
