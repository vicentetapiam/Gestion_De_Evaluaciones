package com.example.Gestion_De_Evaluaciones.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.repository.EvaluacionRepository;

public class EvaluacionServiceTest {
    
    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGuardar() {
        Evaluacion evaluacion = new Evaluacion(0, "25/06/2025", "Matematica");
        Evaluacion evaluacionGuardada = new Evaluacion(1, "25/06/2025", "Matematica");
        when(evaluacionRepository.save(evaluacion)).thenReturn(evaluacionGuardada);
        
        Evaluacion resultado = evaluacionService.guardar(evaluacion);
        assertThat(resultado.getEvaluacionid()).isEqualTo(1);
        verify(evaluacionRepository).save(evaluacion);
    }
}
