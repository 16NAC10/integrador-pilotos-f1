package ar.edu.undec.adapter.data.piloto.repoimplementation;

import ar.edu.undec.adapter.data.piloto.crud.CrearPilotoCrud;
import ar.edu.undec.adapter.data.piloto.mapper.PilotoMapper;
import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piloto.model.Piloto;
import piloto.output.CrearPilotoRepository;

import java.util.UUID;

@Service
public class CrearPilotoRepoImplementation implements CrearPilotoRepository {

    CrearPilotoCrud crearPilotoCrud;

    @Autowired
    public CrearPilotoRepoImplementation(CrearPilotoCrud crearPilotoCrud) {
        this.crearPilotoCrud = crearPilotoCrud;
    }

    @Override
    public UUID crearPiloto(Piloto piloto) {
        try{
            PilotoEntidad pilotoEntidad = PilotoMapper.coreDataMapper(piloto);

            pilotoEntidad = crearPilotoCrud.save(pilotoEntidad);
            return pilotoEntidad.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean buscarPiloto(UUID id) {
        return crearPilotoCrud.searchPilotoEntidadById(id).isPresent();
    }
}
