package com.weg.gestacaoescolar.dao;

import com.weg.gestacaoescolar.conexao.Conexao;
import com.weg.gestacaoescolar.model.Aula;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
            stmt.setDate(2, Date.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                aula.setId(aula.getId());
            }
        }
        return aula;
    }

    public List<Aula> listarAula() throws SQLException {
        String query = """
                """;
        List<Aula> aula = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            ){

        }
        return aula;
    }
}
