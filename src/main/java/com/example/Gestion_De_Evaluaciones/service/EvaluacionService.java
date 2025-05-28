package com.example.Gestion_De_Evaluaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> listarTodos()
    {
        return evaluacionRepository.findAll();
    }

    public Evaluacion guardar(Evaluacion evaluacion)
    {
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion evaluacionxid(int id)
    {
        return evaluacionRepository.getReferenceById(id);
    }

    public Evaluacion findById(int id)
    {
        return evaluacionRepository.findById(id);
    }

    public void delete(Evaluacion evaluacion)
    {
        evaluacionRepository.delete(evaluacion);
    }
}
