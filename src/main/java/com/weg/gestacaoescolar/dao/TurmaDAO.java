package com.weg.gestacaoescolar.dao;

import com.weg.gestacaoescolar.conexao.Conexao;
import com.weg.gestacaoescolar.model.Turma;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaDAO {

    public Turma salvarCursos(Turma turma) throws SQLException {
        String query = """
                INSERT INTO 
                turma
                (nome,curso_id,professor_id)
                VALUES 
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCursoId());
            stmt.setInt(3, turma.getProfessorId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                turma.setId(rs.getInt(1));
            }
        }
        return turma;
    }

    public List<Turma> listarTurma() throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,curso_id
                      ,professor_id
                FROM turma
                """;
        List<Turma> turmas = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");

                Turma turma = new Turma(id,nome,cursoId,professorId);
                turmas.add(turma);
            }
        }
        return turmas;
    }

    public Turma buscarTurmaPorId(int id) throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,curso_id
                      ,professor_id
                FROM turma
                WHERE id = ?
                """;

        int newId = 0;
        String nome = "";
        int cursoId = 0;
        int professorId = 0;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newId =rs.getInt("id");
                nome =rs.getString("nome");
                cursoId =rs.getInt("curso_id");
                professorId =rs.getInt("professor_id");
            }
        }
        return new Turma(newId,nome,cursoId,professorId);
    }

    public void atualizarTurma(int id) throws SQLException {
        String query = "UPDATE turma SET nome = ?, curso_id = ?, professor_id = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public void deletarTurma(int id) throws SQLException {
        String query = "DELETE FROM turma WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean turmaExiste(Turma turma) throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,curso_id
                      ,professor_id
                FROM turma
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, turma.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }

    public boolean turmaExistePorId(int id) throws SQLException {
        String query = """
                SELECT id
                FROM turma
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }
}
