package com.example.xrv;

public class CardViewAsignaturas {

    String asAsignatura;
    String  asProfesor;

    public CardViewAsignaturas(String asAsignatura, String asProfesor) {
        this.asAsignatura = asAsignatura;
        this.asProfesor = asProfesor;
    }

    public String getAsAsignatura() {
        return asAsignatura;
    }

    public void setAsAsignatura(String prAsignatura) {
        this.asAsignatura = asAsignatura;
    }

    public String getAsProfesor() {
        return asProfesor;
    }

    public void setAsProfesor(String asProfesor) {
        this.asProfesor = asProfesor;
    }
}
