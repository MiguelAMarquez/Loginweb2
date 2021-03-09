import java.sql.*;

public class ConnectDB {
 
   public static Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registro","root","130396");
            
            return con;
            
        }catch(Exception ex){
        
        }
        return null;
    }
   
   public static void main (String [] args) {
	   
   }

}
