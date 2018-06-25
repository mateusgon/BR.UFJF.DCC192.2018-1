package controlBD;

import br.ufjf.dcc192.Pessoa;
import java.util.List;

public interface PessoaDAO {
    public int criar (String nome, String email, Integer codigoRepositorio) throws Exception;
    public void excluir (Integer codigoPessoa) throws Exception;
    public List<Pessoa> ListSelecionado (Integer codigoRepositorio) throws Exception;
}
