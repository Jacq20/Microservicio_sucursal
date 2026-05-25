package com.sucursal.Microservicio_sucursal.DTO;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SucursalDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "Máximo 200 caracteres")
    private String direccion;

    private List<String> horarios;

    @Size(max = 15, message = "Máximo 15 caracteres")
    private String telefono;

    @Size(max = 100, message = "Máximo 100 caracteres")
    private String idGerente;
}
