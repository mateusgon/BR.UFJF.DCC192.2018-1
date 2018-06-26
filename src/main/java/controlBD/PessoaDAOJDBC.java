package controlBD;

import br.ufjf.dcc192.Pessoa;
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
    private PreparedStatement operacaoBusca;
    
    public PessoaDAOJDBC() {
        try {
            try {
                conexao = BdConnection.getConnection();
                operacaoInserePessoa = conexao.prepareStatement("insert into pessoa (nome, email, fk_codigoRepositorio) values"
                        + "(?,?, ?)");
                operacaoListarSelecionado = conexao.prepareStatement("select codigoPessoa, nome, email from pessoa where fk_codigoRepositorio = ?");
                operacaoExcluir = conexao.prepareStatement("delete from pessoa where codigoPessoa = ?");
                operacaoBusca = conexao.prepareStatement("select codigoPessoa from pessoa where fk_codigoRepositorio = ? and nome = ? and email = ?");
            } catch (Exception ex) {
                Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public int criar(String nome, String email, Integer codigoRepositorio) throws Exception {
        int retorno = -1;
        operacaoInserePessoa.clearParameters();
        operacaoInserePessoa.setString(1, nome);
        operacaoInserePessoa.setString(2, email);
        operacaoInserePessoa.setInt(3, codigoRepositorio);
        operacaoInserePessoa.executeUpdate();
        
        operacaoBusca.clearParameters();
        operacaoBusca.setInt(1, codigoRepositorio);
        operacaoBusca.setString(2, nome);
        operacaoBusca.setString(3, email);
        operacaoBusca.executeQuery();
        ResultSet resultado = operacaoBusca.executeQuery();
        while(resultado.next())
        {
            retorno = resultado.getInt("codigoPessoa");
        }
        return retorno;
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
                operacaoListarSelecionado.setInt(1, codigoRepositorio);
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
