package ar.edu.undec.adapter.data.piloto.repoimplementation;
import ar.edu.undec.adapter.data.piloto.crud.BuscarPilotoCrud;
import ar.edu.undec.adapter.data.piloto.mapper.PilotoMapper;
import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piloto.model.Piloto;
import piloto.output.BuscarPilotoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuscarPilotoRepoImplementation implements BuscarPilotoRepository {

    BuscarPilotoCrud buscarPilotoCrud;

    @Autowired
    public BuscarPilotoRepoImplementation(BuscarPilotoCrud buscarPilotoCrud) {
        this.buscarPilotoCrud = buscarPilotoCrud;
    }

    @Override
    public Piloto buscarPilotoPorId(UUID id) {
        PilotoEntidad pilotoEntidad = buscarPilotoCrud.searchPilotoEntidadById(id).orElse(null);
        if (pilotoEntidad == null) {
            return null;
        }else {
            return PilotoMapper.dataCoreMapper(pilotoEntidad);
        }
    }

    @Override
    public Piloto buscarPilotoPorNombreCompleto(String fullName) {
        PilotoEntidad pilotoEntidad = buscarPilotoCrud.searchPilotoEntidadByFullName(fullName).orElse(null);
        if (pilotoEntidad == null) {
            return null;
        }else {
            return PilotoMapper.dataCoreMapper(pilotoEntidad);
        }
    }

    @Override
    public Piloto buscarPilotoPorAbreviatura(String abreviatura) {
        PilotoEntidad pilotoEntidad = buscarPilotoCrud.searchPilotoEntidadByShortName(abreviatura).orElse(null);
        if (pilotoEntidad == null) {
            return null;
        }else {
            return PilotoMapper.dataCoreMapper(pilotoEntidad);
        }
    }

    @Override
    public List<Piloto> buscarTodosLosPilotos() {
        List<PilotoEntidad> pilotosData = buscarPilotoCrud.findAll();
        List<Piloto> pilotosCore = new ArrayList<>();
        for(PilotoEntidad pilotoEntidad : pilotosData){
            Piloto piloto = PilotoMapper.dataCoreMapper(pilotoEntidad);
            pilotosCore.add(piloto);
        }
        return pilotosCore;
    }
}