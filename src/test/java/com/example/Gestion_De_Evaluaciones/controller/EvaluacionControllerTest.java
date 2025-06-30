package com.example.Gestion_De_Evaluaciones.controller;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.service.EvaluacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EvaluacionController.class)
public class EvaluacionControllerTest {
    

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private EvaluacionService evaluacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Evaluacion evaluacion;
/* 
    @BeforeEach
    void setUp() {
        evaluacion = new Evaluacion();
        evaluacion.setEvaluacionid(1);
        evaluacion.setFecha("26/06/2025");
        evaluacion.setRamo("Matematica");
    }
*/
    @Test
    void getEvaluacionesTest() throws Exception {
        Evaluacion e1 = new Evaluacion(1, "22/06/2025", "Matematica");
        Evaluacion e2 = new Evaluacion(2, "23/06/2025", "Lenguaje");    
        Mockito.when(evaluacionService.listarTodos()).thenReturn(List.of(e1, e2));

        mockMvc.perform(get("/api/evaluacion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ramo").value("Matematica"))
                .andExpect(jsonPath("$[1].ramo").value("Lenguaje"));
    }


    @Test 
    void getEvaluacionSinContenidoTest() throws Exception {
        when(evaluacionService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/evaluacion"))
                .andExpect(status().isNoContent());
    }

    @Test
    void postEvaluacionTest() throws Exception {
        Evaluacion e1 = new Evaluacion(0, "30/06/2025", "Historia");
        Evaluacion eGuardada = new Evaluacion(3, "30/06/2025", "Historia");

        Mockito.when(evaluacionService.guardar(any(Evaluacion.class))).thenReturn(eGuardada);

        mockMvc.perform(post("/api/evaluacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(e1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.fecha").value("30/06/2025"))
                .andExpect(jsonPath("$.ramo").value("Historia"));
    }



    @Test 
    public void deleteEvaluacionTest() throws Exception{
        int id = 1;
        doNothing().when(evaluacionService).deleteById(id);

        mockMvc.perform(delete("/api/evaluacion/1"))
            .andExpect(status().isOk());

        verify(evaluacionService, times(1)).deleteById(id);
    }
}
