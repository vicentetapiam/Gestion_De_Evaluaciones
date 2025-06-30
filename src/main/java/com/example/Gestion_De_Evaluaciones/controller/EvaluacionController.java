package com.example.Gestion_De_Evaluaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.service.EvaluacionService;



@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {
    @Autowired
    private EvaluacionService evaluacionService;

//OBTENER DATOS
    @GetMapping
    public ResponseEntity<List<Evaluacion>> getEvaluaciones()
    {
        List<Evaluacion> evaluaciones=evaluacionService.listarTodos();
        if (evaluaciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(evaluaciones, HttpStatus.OK);
    }

//CREAR
    @PostMapping
    public ResponseEntity<Evaluacion> postEvaluacion(@RequestBody Evaluacion evaluacion){

        Evaluacion nuevo=evaluacionService.findById(evaluacion.getId());
        if (nuevo==null)
        {
            return new ResponseEntity<>(evaluacionService.guardar(evaluacion), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Evaluacion> deleteEvaluacion(@PathVariable int id, Evaluacion evaluacion){

        evaluacionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
