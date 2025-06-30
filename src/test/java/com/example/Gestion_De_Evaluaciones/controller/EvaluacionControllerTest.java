package com.example.Gestion_De_Evaluaciones.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
    public void getEvaluacionesTest() throws Exception {
        Evaluacion e1 = new Evaluacion(1, "22/06/2025", "Matematica");
        Evaluacion e2 = new Evaluacion(2, "23/06/2025", "Lenguaje");    
        Mockito.when(evaluacionService.listarTodos()).thenReturn(List.of(e1, e2));

        mockMvc.perform(get("/api/evaluacion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ramo").value("Matematica"))
                .andExpect(jsonPath("$[1].ramo").value("Lenguaje"));
    }


    @Test
    void postEvaluacionTest() throws Exception {
        Evaluacion e1 = new Evaluacion(0, "30/06/2025", "Historia");
        Evaluacion eGuardada = new Evaluacion(3, "30/06/2025", "Historia");

        Mockito.when(evaluacionService.guardar(any(Evaluacion.class))).thenReturn(eGuardada);

        mockMvc.perform(post("/api/evaluacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(e1)))
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$.evaluacionid").value(3))
                .andExpect(jsonPath("$.fecha").value("30/06/2025"))
                .andExpect(jsonPath("$.ramo").value("Historia"));
    }


    @Test
    void deleteEvaluacionTest() throws Exception {
        int evaluacionId = 1;
        Evaluacion evaluacion = new Evaluacion(evaluacionId, "25/06/2025", "Ciencias");

        Mockito.when(evaluacionService.findById(evaluacionId)).thenReturn(evaluacion);
        Mockito.doNothing().when(evaluacionService).delete(evaluacion);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .delete("/api/evaluacion/{evaluacionid}", evaluacionId));
                //.andExpect(status().isOk());
    }



}
