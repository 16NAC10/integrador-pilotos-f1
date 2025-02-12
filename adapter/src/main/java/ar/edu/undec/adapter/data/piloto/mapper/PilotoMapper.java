package ar.edu.undec.adapter.data.piloto.mapper;

import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import piloto.model.Piloto;

public class PilotoMapper {

    public static Piloto dataCoreMapper(PilotoEntidad pilotoEntidad) {
        return Piloto.factory(pilotoEntidad.getId(), pilotoEntidad.getName(), pilotoEntidad.getSurname(), pilotoEntidad.getFullName(), pilotoEntidad.getShortName(), pilotoEntidad.getPictureUrl());
    }

    public static PilotoEntidad coreDataMapper(Piloto piloto) {
        return new PilotoEntidad(piloto.getId(), piloto.getName(), piloto.getSurname(), piloto.getFullName(), piloto.getShortName(), piloto.getPictureUrl());
    }
}
