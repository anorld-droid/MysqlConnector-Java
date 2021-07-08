import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnection {
    public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;
        try {
            //TODO add mysql connector to libraries : GOTO File->Project Structure->Libraries
            // then click  '+' icon and locate it in your files.
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/?characterEncoding=utf8&useConfigs=maxPerformance";
            String user = "";//TODO Your username for mysql, when you first installed mysql
            String password = ""; //TODO Your password for mysql, when you first installed mysql
            con = DriverManager.getConnection(url,"root","password");
            ResultSet resultSet = con.getMetaData().getCatalogs();
            statement = con.createStatement();
            String databaseName = ""; //TODO  Enter the name of the database you want to create in lowercase.
            String tableName = ""; //TODO Enter the name of the table you want to create.
            Boolean dbExists = false;
            while (resultSet.next()){
                String dbName = resultSet.getString(1);
                if (!(dbName.equalsIgnoreCase(databaseName))){
                    dbExists = true;
                    break;
                }
            }

            if (dbExists){
                String createDatabase = "CREATE DATABASE "+ databaseName + ";";
                String connectToDb = "USE "+databaseName+";";
                //TODO You can add the columns of your table even more tables this is just an illustration
                String createTable = "CREATE TABLE "+ tableName+" (\n" +
                        " item_Id INTEGER primary key NOT NULL auto_increment,\n" +
                        " Name TEXT NOT NULL, \n" +
                        " Price INTEGER NOT NULL);";
                //TODO You can add insert statements to your table(s),  this is just an illustration
                String sql1 = "INSERT INTO "+ tableName +"(Name, Price) VALUES('Detergent', 123) ";

                statement.executeUpdate(createDatabase);
                statement.executeUpdate(connectToDb);
                statement.executeUpdate(createTable);
                statement.executeUpdate(sql1);

            }
            statement.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
