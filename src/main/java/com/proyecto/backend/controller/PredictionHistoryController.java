package com.proyecto.backend.controller;

import com.proyecto.backend.model.Persona;
import com.proyecto.backend.model.PredictRequest;
import com.proyecto.backend.model.PredictionHistory;
import com.proyecto.backend.model.Usuario;
import com.proyecto.backend.service.PredictService;
import com.proyecto.backend.service.PredictionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app/predictHistory")
public class PredictionHistoryController {

    @Autowired
    private PredictionHistoryService service;

    @Autowired
    private PredictService predictService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/history")
    public ResponseEntity<List<PredictionHistory>> history() {
        return new ResponseEntity<>(service.getAllPredictions(), HttpStatus.OK);
    }

    @GetMapping("/getPredictionUser")
    public ResponseEntity<List<PredictionHistory>> getPredictionUser(@RequestParam Long id) {
        List<PredictionHistory> history = service.getPredictionUser(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/getPredictioId")
    public ResponseEntity<PredictionHistory> history(@RequestParam Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<PredictionHistory> create(@RequestBody PredictionHistory p) {

        PredictRequest request = new PredictRequest();
        String predict = "";
        Double result = 0.0;
        //----->Score<-----//
        request.setAcademicReputationScore(p.getAcademicReputationScore());
        request.setEmployerReputationScore(p.getEmployerReputationScore());
        request.setFacultyStudentScore(p.getFacultyStudentScore());
        request.setCitationsPerFacultyScore(p.getCitationsPerFacultyScore());
        request.setInternationalFacultyScore(p.getInternationalFacultyScore());
        request.setInternationalStudentsScore(p.getInternationalStudentsScore());
        request.setInternationalResearchNetworkScore(p.getInternationalResearchNetworkScore());
        request.setEmploymentOutcomesScore(p.getEmploymentOutcomesScore());
        request.setSustainabilityScore(p.getSustainabilityScore());
        //----->Region<-----//
        request.setRegionAfrica(p.getRegionAfrica());
        request.setRegionAmericas(p.getRegionAmericas());
        request.setRegionAsia(p.getRegionAsia());
        request.setRegionEurope(p.getRegionEurope());
        request.setRegionOceania(p.getRegionOceania());
        request.setRegionNotClassified(p.getRegionNotClassified());
        //----->Size<-----//
        request.setSizeM(p.getSizeL());
        request.setSizeM(p.getSizeM());
        request.setSizeS(p.getSizeS());
        request.setSizeXL(p.getSizeXL());
        //----->Focus<-----//
        request.setFocusFc(p.getFocusCo());
        request.setFocusFc(p.getFocusFc());
        request.setFocusFo(p.getFocusFo());
        request.setFocusSp(p.getFocusSp());
        //----->Res<-----//
        request.setResLo(p.getResHi());
        request.setResLo(p.getResLo());
        request.setResMd(p.getResMd());
        request.setResVh(p.getResVh());
        //----->Status<-----//
        request.setStatusB(p.getStatusA());
        request.setStatusB(p.getStatusB());
        request.setStatusC(p.getStatusC());

        result = predictService.obtenerOverScore(request);
        System.out.println("->" + result);

        p.setOverallScore(result);

        return new ResponseEntity<>(service.save(p), HttpStatus.CREATED);
    }
}
