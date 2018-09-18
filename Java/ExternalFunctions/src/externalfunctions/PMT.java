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
public class PMT extends Function {
    @Override
    protected void xFunc() throws SQLException {
        if (args() != 3) {
            throw new SQLException("Necesita 3 parámetros" + args());
        }
        try {
                double j = value_double(0)/ 12;
                int n = value_int(1);
                double monto = value_double(2);
                double pmt = monto*((j*Math.pow((1+j),n))/(Math.pow((1+j),n) - 1));
                result(pmt);
        }catch (Exception e)
        {
            result(-1);
        }
    }
}
