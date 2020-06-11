package com.example.agenda.services;

import com.example.agenda.models.Profesional;
import java.util.List;

public interface ProfesionalService {
    List<Profesional> listarProfesionales();
    Profesional obtenerProfesional(Long id);
    Profesional crearProfesional(Profesional nuevoProfesional);
    Profesional actualizarProfesional(Long id, Profesional actualProfesional);
    void eliminarProfesional(Long id);
}
