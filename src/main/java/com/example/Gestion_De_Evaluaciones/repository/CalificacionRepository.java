package com.example.Gestion_De_Evaluaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer>{

    List<Calificacion> findAll();
    
    @SuppressWarnings("unchecked")
    Calificacion save(Calificacion calificacion);

    Calificacion findById(int id);

    Calificacion getReferenceById(Integer id);

    void delete(Calificacion calificacion);
}
