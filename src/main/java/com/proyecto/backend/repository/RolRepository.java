package com.proyecto.backend.repository;

import com.proyecto.backend.model.Rol;
import com.proyecto.backend.repository.genericRepository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends GenericRepository<Rol, Long> {

}