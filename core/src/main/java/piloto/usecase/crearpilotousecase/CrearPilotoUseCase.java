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
        String fullName = NombreParser.parse(crearPilotoRequestModel.getFullName());
        String shortName = crearPilotoRequestModel.getShortName().toUpperCase();
        if(crearPilotoRepository.buscarPiloto(fullName) || crearPilotoRepository.buscarPilotoPorAbreviatura(shortName)){
            throw new PilotoExisteException("El piloto ya existe.");
        }
        Piloto piloto = Piloto.factory(crearPilotoRequestModel.getId(), crearPilotoRequestModel.getName(), crearPilotoRequestModel.getSurname(), fullName, shortName, crearPilotoRequestModel.getPictureUrl());
        return crearPilotoRepository.crearPiloto(piloto);
    }
}
