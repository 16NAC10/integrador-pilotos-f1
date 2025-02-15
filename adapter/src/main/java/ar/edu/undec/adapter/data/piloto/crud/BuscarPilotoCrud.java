package ar.edu.undec.adapter.data.piloto.crud;

import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuscarPilotoCrud extends CrudRepository<PilotoEntidad, UUID> {
    Optional<PilotoEntidad> searchPilotoEntidadById(UUID id);
    Optional<PilotoEntidad> searchPilotoEntidadByFullName(String fullName);
    Optional<PilotoEntidad> searchPilotoEntidadByShortName(String shortName);
    List<PilotoEntidad> findAll();
}