package com.example.Gestion_De_Evaluaciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluacion")

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  evaluacionid;

    @Column(length = 10, nullable = false)
    private String fecha;

    @Column(length = 50, nullable = false)
    private String ramo;
/* 
    @OneToMany(mappedBy = "evaluacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Calificacion> notas;
    */





}
