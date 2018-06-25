package controlBD;

import br.ufjf.dcc192.Pessoa;
import br.ufjf.dcc192.Repositorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAOJDBC implements PessoaDAO{

    private Connection conexao;
    private PreparedStatement operacaoInserePessoa;
    private PreparedStatement operacaoExcluir;
    private PreparedStatement operacaoListarSelecionado;
    
    public PessoaDAOJDBC() {
        try {
            try {
                conexao = BdConnection.getConnection();
                operacaoInserePessoa = conexao.prepareStatement("insert into pessoa (nome, email, fk_codigoRepositorio) values"
                        + "(?,?, ?)");
                operacaoListarSelecionado = conexao.prepareStatement("select codigoPessoa, nome, email from pessoa where fk_codigoRepositorio = ?");
                operacaoExcluir = conexao.prepareStatement("delete from pessoa where fk_codigoRepositorio = ?");
            } catch (Exception ex) {
                Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void criar(String nome, String email, Integer codigoRepositorio) throws Exception {
        operacaoInserePessoa.clearParameters();
        operacaoInserePessoa.setString(1, nome);
        operacaoInserePessoa.setString(2, email);
        operacaoInserePessoa.setInt(3, codigoRepositorio);
        operacaoInserePessoa.executeUpdate();
    }

    @Override
    public void excluir(Integer codigoPessoa) throws Exception {
        operacaoExcluir.clearParameters();
        operacaoExcluir.setInt(1, codigoPessoa);
        operacaoExcluir.execute();
    }

    @Override
    public List<Pessoa> ListSelecionado(Integer codigoRepositorio) throws Exception {
        List<Pessoa> pRepositorio = new ArrayList<>();
            try {
                operacaoListarSelecionado.clearParameters();
                ResultSet resultado = operacaoListarSelecionado.executeQuery();
                while (resultado.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCodigoPessoa(resultado.getInt("codigoPessoa"));
                pessoa.setNome(resultado.getString("nome"));
                pessoa.setEmail(resultado.getString("email"));
                pRepositorio.add(pessoa);
            }
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pRepositorio;
    }
    
}
