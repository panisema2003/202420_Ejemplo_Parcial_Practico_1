package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public MedicoEntity createMedico(MedicoEntity medicoEntity)
            throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia el proceso de crear un médico");
        if (!validateRegistro(medicoEntity.getRegistroMedico())) {
            throw new IllegalOperationException("El formato del registro no es valido");
        }
        log.info("Termina proceso de creación de un médico");
        return medicoRepository.save(medicoEntity);

    }

    private boolean validateRegistro(String registro) {
        boolean resp = true;
        if (registro == null || registro.isEmpty()) {
            resp = false;
        } else if (registro.length() > 2) {
            if (!registro.substring(0, 2).equals("RM")) {
                resp = false;
            }
        }
        return resp;
    }

}
