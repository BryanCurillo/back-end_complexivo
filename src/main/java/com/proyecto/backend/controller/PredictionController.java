package com.proyecto.backend.controller;

import com.proyecto.backend.model.PredictRequest;
import com.proyecto.backend.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/predict")
public class PredictionController {

    @Autowired
    private  PredictService predictService;

    @GetMapping("/prueba")
    public ResponseEntity<String> prueba() {
        PredictRequest request = new PredictRequest();

        request.setAcademicReputationScore(85.2);
        request.setEmployerReputationScore(88.4);
        request.setFacultyStudentScore(70.1);
        request.setCitationsPerFacultyScore(92.3);
        request.setInternationalFacultyScore(60.5);
        request.setInternationalStudentsScore(55.0);
        request.setInternationalResearchNetworkScore(45.3);
        request.setEmploymentOutcomesScore(75.4);
        request.setSustainabilityScore(50.2);
        request.setRegionAmericas(1);
        request.setRegionAsia(0);
        request.setRegionEurope(0);
        request.setRegionNotClassified(0);
        request.setRegionOceania(0);
        request.setSizeM(1);
        request.setSizeS(0);
        request.setSizeXL(0);
        request.setFocusFc(1);
        request.setFocusFo(0);
        request.setFocusSp(0);
        request.setResLo(0);
        request.setResMd(1);
        request.setResVh(0);
        request.setStatusB(1);
        request.setStatusC(0);

        return new ResponseEntity<>(predictService.obtenerPrediccion(request), HttpStatus.OK);
    }



    @PostMapping("/prediction")
    public ResponseEntity<String> prueba(@RequestBody PredictRequest request) {
        return new ResponseEntity<>(predictService.obtenerPrediccion(request), HttpStatus.OK);
    }

}

