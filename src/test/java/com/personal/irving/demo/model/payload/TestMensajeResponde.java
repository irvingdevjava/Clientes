package com.personal.irving.demo.model.payload;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("EI_EXPOSE_REP")
public class TestMensajeResponde {

    @Test
    public void testMensajeResponde() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .mensaje("Hola mundo")
                .object("Este es un objeto de prueba")
                .build();

        assertEquals("Hola mundo", respuesta.getMensaje());
        assertEquals("Este es un objeto de prueba", respuesta.getObject());
    }


    
    @Test
    public void testMensajeRespondeSinMensaje() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .object("Este es un objeto de prueba")
                .build();

        assertNull(respuesta.getMensaje());
        assertEquals("Este es un objeto de prueba", respuesta.getObject());
    }

    


    @Test
    public void testMensajeRespondeSinObject() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .mensaje("Hola mundo")
                .build();


        
        assertEquals("Hola mundo", respuesta.getMensaje());
        assertNull(respuesta.getObject());
    }
    

    @Test
    public void testMensajeRespondeConMensajeYObjectNulos() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .build();

        assertNull(respuesta.getMensaje());
        assertNull(respuesta.getObject());
    }

    @Test
    public void testMensajeRespondeConMensajeVacio() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .mensaje("")
                .object("Este es un objeto de prueba")
                .build();

        assertEquals("", respuesta.getMensaje());
        assertEquals("Este es un objeto de prueba", respuesta.getObject());
    }

    @Test
    public void testMensajeRespondeConObjectVacio() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .mensaje("Hola mundo")
                .object("")
                .build();

        assertEquals("Hola mundo", respuesta.getMensaje());
        assertEquals("", respuesta.getObject());
    }

    @Test
    public void testMensajeRespondeConObjectAndObjetoVacio() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .mensaje("")
                .object("")
                .build();

        assertEquals("", respuesta.getMensaje());
        assertEquals("", respuesta.getObject());
    }

    @Test
    public void testToStringBuilder() {
        MensajeResponde respuesta = MensajeResponde.builder()
                .mensaje("Hola mundo")
                .object("Este es un objeto de prueba")
                .build();

        String toString = respuesta.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("mensaje=Hola mundo"));
        assertTrue(toString.contains("object=Este es un objeto de prueba"));
    }

    //Pendiente
    @Test
    public void testEquals() {
        MensajeResponde respuesta1 = MensajeResponde.builder()
                .mensaje("Hola mundo")
                .object("Este es un objeto de prueba")
                .build();

        MensajeResponde respuesta2 = MensajeResponde.builder()
                .mensaje("Hola mundo")
                .object("Este es un objeto de prueba")
                .build();

        MensajeResponde respuesta3 = MensajeResponde.builder()
                .mensaje("Hola")
                .object("Prueba")
                .build();


        assertEquals(respuesta1, respuesta2);
        assertNotEquals(respuesta1, respuesta3);
        assertNotEquals(respuesta1, null);
        assertNotEquals(respuesta1, new Object());
    }

    
    @Test
    public void testSetMensaje() {
        MensajeResponde respuesta = new MensajeResponde(null, null);
        respuesta.setMensaje("Nuevo mensaje");
        assertEquals("Nuevo mensaje", respuesta.getMensaje());
    }
        
    @Test
    public void testSetObject() {
        MensajeResponde respuesta = new MensajeResponde(null, null);
        respuesta.setObject("Nuevo objeto");
        assertEquals("Nuevo objeto", respuesta.getObject());
    }
    
    @Test
    public void testCanEqual() {
        MensajeResponde respuesta1 = new MensajeResponde(null, null);
        MensajeResponde respuesta2 = new MensajeResponde(null, null);
        assertTrue(respuesta1.canEqual(respuesta2));
    }

    @Test
    public void testHashCodeConsistency() {
        MensajeResponde respuesta = new MensajeResponde(null,null);
        respuesta.setMensaje("Hola mundo");
        respuesta.setObject("Este es un objeto de prueba");

        int initialHashCode = respuesta.hashCode();
        assertEquals(initialHashCode, respuesta.hashCode());
        assertEquals(initialHashCode, respuesta.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        MensajeResponde respuesta1 = new MensajeResponde(null,null);
        respuesta1.setMensaje("Hola mundo");
        respuesta1.setObject("Este es un objeto de prueba");

        MensajeResponde respuesta2 = new MensajeResponde(null,null);
        respuesta2.setMensaje("Hola mundo");
        respuesta2.setObject("Este es un objeto de prueba");

        assertEquals(respuesta1.hashCode(), respuesta2.hashCode());
    }

    @Test
    public void testHashCodeInequality() {
        MensajeResponde respuesta1 = new MensajeResponde(null,null);
        respuesta1.setMensaje("Hola mundo");
        respuesta1.setObject("Este es un objeto de prueba");

        MensajeResponde respuesta2 = new MensajeResponde(null,null);
        respuesta2.setMensaje("Hola");
        respuesta2.setObject("Prueba");

        System.out.println("respuesta1: mensaje=" + respuesta1.getMensaje() + ", object=" + respuesta1.getObject() + ", hashCode=" + respuesta1.hashCode());
        System.out.println("respuesta2: mensaje=" + respuesta2.getMensaje() + ", object=" + respuesta2.getObject() + ", hashCode=" + respuesta2.hashCode());

        assertNotEquals(respuesta1.hashCode(), respuesta2.hashCode());
    }

    @Test
    public void testHashCodeEqualsConsistency() {
        MensajeResponde respuesta1 = new MensajeResponde(null, null);
        respuesta1.setMensaje("Hola mundo");
        respuesta1.setObject("Este es un objeto de prueba");

        MensajeResponde respuesta2 = new MensajeResponde(null, null);
        respuesta2.setMensaje("Hola mundo");
        respuesta2.setObject("Este es un objeto de prueba");

        assertTrue(respuesta1.equals(respuesta2));
        assertEquals(respuesta1.hashCode(), respuesta2.hashCode());

        MensajeResponde respuesta3 = new MensajeResponde(null, null);
        respuesta3.setMensaje("Hola");
        respuesta3.setObject("Prueba");

        assertFalse(respuesta1.equals(respuesta3));
        assertNotEquals(respuesta1.hashCode(), respuesta3.hashCode());
    }
    
       
}
