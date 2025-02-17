package ar.edu.undec.adapter.service.piloto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piloto.input.CrearPilotoInput;
import piloto.usecase.crearpilotousecase.CrearPilotoRequestModel;

import java.util.UUID;

@RestController
@RequestMapping("pilotos")
public class CrearPilotoController {

    CrearPilotoInput crearPilotoInput;

    @Autowired
    public CrearPilotoController(CrearPilotoInput crearPilotoInput) {
        this.crearPilotoInput = crearPilotoInput;
    }

    @PostMapping
    public ResponseEntity<?> crearPiloto(@RequestBody CrearPilotoRequestModel crearPilotoRequestModel) {
        try {
            UUID id = crearPilotoInput.crearPiloto(crearPilotoRequestModel);
            if (id != null) {
                return ResponseEntity.created(null).body("El piloto se ha guardado correctamente");
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
