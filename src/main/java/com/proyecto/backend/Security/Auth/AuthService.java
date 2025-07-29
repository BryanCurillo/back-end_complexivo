package com.proyecto.backend.Security.Auth;

import com.proyecto.backend.Security.Jwt.JwtService;
import com.proyecto.backend.model.Persona;
import com.proyecto.backend.model.Rol;
import com.proyecto.backend.model.Usuario;
import com.proyecto.backend.repository.PersonaRepository;
import com.proyecto.backend.repository.RolRepository;
import com.proyecto.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;
    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        Usuario user = userRepository.findByUsuNombreUsuario(request.getUsuNombreUsuario())
                .orElse(null);

        if (user == null) {
            return AuthResponse.builder()
                    .success(false)
                    .message("Usuario no encontrado")
                    .build();
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsuNombreUsuario(), request.getUsuContrasena())
            );
        } catch (Exception ex) {
            // No lanzar excepción, retornar objeto indicando error
            return AuthResponse.builder()
                    .success(false)
                    .message("Credenciales incorrectas")
                    .build();
        }
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .success(true)
                .token(token)
                .message("Usuario autenticado exitosamente")
                .build();
    }
//        public AuthResponse login(LoginRequest request) {

//        Usuario user = userRepository.findByUsuNombreUsuario(request.getUsuNombreUsuario())
//                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsuNombreUsuario(), request.getUsuContrasena())
//            );
//        } catch (Exception ex) {
//            ex.printStackTrace(); // Muestra la excepción completa en consola
//            throw new RuntimeException("Error en autenticación: " + ex.getMessage());
//        }
//
//
//        String token = jwtService.getToken(user);
//        return AuthResponse.builder()
//                .token(token)
//                .build();

//    }

    public AuthResponse register(Usuario request) {

        // Obtener la persona gestionada (persistente) por ID
        Persona personaManaged = null;
        if (request.getUsuPerId() != null && request.getUsuPerId().getPerId() != null) {
            personaManaged = personaRepository.findById(request.getUsuPerId().getPerId())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        }


        Usuario user = Usuario.builder()
                .usuId(request.getUsuId())
                .usuNombreUsuario(request.getUsuNombreUsuario())
                .usuContrasena(passwordEncoder.encode(request.getUsuContrasena()))
                .usuCorreo(request.getUsuCorreo())
                .usuEstado(request.getUsuEstado())
                .usuFechaRegistro(request.getUsuFechaRegistro())
                .usuPerId(personaManaged)  // Asignar entidad gestionada aquí//.usuPerId(request.getUsuPerId())
                .rolId(request.getRolId())
                .foto(request.getFoto())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .success(true)
                .message("Usuario registrado exitosamente")
                .build();

    }

    public Usuario update(Usuario request) {
        Usuario user = Usuario.builder()
                .usuId(request.getUsuId())
                .usuNombreUsuario(request.getUsuNombreUsuario())
                .usuContrasena(passwordEncoder.encode(request.getUsuContrasena()))
                .usuCorreo(request.getUsuCorreo())
                .usuEstado(request.getUsuEstado())
                .usuFechaRegistro(request.getUsuFechaRegistro())
                .usuPerId(request.getUsuPerId())
                .rolId(request.getRolId())
                .foto(request.getFoto())
                .build();

        userRepository.save(user);

        return userRepository.save(user);

    }

    public boolean usuarioValido(String user) {
        int cont = userRepository.usuarioUnico(user.trim());

        if (cont > 0) {
            return true;
        } else {
            return false;
        }
    }
}
