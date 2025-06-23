package com.example.Gestion_De_Evaluaciones.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
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


    @Test
    void guardarTest(){
        Calificacion calificacion = new Calificacion(1, 5.5, null);
        Calificacion calificacionGuardada = new Calificacion(1, 5.5, null);
    }
}
