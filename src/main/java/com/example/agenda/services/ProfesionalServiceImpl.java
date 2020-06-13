package com.example.agenda.services;

import com.example.agenda.models.Profesional;
import com.example.agenda.repositories.ProfesionalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {

    private final ProfesionalRepository profesionalRepo;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepo) {
        this.profesionalRepo = profesionalRepo;
    }

    @Override
    public List<Profesional> listarProfesionales() {
        return profesionalRepo.findAll();
    }

    @Override
    public Profesional obtenerProfesional(Long id) {
        return profesionalRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("profesional", id));
    }

    @Override
    public Profesional crearProfesional(Profesional nuevoProfesional) {
        return profesionalRepo.save(nuevoProfesional);
    }

    @Override
    public Profesional actualizarProfesional(Long id, Profesional actualProfesional) {
        return profesionalRepo.findById(id)
                .map(profesional -> {
                    profesional.setDocumento(actualProfesional.getDocumento());
                    profesional.setNombre(actualProfesional.getNombre());
                    profesional.setApellido(actualProfesional.getApellido());
                    profesional.setCorreo(actualProfesional.getCorreo());
                    profesional.setTelefono(actualProfesional.getTelefono());
                    profesional.setFechaNacimiento(actualProfesional.getFechaNacimiento());
                    return profesionalRepo.save(profesional);
                })
                .orElseThrow(() -> new EntityNotFoundException("profesional", id));
    }

    @Override
    public void eliminarProfesional(Long id) {
        if (profesionalRepo.existsById(id)) {
            profesionalRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("profesional", id);
        }
    }
}
