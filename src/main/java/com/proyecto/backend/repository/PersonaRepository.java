package com.proyecto.backend.repository;

import com.proyecto.backend.model.Persona;
import com.proyecto.backend.model.Rol;
import com.proyecto.backend.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonaRepository extends GenericRepository<Persona, Long> {

    @Query(value = "SELECT COUNT(*) FROM persona WHERE per_cedula = :ci", nativeQuery = true)
    int cedulaUnica(@Param("ci") String ci);
}