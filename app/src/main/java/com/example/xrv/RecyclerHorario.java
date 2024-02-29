package com.example.xrv;

public class RecyclerHorario {
    String hrAsignatura;
    String hrHorario;

    public RecyclerHorario(String hrAsignatura, String hrHorario) {
        this.hrAsignatura = hrAsignatura;
        this.hrHorario = hrHorario;
    }

    public String getHrAsignatura() {
        return hrAsignatura;
    }

    public void setHrAsignatura(String hrAsignatura) {
        this.hrAsignatura = hrAsignatura;
    }

    public String getHrHorario() {
        return hrHorario;
    }

    public void setHrHorario(String hrHorario) {
        this.hrHorario = hrHorario;
    }
}
