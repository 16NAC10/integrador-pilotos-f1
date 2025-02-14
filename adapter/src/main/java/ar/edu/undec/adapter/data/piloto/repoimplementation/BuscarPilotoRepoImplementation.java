package ar.edu.undec.adapter.data.piloto.repoimplementation;
import ar.edu.undec.adapter.data.piloto.crud.BuscarPilotoCrud;
import ar.edu.undec.adapter.data.piloto.mapper.PilotoMapper;
import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piloto.model.Piloto;
import piloto.output.BuscarPilotoRepository;
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
        PilotoEntidad pilotoEntidad = buscarPilotoCrud.searchPilotoEntidadById(id).get();
        return PilotoMapper.dataCoreMapper(pilotoEntidad);
    }

    @Override
    public Piloto buscarPilotoPorNombreCompleto(String fullName) {
        PilotoEntidad pilotoEntidad = buscarPilotoCrud.searchPilotoEntidadByFullName(fullName).get();
        return PilotoMapper.dataCoreMapper(pilotoEntidad);
    }

    @Override
    public Piloto buscarPilotoPorAbreviatura(String abreviatura) {
        PilotoEntidad pilotoEntidad = buscarPilotoCrud.searchPilotoEntidadByShortName(abreviatura).get();
        return PilotoMapper.dataCoreMapper(pilotoEntidad);
    }
}