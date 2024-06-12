package com.example.xrv;

//Clase encargada de definir las propiedades de la card Aulas
public class CardViewAulas {

    public String asignatura;
    public String aula;

    //Método constructor
    public CardViewAulas(String asignatura, String aula) {
        this.asignatura = asignatura;
        this.aula = aula;
    }

    // Método que devuelve la asignatura
    public String getAsignatura() {
        return asignatura;
    }

    // Método que permite modificar la asignatura
    public void setAsignatura(String asignatura) {
        asignatura = asignatura;
    }

    // Método que devuelve la aula
    public String getAula() {
        return aula;
    }

    // Método que permite modificar la aula
    public void setAula(String aula) {
        aula = aula;
    }
}
