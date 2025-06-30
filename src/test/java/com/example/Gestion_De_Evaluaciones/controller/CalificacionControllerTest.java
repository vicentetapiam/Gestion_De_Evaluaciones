package com.example.Gestion_De_Evaluaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
import com.example.Gestion_De_Evaluaciones.service.CalificacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CalificacionController.class)
public class CalificacionControllerTest {
    

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalificacionService calificacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Calificacion calificacion;

    
}
