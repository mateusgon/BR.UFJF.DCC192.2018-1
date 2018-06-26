package controlBD;

import br.ufjf.dcc192.Repositorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositorioDAOJDBC implements RepositorioDAO{

    private Connection conexao;
    private PreparedStatement operacaoInsereRepositorio;
    private PreparedStatement operacaoListarTodos;
    private PreparedStatement operacaoExcluir;
    private PreparedStatement operacaoListarSelecionado;
    
    public RepositorioDAOJDBC() {
        try {
            try {
                conexao = BdConnection.getConnection();
                operacaoInsereRepositorio = conexao.prepareStatement("insert into repositorio (nome, url) values"
                        + "(?,?)");
                operacaoListarSelecionado = conexao.prepareStatement("select codigoRepositorio where nome = ? and url = ?");
                operacaoListarTodos = conexao.prepareStatement("select codigoRepositorio, nome, url from repositorio");
                operacaoExcluir = conexao.prepareStatement("delete from repositorio where codigoRepositorio = ?");
            } catch (Exception ex) {
                Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void criar(String nome, String url) throws Exception {
        operacaoInsereRepositorio.clearParameters();
        operacaoInsereRepositorio.setString(1, nome);
        operacaoInsereRepositorio.setString(2, url);
        operacaoInsereRepositorio.executeUpdate();
    }

    @Override
    public void excluir(Integer codigoRepositorio) throws Exception {
        operacaoExcluir.clearParameters();
        operacaoExcluir.setInt(1, codigoRepositorio);
        operacaoExcluir.execute();
    }

    @Override
    public List<Repositorio> ListAll() throws Exception {
        List<Repositorio> repositorios = new ArrayList<>();
            try {
                operacaoListarTodos.clearParameters();
                ResultSet resultado = operacaoListarTodos.executeQuery();
                while (resultado.next()) {
                Repositorio repositorio = new Repositorio();
                repositorio.setCodigoRepositorio(resultado.getInt("codigoRepositorio"));
                repositorio.setNome(resultado.getString("nome"));
                repositorio.setUrl(resultado.getString("url"));
                repositorios.add(repositorio);
            }
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositorioDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repositorios;
    }

    @Override
    public Integer ListSelecionado(String nome, String url) throws Exception {
        Integer retorno = -1;
        operacaoListarSelecionado.clearParameters();
        operacaoListarSelecionado.setString(1, nome);
        operacaoListarSelecionado.setString(2, url);
        ResultSet resultado = operacaoListarSelecionado.executeQuery();
        while (resultado.next())
        {
            retorno = resultado.getInt("codigoRepositorio");
        }
        return retorno;
    }
    
}
