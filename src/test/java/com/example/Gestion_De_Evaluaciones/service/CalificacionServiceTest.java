package com.example.Gestion_De_Evaluaciones.service;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.repository.CalificacionRepository; // <-- Importa la List correcta

// El error en la línea 41 es que estás importando y usando la clase incorrecta para List. 
// Estás usando: org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List
// Deberías usar: java.util.List
// Solución: Cambia la importación a: import java.util.List;





public class CalificacionServiceTest {

    @Mock
    private CalificacionRepository calificacionRepository;

    @InjectMocks
    private CalificacionService calificacionService;

    @BeforeEach
    void setp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarCalificacionesVacioTest() {
        when(calificacionRepository.findAll()).thenReturn(java.util.Collections.emptyList());

        List<Calificacion> resultado = calificacionService.listarCalificaciones();
        assertThat(resultado).isEmpty();
        verify(calificacionRepository).findAll();
    }

    @Test
    void listarCalificacionesConUnElementoTest() {
        Evaluacion e1 = new Evaluacion(14, "30/06/2025", "Arte");
        Calificacion c1 = new Calificacion(6, 95, e1);
        when(calificacionRepository.findAll()).thenReturn(Arrays.asList(c1));

        List<Calificacion> resultado = calificacionService.listarCalificaciones();
        assertThat(resultado).hasSize(1).contains(c1);
        verify(calificacionRepository).findAll();
    }

    @Test
    void listarCalificacionesTest(){
        Evaluacion e1 = new Evaluacion(10, "26/06/2025", "Ingles");
        Calificacion c1 = new Calificacion(1, 65, e1);
        Calificacion c2 = new Calificacion(2, 66, e1);
        when(calificacionRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Calificacion> resultado = calificacionService.listarCalificaciones();
        assertThat(resultado).hasSize(2).contains(c1, c2);
        verify(calificacionRepository).findAll();
    }

    @Test
    void guardarCalificacionTest() {
        Evaluacion e1 = new Evaluacion(11, "27/06/2025", "Matematicas");
        Calificacion c = new Calificacion(3, 90, e1);
        when(calificacionRepository.save(c)).thenReturn(c);

        Calificacion resultado = calificacionService.guardar(c);
        assertThat(resultado).isEqualTo(c);
        verify(calificacionRepository).save(c);
    }

    @Test
    void findByIdTest() {
        Evaluacion e1 = new Evaluacion(12, "28/06/2025", "Historia");
        Calificacion c = new Calificacion(4, 75, e1);
        when(calificacionRepository.getReferenceById(4)).thenReturn(c);

        Calificacion resultado = calificacionService.findById(4);
        assertThat(resultado).isEqualTo(c);
        verify(calificacionRepository).getReferenceById(4);
    }

    @Test
    void deleteCalificacionTest() {
        Evaluacion e1 = new Evaluacion(13, "29/06/2025", "Ciencias");
        Calificacion c = new Calificacion(5, 80, e1);

        calificacionService.delete(c);
        verify(calificacionRepository).delete(c);
    }
}
