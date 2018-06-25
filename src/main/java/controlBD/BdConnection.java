package controlBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class BdConnection {
    
    public static Connection instancia = null;
    public static Connection getConnection() throws Exception
    {
        if(instancia == null)
        {
            String driverURL = "jdbc:derby://localhost:1527/dcc094";
            instancia = DriverManager.getConnection(driverURL, "dcc094", "dcc094");
            return instancia;
        }
        else
        {
            return instancia;
        }
    }
}
