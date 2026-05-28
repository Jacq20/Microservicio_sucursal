package com.sucursal.Microservicio_sucursal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sucursal.Microservicio_sucursal.DTO.SucursalDTO;
import com.sucursal.Microservicio_sucursal.model.Sucursal;
import com.sucursal.Microservicio_sucursal.model.UsuarioDTO;
import com.sucursal.Microservicio_sucursal.repository.SucursalRepository;

@Service
public class SucursalService {
    
    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Sucursal gestionarSucursal(SucursalDTO dto) {
        try {
            // Verificamos que el gerente existe en ms_usuarios
            String url = "http://localhost:8089/api/usuarios/" + dto.getIdGerente();
            UsuarioDTO gerente = restTemplate.getForObject(url, UsuarioDTO.class);

            if (gerente == null) {
                throw new RuntimeException("El gerente no existe en el sistema");
            }

            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(dto.getNombre());
            sucursal.setDireccion(dto.getDireccion());
            sucursal.setHorarios(dto.getHorarios());
            sucursal.setTelefono(dto.getTelefono());
            sucursal.setIdGerente(dto.getIdGerente());
            sucursal.setEstadoOperacion(Sucursal.EstadoOperacion.ACTIVA);
            return sucursalRepository.save(sucursal);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Sucursal> consultarSucursales() {
        return sucursalRepository.findAll();
    }

    public Optional<Sucursal> buscarPorId(Long id) {
        return sucursalRepository.findById(id);
    }

    public Optional<List<String>> obtenerHorarios(Long id) {
        return sucursalRepository.findById(id).map(Sucursal::getHorarios);
    }

    public Optional<Sucursal> actualizarHorarios(Long id, List<String> horarios) {
        try {
            Optional<Sucursal> sucursal = sucursalRepository.findById(id);
            sucursal.ifPresent(s -> {
                s.setHorarios(horarios);
                sucursalRepository.save(s);
            });
            return sucursal;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Optional<Sucursal> darAltaSucursal(Long id) {
        try {
            Optional<Sucursal> sucursal = sucursalRepository.findById(id);
            sucursal.ifPresent(s -> {
                s.setEstadoOperacion(Sucursal.EstadoOperacion.ACTIVA);
                sucursalRepository.save(s);
            });
            return sucursal;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
