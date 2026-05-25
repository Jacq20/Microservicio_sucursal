package com.sucursal.Microservicio_sucursal.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSucursal;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 200, nullable = false)
    private String direccion;

    @ElementCollection
    private List<String> horarios;

    @Column(length = 15)
    private String telefono;

    @Enumerated(EnumType.STRING)
    private EstadoOperacion estadoOperacion;

    @Column(length = 100)
    private String idGerente;

    public enum EstadoOperacion {
        ACTIVA, INACTIVA, EN_MANTENIMIENTO
    }
}
