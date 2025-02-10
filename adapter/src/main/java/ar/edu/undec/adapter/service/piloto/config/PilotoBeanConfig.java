package ar.edu.undec.adapter.service.piloto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import piloto.input.CrearPilotoInput;
import piloto.output.CrearPilotoRepository;
import piloto.usecase.crearpilotousecase.CrearPilotoUseCase;

@Configuration
public class PilotoBeanConfig {

    @Bean
    public CrearPilotoInput crearPilotoInput(CrearPilotoRepository crearPilotoRepository) {
        return new CrearPilotoUseCase(crearPilotoRepository);
    }
}