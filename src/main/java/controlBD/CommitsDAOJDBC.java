package controlBD;

import br.ufjf.dcc192.Commits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.repodriller.domain.Modification;
import org.repodriller.domain.ModificationType;

public class CommitsDAOJDBC implements CommitsDAO{

    private Connection conexao;
    private PreparedStatement operacaoInsereCommits;
    private PreparedStatement operacaoInsereCommitsModificacoes;
    private PreparedStatement operacaoExcluirCommits;
    private PreparedStatement operacaoExcluirCommitsModificacoes;
    private PreparedStatement operacaoListarSelecionado;
    private PreparedStatement operacaoListarSelecionadoModificacoes;
    
    public CommitsDAOJDBC() {
        try {
            try {
                conexao = BdConnection.getConnection();
                operacaoInsereCommits = conexao.prepareStatement("insert into commits (codigoCommits, comentario, fk_codigoRepositorio, fk_codigoPessoa) values"
                        + "(?,?,?,?)");
                operacaoInsereCommitsModificacoes = conexao.prepareStatement("insert into commits_modificacoes (diff, fk_codigoCommits) values (?, ?)");
                operacaoListarSelecionado = conexao.prepareStatement("select codigoCommits, comentario from commits where fk_codigoRepositorio = ? and fk_codigoPessoa = ?");
                operacaoListarSelecionadoModificacoes = conexao.prepareStatement("select diff from commits_modificacoes where fk_codigoCommits = ?");
                operacaoExcluirCommits = conexao.prepareStatement("delete from commits where fk_codigoRepositorio = ? and fk_codigoPessoa = ?");
                operacaoExcluirCommitsModificacoes = conexao.prepareStatement("delete from commits_modificacoes where fk_codigoCommits = ?");
            } catch (Exception ex) {
                Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void criar(String id, String comentarios, Integer codigoRepositorio, Integer codigoPessoa, List<Modification> modificacoes) throws Exception {
        operacaoInsereCommits.clearParameters();
        operacaoInsereCommits.setString(1, id);
        operacaoInsereCommits.setString(2, comentarios);
        operacaoInsereCommits.setInt(3, codigoRepositorio);
        operacaoInsereCommits.setInt(4, codigoPessoa);
        operacaoInsereCommits.executeUpdate();
        for (Modification modificacoe : modificacoes) {
            operacaoInsereCommitsModificacoes.clearParameters();
            operacaoInsereCommitsModificacoes.setString(1, modificacoe.getDiff());
            operacaoInsereCommitsModificacoes.setString(2, id);
            operacaoInsereCommitsModificacoes.executeUpdate();
        }
    }

    @Override
    public void excluir(Integer codigoPessoa, Integer codigoRepositorio, String codigoCommit) throws Exception{
        operacaoExcluirCommitsModificacoes.clearParameters();
        operacaoExcluirCommitsModificacoes.setString(1, codigoCommit);
        operacaoExcluirCommitsModificacoes.execute();
        operacaoExcluirCommits.clearParameters();
        operacaoExcluirCommits.setInt(1, codigoRepositorio);
        operacaoExcluirCommits.setInt(2, codigoPessoa);
        operacaoExcluirCommits.execute();
    }

    @Override
    public List<Commits> listSelecionado(Integer codigoPessoa, Integer codigoRepositorio) throws Exception{
        List<Commits> commits = new ArrayList<>();
        operacaoListarSelecionado.clearParameters();
        operacaoListarSelecionado.setInt(1, codigoPessoa);
        operacaoListarSelecionado.setInt(2, codigoRepositorio);
        ResultSet result = operacaoListarSelecionado.executeQuery();
        while (result.next())
        {
            Commits c = new Commits();
            c.setId(result.getString("codigoCommits"));
            c.setComentario(result.getString("comentario"));
            commits.add(c);
        }
        result.close();
        for (Commits commit : commits) {
            operacaoListarSelecionadoModificacoes.clearParameters();
            operacaoListarSelecionadoModificacoes.setString(1, commit.getId());
            ResultSet resultado = operacaoListarSelecionadoModificacoes.executeQuery();
            while (resultado.next())
            {
                Modification m = new Modification(null, null, null, resultado.getString("diff"), null);
                commit.getModificacoes().add(m);
            }
            resultado.close();
        }
        return commits;
    }
    
}
