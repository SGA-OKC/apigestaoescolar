package com.weg.gestacaoescolar.dao;

import com.weg.gestacaoescolar.conexao.Conexao;
import com.weg.gestacaoescolar.model.Turma;
import com.weg.gestacaoescolar.model.TurmaResposta;
import com.weg.gestacaoescolar.util.gerarIn;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaDAO {

    public Turma salvarTurma(Turma turma) throws SQLException {
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
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());
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

    public TurmaResposta buscarTurmasPorId(int id) throws SQLException {
        String query = """
                        SELECT t.id
                        , t.nome
                        , t.curso_id
                        , t.professor_id
                        , p.nome as professor
                        , c.nome as curso
                        FROM turma t 
                        LEFT JOIN professor p 
                        ON  t.professor_id = p.id
                        LEFT JOIN curso c
                        ON c.id = t.curso_id
                        WHERE t.id = ?
                        """;
        TurmaResposta turmaResposta = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int newId = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");
                String nomeProfessor = rs.getString("professor");
                String nomeCurso = rs.getString("curso");

                var turma = new Turma(newId, nome, cursoId, professorId);
                turmaResposta = new TurmaResposta(turma,nomeProfessor, nomeCurso);
            }
        }
        return turmaResposta;
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

    public List<String> buscarListaNomesPorId(List<Integer> idsAlunos) throws SQLException {
        String query = """
                SELECT nome
                FROM aluno 
                WHERE id IN """ + gerarIn.gerando(idsAlunos.size());


        List<String> nomeAlunos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            String nome = "";

            for (int i = 0; i < idsAlunos.size(); i++) {
                stmt.setInt(i + 1, idsAlunos.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                nome = rs.getString("nome");
                nomeAlunos.add(nome);
            }

        }
        return nomeAlunos;
    }

    public void inserirAlunosTurma(int idTurma, List<Integer> idAlunos )throws SQLException{
        String query = """
                INSERT INTO turma_aluno
                (turma_id, aluno_id)
                VALUES
                (?,?)
                """;

        for(Integer idAluno : idAlunos){
            try(Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1,idTurma);
                stmt.setInt(2,idAluno);
                stmt.executeUpdate();
            }
        }
    }
}
