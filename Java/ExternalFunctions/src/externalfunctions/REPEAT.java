/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package externalfunctions;
import org.sqlite.Function;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.SQLException;
/**
 *
 * @author ANDY ESCOBAR
 */
public class REPEAT extends Function{
    protected void xFunc() throws SQLException {
        if (args() != 2) {
            throw new SQLException("Requiere dos argumentos" + args());
        }
        String cadena = value_text(0);
        int numero = value_int(1);
        String temp="";
        for(int i=0; i<numero;i++){
            temp+=cadena;
        }
        result(temp);
    }
}
