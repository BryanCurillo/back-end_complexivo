package com.proyecto.backend.controller;

import com.proyecto.backend.model.Persona;
import com.proyecto.backend.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/personas") // Usar plural para recursos REST
//@CrossOrigin(origins = "*")
public class PersonaController {
    @Autowired
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        List<Persona> personas = personaService.findByAll();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/cedula-unica")
    public ResponseEntity<Boolean> isCedulaUnica(@RequestParam String ci) {
        boolean esUnica = personaService.cedulaUnica(ci);
        return ResponseEntity.ok(esUnica);
    }

    @PostMapping("/create")
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaService.save(persona);
        return ResponseEntity.status(201).body(nuevaPersona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona datosActualizados) {
        Persona personaExistente = personaService.findById(id);
        if (personaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            personaExistente.setPerApellido(datosActualizados.getPerApellido());
            personaExistente.setPerCedula(datosActualizados.getPerCedula());
            personaExistente.setPerDireccion(datosActualizados.getPerDireccion());
            personaExistente.setPerTelefono(datosActualizados.getPerTelefono());
            personaExistente.setPerNombre(datosActualizados.getPerNombre());
            personaExistente.setPerFechaNacimiento(datosActualizados.getPerFechaNacimiento());

            Persona personaActualizada = personaService.save(personaExistente);
            return ResponseEntity.ok(personaActualizada);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
