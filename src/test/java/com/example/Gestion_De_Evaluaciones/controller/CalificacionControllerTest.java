package com.example.Gestion_De_Evaluaciones.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
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

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.service.CalificacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CalificacionController.class)
public class CalificacionControllerTest {
    

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalificacionService calificacionService;

    @MockBean
    private com.example.Gestion_De_Evaluaciones.service.EvaluacionService evaluacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Calificacion calificacion;

    @Test
    public void getCalificacionesTest() throws Exception {
        Evaluacion e1 = new Evaluacion(1, "22/06/2025", "Matematica");
        Calificacion c1 = new Calificacion(1, 62, e1);
        Calificacion c2 = new Calificacion(2, 55, e1);
        Mockito.when(calificacionService.listarCalificaciones()).thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/api/calificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nota").value(62))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nota").value(55));
    }


    @Test
    void postCalificacionTest() throws Exception {
        Evaluacion e1 = new Evaluacion(1, "22/06/2025", "Matematica");
        Calificacion c1 = new Calificacion(0, 62, e1);
        Calificacion cGuardada = new Calificacion(1, 62, e1);   

        Mockito.when(calificacionService.guardar(any(Calificacion.class))).thenReturn(cGuardada);

        mockMvc.perform(post("/api/calificaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nota").value(62));
    }


    @Test
    void deleteCalificacionTest() throws Exception {
        int id = 1;
        Mockito.doNothing().when(calificacionService).deleteById(id);
        
        mockMvc.perform(delete("/api/calificaciones/1"))
                .andExpect(status().isOk());
        
        Mockito.verify(calificacionService, times(1)).deleteById(id);
    }
}
