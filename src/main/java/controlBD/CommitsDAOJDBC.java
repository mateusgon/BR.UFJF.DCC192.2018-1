package controlBD;

import br.ufjf.dcc192.Commits;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.repodriller.domain.Modification;

public class CommitsDAOJDBC implements CommitsDAO{

    private Connection conexao;
    private PreparedStatement operacaoInsereCommits;
    private PreparedStatement operacaoExcluirCommits;
    private PreparedStatement operacaoListarSelecionado;
     private static Scanner input;
    
    public CommitsDAOJDBC() {
        try {
            try {
                conexao = BdConnection.getConnection();
                operacaoInsereCommits = conexao.prepareStatement("insert into commits (codigoCommits, comentario, fk_codigoRepositorio, fk_codigoPessoa) values"
                        + "(?,?,?,?)");
                operacaoListarSelecionado = conexao.prepareStatement("select codigoCommits, comentario from commits where fk_codigoRepositorio = ? and fk_codigoPessoa = ?");
                operacaoExcluirCommits = conexao.prepareStatement("delete from commits where fk_codigoRepositorio = ? and fk_codigoPessoa = ?");
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
    }

    @Override
    public void excluir(Integer codigoPessoa, Integer codigoRepositorio, String nome) throws Exception{
        operacaoExcluirCommits.clearParameters();
        operacaoExcluirCommits.setInt(1, codigoRepositorio);
        operacaoExcluirCommits.setInt(2, codigoPessoa);
        operacaoExcluirCommits.execute();
        File file = new File (nome+codigoRepositorio+"commits"+codigoPessoa+".txt");
        file.delete();
    }

    @Override
    public List<Commits> listSelecionado(Integer codigoPessoa, Integer codigoRepositorio, String nome) throws Exception{
        List<Commits> commits = new ArrayList<>();
        operacaoListarSelecionado.clearParameters();
        operacaoListarSelecionado.setInt(1, codigoRepositorio);
        operacaoListarSelecionado.setInt(2, codigoPessoa);
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
            input = new Scanner (new FileReader(nome+codigoRepositorio+"commits"+codigoPessoa+".txt")).useDelimiter("/Fim/");
            input.useLocale(Locale.ENGLISH);
            try
                {
                    while (input.hasNext())
                    {
                        Modification m = new Modification(null, null, null, input.next(), null);
                        commit.getModificacoes().add(m);
                    }
                }
                catch (NoSuchElementException elementException)
                {
                  System.out.println("Todas as leituras de item foram feitas.");
                }
                catch (IllegalStateException stateException)
                {
                   System.err.println("Error reading from file. Terminating.");
                } 
            input.close();
        }
        return commits;
    }
    
}
