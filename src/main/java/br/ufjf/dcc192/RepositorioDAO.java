package br.ufjf.dcc192;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class RepositorioDAO {
    
    private static Scanner input;
    private File arquivo = new File("item.txt");
    
    public Boolean vazio () throws IOException
    {
        try
            {
                Scanner input;               
                input = new Scanner ("item.txt");
                if (arquivo.exists() && arquivo.length() != 0)
                {
                    input.close();
                    return false;
                }
                else
                {
                    input.close();
                    return true;
                }
            }catch (SecurityException securityException)
            {
                System.err.println("Write permission denied. Terminating.");
                System.exit(1);
            }
        return false;
    }
}
