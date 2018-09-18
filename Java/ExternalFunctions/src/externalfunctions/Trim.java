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
public class Trim extends Function{
    @Override
    protected void xFunc() throws SQLException {
        if (args() != 2) {
            throw new SQLException("Requiere dos parámetros" + args());
        }
        String cadena = value_text(0);
        String caracter = value_text(1);
        char letra = caracter.charAt(0);
        String temporal="";
        String resultante="";
        boolean t=true;

        for(int i=0;i<cadena.length();i++){
            if(t && cadena.charAt(i)== letra)
                continue;
            temporal += cadena.charAt(i);
            t=false;
        }
        resultante=temporal;
        
        for(int i= temporal.length()-1;i>=0;i--){
            if(temporal.charAt(i)==letra){
                resultante = resultante.substring(0,i);
                continue;
            }
            break;
        }
        result(resultante);
    }
}
