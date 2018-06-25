package controlBD;

import java.util.List;
import org.repodriller.domain.Modification;
import br.ufjf.dcc192.Commits;

public interface CommitsDAO {
    public void criar (String id, String comentarios, Integer codigoRepositorio, Integer codigoPessoa, List<Modification> modificacoes) throws Exception;
    public void excluir (Integer codigoPessoa, Integer codigoRepositorio, String codigoCommit) throws Exception;
    public List<Commits> listSelecionado (Integer codigoPessoa, Integer codigoRepositorio) throws Exception;
}
