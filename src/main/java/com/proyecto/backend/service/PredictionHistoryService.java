package com.proyecto.backend.service;

import com.proyecto.backend.model.PredictionHistory;
import com.proyecto.backend.model.Usuario;
import com.proyecto.backend.repository.PredictionHistoryRepository;
import com.proyecto.backend.service.genericService.GenericService;
import com.proyecto.backend.service.genericService.GenericServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PredictionHistoryService  extends GenericServiceImpl<PredictionHistory, Long> implements GenericService<PredictionHistory, Long> {

    @Autowired
    private PredictionHistoryRepository repository;

    @Override
    public CrudRepository<PredictionHistory, Long>getDao() {
        return repository;
    }

    public List<PredictionHistory> getAllPredictions(){
        return repository.findAllPrediction();
    }

    public List<PredictionHistory> getPredictionUser(Long id){
        return repository.findPredictionHistoriesByUsuId(id);
    }

}
