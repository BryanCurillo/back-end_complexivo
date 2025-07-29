package com.proyecto.backend.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictRequest {

    @JsonProperty("Academic_Reputation_Score")
    private double academicReputationScore;

    @JsonProperty("Employer_Reputation_Score")
    private double employerReputationScore;

    @JsonProperty("Faculty_Student_Score")
    private double facultyStudentScore;

    @JsonProperty("Citations_per_Faculty_Score")
    private double citationsPerFacultyScore;

    @JsonProperty("International_Faculty_Score")
    private double internationalFacultyScore;

    @JsonProperty("International_Students_Score")
    private double internationalStudentsScore;

    @JsonProperty("International_Research_Network_Score")
    private double internationalResearchNetworkScore;

    @JsonProperty("Employment_Outcomes_Score")
    private double employmentOutcomesScore;

    @JsonProperty("Sustainability_Score")
    private double sustainabilityScore;

    @JsonProperty("Region_Africa")
    private int regionAfrica;

    @JsonProperty("Region_Americas")
    private int regionAmericas;

    @JsonProperty("Region_Asia")
    private int regionAsia;

    @JsonProperty("Region_Europe")
    private int regionEurope;

    @JsonProperty("Region_Not Classified")
    private int regionNotClassified;

    @JsonProperty("Region_Oceania")
    private int regionOceania;

    @JsonProperty("SIZE_L")
    private int sizeL;

    @JsonProperty("SIZE_M")
    private int sizeM;

    @JsonProperty("SIZE_S")
    private int sizeS;

    @JsonProperty("SIZE_XL")
    private int sizeXL;

    @JsonProperty("FOCUS_CO")
    private int focusCo;

    @JsonProperty("FOCUS_FC")
    private int focusFc;

    @JsonProperty("FOCUS_FO")
    private int focusFo;

    @JsonProperty("FOCUS_SP")
    private int focusSp;

    @JsonProperty("RES._HI")
    private int resHi;

    @JsonProperty("RES._LO")
    private int resLo;

    @JsonProperty("RES._MD")
    private int resMd;

    @JsonProperty("RES._VH")
    private int resVh;

    @JsonProperty("STATUS_A")
    private int statusA;

    @JsonProperty("STATUS_B")
    private int statusB;

    @JsonProperty("STATUS_C")
    private int statusC;

}
