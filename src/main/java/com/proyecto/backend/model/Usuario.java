package com.proyecto.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuId")
    private Long usuId;


    @Column(unique = true)
    private String usuNombreUsuario;

    @Column
    private String usuContrasena;

    @Column(name = "usuCorreo")
    private String usuCorreo;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @Column
    private int usuEstado;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime usuFechaRegistro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuPerId", referencedColumnName = "perId")
//    @JsonIgnore // Esta anotación evita que se serialice el campo usuPerId
    private Persona usuPerId;


    @ManyToOne
    @JoinColumn(name = "rolId", referencedColumnName = "rolId")
    private Rol rolId;

    @JsonIgnore
    @OneToMany(mappedBy = "predictId")
    private List<PredictionHistory> historyList;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" +rolId.getRolNombre()));
    }

    @Override
    public String getPassword() {
        return this.getUsuContrasena();
    }

    @Override
    public String getUsername() {
        return this.usuNombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
