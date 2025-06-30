package com.example.Gestion_De_Evaluaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
import com.example.Gestion_De_Evaluaciones.repository.CalificacionRepository;

@Service
public class CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;

    public List<Calificacion> listarCalificaciones()
    {
        return calificacionRepository.findAll();
    }

    public Calificacion guardar(Calificacion calificacion)
    {
        return calificacionRepository.save(calificacion);
    }

    public Calificacion findById(int id)
    {
        return calificacionRepository.getReferenceById(id);
    }

    public void deleteById(int id)
    {
        calificacionRepository.deleteById(id);
    }


}
