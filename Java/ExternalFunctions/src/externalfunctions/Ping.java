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
public class Ping extends Function{
      @Override
    protected void xFunc() throws SQLException {

        if (args() != 1) {
            throw new SQLException("Ocupa al menos un argumento" + args());
        }
        try{
            String ip = value_text(0);
            new Socket().connect(new InetSocketAddress(ip,80),5000);
            result(1);
        }
        catch(IOException ex){
            result(0);
        }
    }
}
