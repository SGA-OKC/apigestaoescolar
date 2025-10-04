package com.weg.gestacaoescolar.dao;

import com.weg.gestacaoescolar.conexao.Conexao;
import com.weg.gestacaoescolar.model.Aluno;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoDAO {

    public Aluno salvarAluno(Aluno aluno) throws SQLException {
        String query = """
                INSERT INTO aluno
                (nome,email,matricula,data_nascimento) 
                VALUES
                (?,?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                aluno.setId(rs.getInt(1));
            }
        }
        return aluno;
    }

    public List<Aluno> listAlunos() throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,email
                      ,matricula
                      ,data_nascimento
                FROM aluno
                """;
        List<Aluno> alunos = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

                Aluno aluno = new Aluno(id,nome,email,matricula,dataNascimento);
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public Aluno buscarAlunoPorID(int id) throws SQLException {
        String query = """
                SELECT id
                      ,nome
                FROM aluno
                WHERE id = ?
                """;

        int newID = 0;
        String nome = "";
        String email = "";
        String matricula = "";
        LocalDate dataNascimento = null;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newID = rs.getInt("id");
                nome = rs.getString("nome");
                email = rs.getString("email");
                matricula = rs.getString("matricula");
                dataNascimento = rs.getDate("data_nascimento").toLocalDate();
            }
        }
        return new Aluno(newID,nome,email,matricula,dataNascimento);
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        String query = "UPDATE aluno SET nome = ?, email = ?, matricula = ?, data_nascimento = ? WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, aluno.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteAluno(int id) throws SQLException {
        String query = "DELETE FROM aluno WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean alunoExiste(int id) throws SQLException {
        String query = """
                SELECT id
                      ,nome
                      ,email
                      ,matricula
                      ,data_nascimento
                FROM aluno
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

    public boolean alunoExisteId(int id) throws SQLException {
        String query = """
                SELECT id
                FROM aluno
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
