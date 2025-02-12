package ar.edu.undec.adapter.service.piloto.controller;

import ar.edu.undec.adapter.service.piloto.dto.PilotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.input.BuscarPilotoInput;
import piloto.model.Piloto;
import java.util.UUID;

@RestController
@RequestMapping("pilotos")
public class BuscarPilotoController {

    private BuscarPilotoInput buscarPilotoInput;

    @Autowired
    public BuscarPilotoController(BuscarPilotoInput buscarPilotoInput) {
        this.buscarPilotoInput = buscarPilotoInput;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscarPiloto(@PathVariable(name = "id") String stringId) {
        try{
            UUID uuid = UUID.fromString(stringId);
            Piloto piloto = buscarPilotoInput.buscarPiloto(uuid);
            PilotoDto pilotoDto = PilotoDto.factory(piloto.getId(), piloto.getName(), piloto.getSurname(), piloto.getFullName(), piloto.getShortName(), piloto.getPictureUrl());
            return ResponseEntity.ok(pilotoDto);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
