package com.weg.gestacaoescolar.dao;

import com.weg.gestacaoescolar.conexao.Conexao;
import com.weg.gestacaoescolar.model.Nota;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotaDAO {

    public Nota criarNota(Nota nota) throws SQLException {
        String query = """
                INSERT INTO 
                nota
                (aluno_id,aula_id,valor)
                VALUES 
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, nota.getAlunoId());
            stmt.setInt(2, nota.getAulaId());
            stmt.setDouble(3, nota.getValor());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                nota.setId(rs.getInt(1));
            }
        }
        return nota;
    }

    public List<Nota> listarNotas() throws SQLException {
        String query = """
                SELECT id
                      ,aluno_id
                      ,aula_id
                      ,valor
                FROM nota
                """;
        List<Nota> notas = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int alunoId = rs.getInt("aluno_id");
                int aulaId = rs.getInt("aula_id");
                double valor = rs.getDouble("valor");

                Nota nota = new Nota(id,alunoId,aulaId,valor);
                notas.add(nota);
            }
        }
        return notas;
    }

//    public Nota pesquisarNotaPorId(int id) throws SQLException {
//        String query = """
//                """;
//
//        int newId = 0;
//        int alunoId = 0;
//        int aulaId = 0;
//        double valor = 0.00;
//
//        try(Connection conn = Conexao.conectar();
//            PreparedStatement stmt = conn.prepareStatement(query)){
//
//
//        }
//    }
}
