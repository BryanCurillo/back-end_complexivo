package com.proyecto.backend.service;

import com.proyecto.backend.model.Rol;
import com.proyecto.backend.repository.RolRepository;
import com.proyecto.backend.service.genericService.GenericService;
import com.proyecto.backend.service.genericService.GenericServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolService extends GenericServiceImpl<Rol, Long> implements GenericService<Rol, Long> {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public CrudRepository<Rol, Long> getDao() {
        return rolRepository;
    }


}