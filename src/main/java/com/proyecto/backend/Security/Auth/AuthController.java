package com.proyecto.backend.Security.Auth;

import com.proyecto.backend.model.Persona;
import com.proyecto.backend.model.Rol;
import com.proyecto.backend.model.Usuario;
import com.proyecto.backend.service.PersonaService;
import com.proyecto.backend.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @Autowired
    private final PersonaService personaService;

    @Autowired
    private RolService rolService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario request) {
        if (request.getUsuId() != null && request.getUsuId() == 0L) {
            request.setUsuId(null);
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/usuarioValido")
    public ResponseEntity<Boolean> usuarioValido(@RequestParam String user) {
        return new ResponseEntity<>(authService.usuarioValido(user), HttpStatus.OK);
    }

    //PERSONA
    @GetMapping("/cedula-unica")
    public ResponseEntity<Boolean> isCedulaUnica(@RequestParam String ci) {
        boolean esUnica = personaService.cedulaUnica(ci);
        return ResponseEntity.ok(esUnica);
    }

    @PostMapping("/create")
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        if (persona.getPerId() != null && persona.getPerId() == 0L) {
            persona.setPerId(null);
        }

        Persona nuevaPersona = personaService.save(persona);
        return ResponseEntity.status(201).body(nuevaPersona);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Rol>> read() {
        return new ResponseEntity<>(rolService.findByAll(), HttpStatus.OK);
    }
}
