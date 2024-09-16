package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity)
            throws IllegalOperationException {
        log.info("Inicia el proceso de crear una especialidad");
        if (!checkDescription(especialidadEntity.getDescripcion())) {
            throw new IllegalOperationException("La descripcion no cumple con los parametros indicados");
        }
        log.info("Termina el proceso de crear una especialidad");
        return especialidadRepository.save(especialidadEntity);
    }

    private boolean checkDescription(String description) {
        boolean resp = true;
        if (description.isEmpty() || description == null) {
            resp = false;
        } else if (description.length() < 10) {
            resp = false;
        }
        return resp;
    }

}
