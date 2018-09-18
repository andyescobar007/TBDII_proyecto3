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
public class COMPARESTRING extends Function{
    @Override
    protected void xFunc() throws SQLException {
        if (args() != 2) {
            throw new SQLException("Requiere 2 strings" + args());
        }
        String primera = value_text(0);
        String segunda = value_text(1);
        int t=0;
        if(primera.compareTo(segunda)==0){
            t=0;
            result();
        }
        else if(primera.compareTo(segunda)<0){
            t=-1;
        }else if(primera.compareTo(segunda)>0){
            t=1;
        }
        result((t));
    }
}
