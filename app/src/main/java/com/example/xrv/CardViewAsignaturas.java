package com.example.xrv;

//Clase sencilla encargada de definir las propiedades de la card Asignaturas
public class CardViewAsignaturas {

    String asAsignatura;
    String  asProfesor;

    //Método constructor
    public CardViewAsignaturas(String asAsignatura, String asProfesor) {
        this.asAsignatura = asAsignatura;
        this.asProfesor = asProfesor;
    }

    // Método que devuelve la asignatura
    public String getAsAsignatura() {
        return asAsignatura;
    }

    // Método que permite modificar la asignatura
    public void setAsAsignatura(String prAsignatura) {
        this.asAsignatura = asAsignatura;
    }

    // Método que devuelve el profesor
    public String getAsProfesor() {
        return asProfesor;
    }

    // Método que permite modificar el profesor
    public void setAsProfesor(String asProfesor) {
        this.asProfesor = asProfesor;
    }
}
