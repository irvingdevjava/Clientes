package com.personal.irving.demo.model.payload;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestMensajeResponde {

    @Test
    public void testMensajeResponde() {
        // Arrange (Preparacion)
        MensajeResponde respuesta = MensajeResponde.builder()
                .mensaje("Hola mundo")
                .object("Este es un objeto de prueba")
                .build();

        // Act (Action)

        // Assert (Verificacion)
        assertEquals("Hola mundo", respuesta.getMensaje());
        assertEquals("Este es un objeto de prueba", respuesta.getObject());

    }
}