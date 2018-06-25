package controlBD;

import java.util.Date;

public interface CommitsDAO {
    public void criar (String titulo, Double minimo, Date data, Date sorteio, String senha, Integer id) throws Exception;
    
}
