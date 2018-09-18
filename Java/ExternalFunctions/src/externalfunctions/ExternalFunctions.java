/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package externalfunctions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.Function;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ExternalFunctions {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    
    public static void main(String[] args) throws SQLException {
    
        String path = "C:/Users/ANDY ESCOBAR/Documents/NetBeansProjects/ExternalFunctions/test.db";
        Connection connection = Connection(path);
        Function.create(connection,Trim.class.getSimpleName(), new Trim());
        Function.create(connection, Ping.class.getSimpleName(), new Ping());
        Function.create(connection, Factorial.class.getSimpleName(), new Factorial());
        Function.create(connection, C2F.class .getSimpleName(), new C2F());
        Function.create(connection, F2C.class .getSimpleName(), new F2C());
        Function.create(connection, BIN2DEC.class.getSimpleName(), new BIN2DEC());
        Function.create(connection, DEC2BIN.class.getSimpleName(), new DEC2BIN());
        Function.create(connection, DEC2HEX.class.getSimpleName(), new DEC2HEX());
        Function.create(connection, HEX2DEC.class.getSimpleName(), new HEX2DEC());
        Function.create(connection, REPEAT.class.getSimpleName(), new REPEAT());
        Function.create(connection, PMT.class.getSimpleName(), new PMT());
        Function.create(connection, COMPARESTRING.class.getSimpleName(), new COMPARESTRING());
        
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  
        int opcion;
        try {
            do{
                Scanner lea=new Scanner(System.in);
                menu();
                System.out.println("Ingrese una opcion: ");
                opcion=lea.nextInt();
                ResultSet rs;
                switch(opcion){
                    case 1:
                       System.out.println("------ PING ------");
                        System.out.println("Dominio: ");
                       String dominio=lea.next();
                       ResultSet rst = statement.executeQuery("select Ping('"+dominio+"') as b");
                       System.out.println("Valor PING:  "+ rst.getString("b"));
                       break;
                       
                    case 2:
                        System.out.println("------ PMT ------");
                        System.out.print("Tasa de Interes: ");
                        String tasa=lea.next();
                        System.out.print("Periodos: ");
                        String periodo=lea.next();
                        System.out.print("Valor del prestamo: ");
                        String prestamo=lea.next();
                        rs = statement.executeQuery("select PMT("+tasa+","+periodo+","+prestamo+") as b");
                        
                        ///0.08,10,10000
                        System.out.println("Valor:  "+ rs.getDouble(1));
                        break;
                        
                    case 3:
                        System.out.println("------ BIN2DEC ------");
                        //101010
                        System.out.print("Binario: ");
                        String binario=lea.next();
                        rs= statement.executeQuery("select BIN2DEC ("+binario+") as b");
                        System.out.println("Decimal="+ rs.getInt(1));
                        break;
                    
                    case 4:
                        System.out.println("------ DEC2BIN ------");
                        System.out.print("Decimal: ");
                        String decimal=lea.next();
                        rs= statement.executeQuery("select DEC2BIN ("+decimal+") as b");
                        System.out.println("Binario="+ rs.getString("b"));
                        break;
                        
                    case 5:
                        System.out.println("------ C2F ------");
                        System.out.print("°C: ");
                        String celsius=lea.next();
                        rs= statement.executeQuery("select C2F("+celsius+") as b");
                        System.out.println("Temperatura: "+ rs.getDouble(1)+ " °F");
                        break;
                        
                    case 6:
                        System.out.println("------ F2C ------");
                        System.out.print("°F: ");
                        String farenheit=String.valueOf(lea.nextDouble());
                        rs= statement.executeQuery("select F2C ("+farenheit+") as b");
                        System.out.println("Temperatura en Celsius="+ rs.getDouble(1)+ " °C");
                        break;
                        
                    case 7:
                        System.out.println("------ Factorial ------");
                        System.out.print("Factorial: ");
                        String factorial=String.valueOf(lea.nextInt());
                        rs = statement.executeQuery("select Factorial("+factorial+") as b");
                        while (rs.next()){
                          System.out.println("Resultado= "+ rs.getInt(1));
                        }
                        break;
                        
                    case 8:
                        System.out.println("------ HEX2DEC ------");
                        System.out.print("Hexadecimal:  ");
                        String hex=lea.next();
                        rs= statement.executeQuery("select HEX2DEC ('"+hex+"') as b");
                        System.out.println("Decimal: "+ rs.getInt(1));
                        break;
                        
                    case 9:
                        System.out.println("------ DEC2HEX ------");
                        System.out.print("Decimal:  ");
                        String tempDecimal=lea.next();
                        rs= statement.executeQuery("select DEC2HEX ("+tempDecimal+") as b");
                        System.out.println("Hexadecimal : "+ rs.getInt(1));
                        break;
                    case 10:
                        System.out.println("------ COMPARESTRING ------");
                        System.out.print("STRING 1    : ");
                        String cadena1=lea.next();
                        System.out.print("STRING 2    : ");
                        String cadena2=lea.next();
                        rs= statement.executeQuery("select COMPARESTRING('"+cadena1+"','"+cadena2+"') as b");
                        System.out.println("Valor de retorno: "+ rs.getInt(1));
                        System.out.println("Caso: "+getCasoCompareString(rs.getInt(1)));
                                
                        break;
                    case 11:
                        System.out.println("------ TRIM ------");
                        System.out.print("Cadena: ");
                        String cadena=lea.next();
                        System.out.print("Caracter a remover: ");
                        String caracter=lea.next();
                        rs = statement.executeQuery("select Trim('"+cadena+"','"+caracter+"') as b");
                        while(rs.next()){
                            System.out.println("b = " + rs.getString("b"));
                        }
                        break;
                    case 12:
                        System.out.println("------ REPEAT ------");
                        System.out.print("Valor: ");
                        String valor=lea.next();
                        System.out.println("Repetir: ");
                        String repetir;
                        System.out.println((repetir=lea.next())+" veces");
        
                        
                        rs = statement.executeQuery("select REPEAT('"+valor+"',"+repetir+") as b");
                        while(rs.next()){
                            System.out.println("Cadena: " + rs.getString("b"));
                        }
                        break;
                }
                if(opcion!=0){
                    System.out.println("\n----------------------");
                    System.out.println("Desea continuar?(s/n)");
                    opcion=lea.next().equals("n")?0:opcion;    
                }
                   
            }while(opcion!=0);
        }catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        
    }

    public static Connection Connection(String path)
    {
        File file = new File(path);
        if (file.exists())
        {
            Connection conn;
            try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:" + path;
                conn = DriverManager.getConnection(url);
                System.out.println("Conexión Establecida");
                return conn;
            }catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        else
        {
            System.out.println("No se encontro la base de datos");
            return null;
        }
    }
    
    public static void menu(){
        System.out.println("======  FUNCIONES EXTERNAS ======");
        System.out.println("1.  PING");
        System.out.println("2.  PMT");
        System.out.println("3.  BIN2DEC");
        System.out.println("4.  DEC2BIN");
        System.out.println("5.  C2F");
        System.out.println("6.  F2C");
        System.out.println("7.  Factorial");
        System.out.println("8.  HEX2DEC");
        System.out.println("9.  DEC2HEX");
        System.out.println("10. COMPARESTRING");
        System.out.println("11. TRIM");
        System.out.println("12. REPEAT");
        System.out.println("0.  Salir");
    
    }
    
    public static String getCasoCompareString(int i){
        switch (i) {
            case -1:
                return "STRING 1 MENOR QUE EL STRING 2";
            case 0:
                return "AMBOS STRING SON IGUALES";
            case 1:
                return "STRING 2 ES MENOR QUE EL STRING 1";
            default:
                break;
        }
        return null;
          
    }

}


