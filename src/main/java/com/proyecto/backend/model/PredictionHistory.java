package com.proyecto.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PredictionHistory")
public class PredictionHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long predictId;

    private String institutionName;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime predictionDate;

    private Double overallScore;

    @ManyToOne
    @JoinColumn(name = "usuId", referencedColumnName = "usuId")
    private Usuario usuId;

    private double academicReputationScore;
    private double employerReputationScore;
    private double facultyStudentScore;
    private double citationsPerFacultyScore;
    private double internationalFacultyScore;
    private double internationalStudentsScore;
    private double internationalResearchNetworkScore;
    private double employmentOutcomesScore;
    private double sustainabilityScore;

    private int regionAfrica;
    private int regionAmericas;
    private int regionAsia;
    private int regionEurope;
    private int regionNotClassified;
    private int regionOceania;

    private int sizeL;
    private int sizeM;
    private int sizeS;
    private int sizeXL;

    private int focusCo;
    private int focusFc;
    private int focusFo;
    private int focusSp;

    private int resHi;
    private int resLo;
    private int resMd;
    private int resVh;

    private int statusA;
    private int statusB;
    private int statusC;


}
