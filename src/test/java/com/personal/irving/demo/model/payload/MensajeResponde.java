package com.personal.irving.demo.model.payload;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MensajeResponde implements Serializable{
    private String mensaje;
    private Object object;
}
