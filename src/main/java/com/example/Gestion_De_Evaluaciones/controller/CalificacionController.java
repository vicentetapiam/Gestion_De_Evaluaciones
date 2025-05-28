package com.example.Gestion_De_Evaluaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gestion_De_Evaluaciones.model.Calificacion;
import com.example.Gestion_De_Evaluaciones.model.Evaluacion;
import com.example.Gestion_De_Evaluaciones.service.CalificacionService;
import com.example.Gestion_De_Evaluaciones.service.EvaluacionService;



@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {
    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private EvaluacionService evaluacionService;


//OBTENER DATOS
    @GetMapping
    public ResponseEntity<List<Calificacion>> getCalificaciones()
    {
        List<Calificacion> calificaciones = calificacionService.calificaciones();
        if(calificaciones.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(calificaciones,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Calificacion> postCalificacion(@RequestBody Calificacion calificacion)
    {
        int id_link=calificacion.getEvaluacion().getEvaluacionid();
        
        Evaluacion evaluacion=evaluacionService.evaluacionxid(id_link);
        if (evaluacion!=null)
        {
            calificacion.setEvaluacion(evaluacion);
        }

        Calificacion nuevo = calificacionService.guardar(calificacion);
        if (nuevo==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(nuevo,HttpStatus.CREATED);
    }


//REMPLEAZAR DATOS
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> putEvaluacion(@PathVariable int id, @RequestBody Calificacion calificacionMod){
        Calificacion buscado = calificacionService.findById(calificacionMod.getId());
        if(buscado == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            buscado.setNota(calificacionMod.getNota());
            buscado.setEvaluacion(calificacionMod.getEvaluacion());
            return new ResponseEntity<>(calificacionService.guardar(calificacionMod),HttpStatus.OK);
        }
    } 

        @DeleteMapping("/{id}")
    public ResponseEntity<Calificacion>deleteSistema(@PathVariable int id, Calificacion calificacion){
        Calificacion buscado = calificacionService.findById(calificacion.getId());
        if(buscado == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            calificacionService.delete(buscado);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    
}
