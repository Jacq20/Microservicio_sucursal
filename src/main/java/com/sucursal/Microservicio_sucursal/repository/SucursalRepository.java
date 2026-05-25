package com.sucursal.Microservicio_sucursal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sucursal.Microservicio_sucursal.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByEstadoOperacion(Sucursal.EstadoOperacion estadoOperacion);
}
