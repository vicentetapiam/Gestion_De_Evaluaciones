package com.example.Gestion_De_Evaluaciones.service;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

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
        assertThat(resultado.getId()).isEqualTo(1);
        verify(evaluacionRepository).save(evaluacion);
    }

    @Test
    void testListarTodo(){
        Evaluacion e1 = new Evaluacion(1, "23/06/2025", "Ciencias");
        Evaluacion e2 = new Evaluacion(2, "25/10/2025", "Matematica");
        when(evaluacionRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Evaluacion> resultado = evaluacionService.listarTodos();
        assertThat(resultado).hasSize(2).contains(e1, e2);
        verify(evaluacionRepository).findAll();
    }

    @Test
    void findByIdTest(){
        Evaluacion e1 = new Evaluacion(1, "23/06/2025", "Ciencias");
        when(evaluacionRepository.findById(e1.getId())).thenReturn(e1);

        Evaluacion resultado = evaluacionService.findById(e1.getId());
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getFecha()).isEqualTo("23/06/2025");
        assertThat(resultado.getRamo()).isEqualTo("Ciencias");
        verify(evaluacionRepository).findById(1);
    }



    @Test
    void deleteTest(){
        int id = 1;
        doNothing().when(evaluacionRepository).deleteById(id);
        evaluacionService.deleteById(id);
        verify(evaluacionRepository, times(1)).deleteById(id);
    }
}

