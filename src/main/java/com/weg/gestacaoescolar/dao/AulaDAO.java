package com.weg.gestacaoescolar.dao;

import com.weg.gestacaoescolar.conexao.Conexao;
import com.weg.gestacaoescolar.model.Aula;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaDAO {

    public Aula salvarAula(Aula aula) throws SQLException {
        String query = """
                INSERT INTO 
                aula
                (turma_id,data_hora,assunto)
                VALUES 
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, aula.getTurmaId());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                aula.setId(rs.getInt(1));
            }
        }
        return aula;
    }

    public List<Aula> listarAula() throws SQLException {
        String query = """
                SELECT turma_id
                      ,data_hora
                      ,assunto
                FROM aula
                """;
        List<Aula> aula = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int turmaId = rs.getInt("aluno_id");
                LocalDateTime data = rs.getTimestamp("data_aula").toLocalDateTime();
                String assunto = rs.getString("assunto");

                Aula aulas = new Aula(turmaId,data,assunto);
                aula.add(aulas);
            }
        }
        return aula;
    }

    public Aula buscarAulaPorID(int id) throws SQLException {
        String query = """
                SELECT id
                      ,turma_id
                      ,data_hora
                      ,assunto
                FROM aula
                WHERE id = ?
                """;
        Aula aula = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int newID = rs.getInt("id");
                int turmaId = rs.getInt("turma_id");
                LocalDateTime data = rs.getTimestamp("data_hora").toLocalDateTime();
                String assunto = rs.getString("assunto");

                aula = new Aula(newID, turmaId, data, assunto);
            }
        }
        return aula;
    }

    public Aula atualizarAula(int id,Aula aula) throws SQLException {
        String query = "UPDATE aula SET turma_id = ?, data_hora = ?, assunto = ? WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, aula.getTurmaId());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
        return aula;
    }

    public void deletarAula(int id) throws SQLException {
        String query = "DELETE FROM aula WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }

    public boolean aulaExistePorId(int id) throws SQLException {
        String query = """
                SELETE id
                FROM aula
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            stmt.executeUpdate();

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return true;
            }
        }
        return false;
    }

    public String buscarNomeTurmaPorId(int turmaId) throws SQLException {
        String query = """
                SELETE nome 
                FROM turma
                WHERE id = ?
                """;
        String nomeTurma = null;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, turmaId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                nomeTurma = rs.getString("nome");
            }
        }
        return nomeTurma;
    }
}
