package com.example.xrv;

public class CardViewNotas {

    String ntAsignatura;
    String ntNotas;

    public CardViewNotas(String ntAsignatura, String ntNotas) {
        this.ntAsignatura = ntAsignatura;
        this.ntNotas = ntNotas;
    }

    public String getNtAsignatura() {
        return ntAsignatura;
    }

    public void setNtAsignatura(String ntAsignatura) {
        this.ntAsignatura = ntAsignatura;
    }

    public String getNtNotas() {
        return ntNotas;
    }

    public void setNtNotas(String ntNotas) {
        this.ntNotas = ntNotas;
    }
}
