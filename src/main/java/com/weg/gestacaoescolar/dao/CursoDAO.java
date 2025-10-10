package com.weg.gestacaoescolar.dao;

import com.weg.gestacaoescolar.conexao.Conexao;
import com.weg.gestacaoescolar.model.Curso;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoDAO {
    public Curso salvarCurso(Curso curso) throws SQLException {
        String query = """
                INSERT INTO
                curso
                (nome,codigo)
                VALUES 
                (?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                curso.setId(rs.getInt(1));
            }
        }
        return curso;
    }

    public List<Curso> listarCurso() throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,codigo
                FROM curso
                """;
        List<Curso> cursos = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                Curso curso = new Curso(id,nome,codigo);
                cursos.add(curso);
            }
        }
        return cursos;
    }

    public Curso buscarCursoPorId(int id) throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,codigo
                FROM curso
                WHERE id = ?
                """;

        int newId = 0;
        String nome = "";
        String codigo = "";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newId = rs.getInt("id");
                nome = rs.getString("nome");
                codigo = rs.getString("codigo");
            }
        }
        return new Curso(newId,nome,codigo);
    }

    public Curso atualizarCurso(Curso curso) throws SQLException {
        String query = "UPDATE curso SET nome = ?, codigo = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, curso.getId());
            stmt.executeUpdate();
        }
        return curso;
    }

    public void deletarCurso(int id) throws SQLException {
        String query = "DELETE FROM curso WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean cursoExiste(Curso curso) throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,codigo
                FROM curso
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, curso.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }
    public boolean cursoExistePorId(int id) throws SQLException {
        String query = """
                SELECT id
                FROM curso
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }
}
