package com.proyecto.backend.controller;

import com.proyecto.backend.model.Rol;
import com.proyecto.backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/app/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/read")
    public ResponseEntity<List<Rol>> read() {
        return new ResponseEntity<>(rolService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Rol> getById(@PathVariable Long id) {
        return new ResponseEntity<>(rolService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Rol> create(@RequestBody Rol p) {
        return new ResponseEntity<>(rolService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Rol> update(@PathVariable Long id, @RequestBody Rol r) {
        Rol rol = rolService.findById(id);
        if (rol != null) {
            try {

                rol.setRolNombre(r.getRolNombre());
                rol.setRolFechaRegistro(r.getRolFechaRegistro());

                return new ResponseEntity<>(rolService.save(rol), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        rolService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}