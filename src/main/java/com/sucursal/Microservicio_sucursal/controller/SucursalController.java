package com.sucursal.Microservicio_sucursal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sucursal.Microservicio_sucursal.DTO.SucursalDTO;
import com.sucursal.Microservicio_sucursal.model.Sucursal;
import com.sucursal.Microservicio_sucursal.service.SucursalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {
    
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> getSucursales() {
        List<Sucursal> lista = sucursalService.consultarSucursales();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
        @GetMapping("/{id}")
    public ResponseEntity<?> getSucursal(@PathVariable Long id) {
        Sucursal buscada = sucursalService.buscarPorId(id).orElse(null);
        if (buscada == null) {
            return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buscada, HttpStatus.OK);
    }

     @GetMapping("/{id}/horarios")
    public ResponseEntity<?> getHorarios(@PathVariable Long id) {
        List<String> horarios = sucursalService.obtenerHorarios(id).orElse(null);
        if (horarios == null) {
            return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postSucursal(@Valid @RequestBody SucursalDTO dto) {
        try {
            Sucursal nueva = sucursalService.gestionarSucursal(dto);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

     @PutMapping("/{id}/horarios")
    public ResponseEntity<?> putHorarios(
            @PathVariable Long id,
            @RequestBody List<String> horarios) {
        try {
            Sucursal actualizada = sucursalService.actualizarHorarios(id, horarios).orElse(null);
            if (actualizada == null) {
                return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/alta")
    public ResponseEntity<?> darAlta(@PathVariable Long id) {
        try {
            Sucursal reactivada = sucursalService.darAltaSucursal(id).orElse(null);
            if (reactivada == null) {
                return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(reactivada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
