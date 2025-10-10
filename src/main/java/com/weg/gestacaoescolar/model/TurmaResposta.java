package com.weg.gestacaoescolar.model;

public class TurmaResposta extends Turma{
    private String nomeProfessor;
    private String nomeCurso;

    public TurmaResposta(Turma turma, String nomeProfessor, String nomeCurso){
        super(turma.getId(),turma.getNome(), turma.getCurso_id(), turma.getProfessor_id());
        this.nomeProfessor=nomeProfessor;
        this.nomeCurso = nomeCurso;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}