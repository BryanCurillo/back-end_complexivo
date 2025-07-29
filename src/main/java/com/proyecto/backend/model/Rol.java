package com.proyecto.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rol")
public class Rol implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long rolId;

    @Column
    private String rolNombre;
    @Column
    private String rolDescripcion;

    @Column
    @CreationTimestamp
    private LocalDateTime rolFechaRegistro;

    @JsonIgnore
    @OneToMany(mappedBy = "rolId")
    private List<Usuario> listaUsuarios;
}
