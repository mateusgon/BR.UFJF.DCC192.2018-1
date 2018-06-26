package controlBD;

import br.ufjf.dcc192.Repositorio;
import java.util.Date;
import java.util.List;

public interface RepositorioDAO {
    public void criar (String nome, String url) throws Exception;
    public void excluir (Integer codigoRepositorio) throws Exception;
    public List<Repositorio> ListAll () throws Exception;
    public Integer ListSelecionado (String nome, String url) throws Exception;
}
