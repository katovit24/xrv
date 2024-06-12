package com.example.xrv;

//Clase sencilla encargada de definir las propiedades de la card Notas
public class CardViewNotas {

    String ntAsignatura;
    String ntNotas;

    //Método constructor
    public CardViewNotas(String ntAsignatura, String ntNotas) {
        this.ntAsignatura = ntAsignatura;
        this.ntNotas = ntNotas;
    }

    // Método que devuelve la asignatura
    public String getNtAsignatura() {
        return ntAsignatura;
    }

    // Método que permite modificar la asignatura
    public void setNtAsignatura(String ntAsignatura) {
        this.ntAsignatura = ntAsignatura;
    }

    // Método que devuelve la nota
    public String getNtNotas() {
        return ntNotas;
    }

    // Método que permite modificar la nota
    public void setNtNotas(String ntNotas) {
        this.ntNotas = ntNotas;
    }

    //Método toString sobreescrito para poder obtener en String la asignatura y las notas
    @Override
    public String toString() {
        return ntAsignatura + ntNotas;
    }
}
