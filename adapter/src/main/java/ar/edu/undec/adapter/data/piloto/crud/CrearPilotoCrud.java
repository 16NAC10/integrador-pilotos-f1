package ar.edu.undec.adapter.data.piloto.crud;

import ar.edu.undec.adapter.data.piloto.model.PilotoEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CrearPilotoCrud extends CrudRepository<PilotoEntidad, UUID> {
    Boolean existsByFullName(String full_name);
    Boolean existsByShortName(String short_name);
}
