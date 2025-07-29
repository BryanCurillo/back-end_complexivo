package com.proyecto.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Persona")
public class Persona implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perId;

    @Column(unique = true)
    private String perCedula;

    private String perNombre;
    private String perApellido;
    private String perDireccion;
    private String perTelefono;

    @Column
    @Temporal(TemporalType.DATE)
    private Date perFechaNacimiento;

    @OneToOne(mappedBy = "usuPerId")
    @JsonIgnore // Esta anotación evita que se serialice el campo usuario
    private Usuario usuario;

}
