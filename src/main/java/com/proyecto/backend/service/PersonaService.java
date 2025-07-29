package com.proyecto.backend.service;

import com.proyecto.backend.model.Persona;
import com.proyecto.backend.model.Rol;
import com.proyecto.backend.repository.PersonaRepository;
import com.proyecto.backend.repository.RolRepository;
import com.proyecto.backend.service.genericService.GenericService;
import com.proyecto.backend.service.genericService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService extends GenericServiceImpl<Persona, Long> implements GenericService<Persona, Long> {

    @Autowired
    public PersonaRepository personaRepository;

    @Override
    public CrudRepository<Persona, Long> getDao() {
        return personaRepository;
    }


    public boolean cedulaUnica(String ci) {
        int cont = personaRepository.cedulaUnica(ci.trim());

        if (cont > 0) {
            return false;
        } else {
            return true;
        }
    }
}