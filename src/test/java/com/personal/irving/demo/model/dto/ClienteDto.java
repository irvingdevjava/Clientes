package com.personal.irving.demo.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ClienteDto implements Serializable{
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDateTime fechaRegistro;

    
    
}
