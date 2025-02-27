package ar.edu.undec.adapter.service.piloto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import piloto.input.BuscarPilotoInput;
import piloto.input.CrearPilotoInput;
import piloto.output.BuscarPilotoRepository;
import piloto.output.CrearPilotoRepository;
import piloto.usecase.buscarpilotousecase.BuscarPilotoUseCase;
import piloto.usecase.crearpilotousecase.CrearPilotoUseCase;

@Configuration
public class PilotoBeanConfig {

    @Bean
    public CrearPilotoInput crearPilotoInput(CrearPilotoRepository crearPilotoRepository) {
        return new CrearPilotoUseCase(crearPilotoRepository);
    }

    @Bean
    public BuscarPilotoInput buscarPilotoInput(BuscarPilotoRepository buscarPilotoRepository) {
        return new BuscarPilotoUseCase(buscarPilotoRepository);
    }
}
