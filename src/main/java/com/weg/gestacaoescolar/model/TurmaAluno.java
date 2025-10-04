package com.weg.gestacaoescolar.model;

public class TurmaAluno {
    private int turmaId;
    private int alunoId;

    public TurmaAluno(int turmaId, int alunoId) {
        this.turmaId = turmaId;
        this.alunoId = alunoId;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }
}
