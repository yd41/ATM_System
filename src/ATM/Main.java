package ATM;
import MS.SQL;
import MainInterface.MainInterface;
import java.io.IOException;
import java.sql.SQLException;


public class Main {
    static public double upper=20000.00;
    public static int Inquire_count=0;
      public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQL.Init();
        try {
            MainInterface.Init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
