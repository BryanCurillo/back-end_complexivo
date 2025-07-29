package com.proyecto.backend.repository;

import com.proyecto.backend.model.PredictionHistory;
import com.proyecto.backend.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionHistoryRepository extends GenericRepository<PredictionHistory, Long> {
    @Query("SELECT p FROM PredictionHistory p ORDER BY p.overallScore DESC")
    List<PredictionHistory> findAllPrediction();

    @Query("SELECT p FROM PredictionHistory p WHERE p.usuId.usuId = :id ORDER BY p.overallScore desc ")
    List<PredictionHistory> findPredictionHistoriesByUsuId(@Param("id") Long id);

}
