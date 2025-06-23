package com.example.Gestion_De_Evaluaciones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calificacion")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = false)
    private float nota;

    @ManyToOne
    @JoinColumn(name= "evaluacionid" )
    @JsonBackReference
    private Evaluacion evaluacion;
}
