package com.example.Gestion_De_Evaluaciones.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.repository.CalificacionRepository;

public class CalificacionServiceTest {
    
    @Mock
    private CalificacionRepository calificacionRepository;

    @InjectMocks
    private CalificacionService calificacionService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    private Calificacion crearCalificacion(){
        List<Calificacion> notas = new List<Calificacion>() {
            
        };
        Evaluacion evaluacion = new Evaluacion()
        evaluacion.setEvaluacionid(1);
        evaluacion.setFecha(2025-06-01);
        evaluacion.setRamo("Matematica");
        evaluacion.setNotas(null);
    }

    @Test
    void guardarTest(){
        Evaluacion evaluacion = new Evaluacion(0, null, null, null)
        Calificacion calificacion = new Calificacion(0, 0, null)

}
