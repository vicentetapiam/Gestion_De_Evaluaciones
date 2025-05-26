package com.example.Gestion_De_Evaluaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion_De_Evaluaciones.model.Evaluacion;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    
    List<Evaluacion> findAll();

    @SuppressWarnings("unchecked")
    Evaluacion save(Evaluacion evaluacion);

    Evaluacion findById(int id);

    Evaluacion getReferenceById(Integer id);

    void delete(Evaluacion evaluacion);

}
