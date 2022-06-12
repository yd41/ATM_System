package MS;
import MainInterface.MainInterface;
import ATM.UserManage;
import java.sql.*;

public class SQL {
 public static   Connection con;

    public static void Init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String user = "";
        String password = "";
        String service="";
        String database="atm";
        String url = String.format("jdbc:mysql://%s:3306/%s?user=%s&password=%s&SSL=false&serviceTimezone&characterEncoding=gb2312"
                ,service,database,user,password);


        con= DriverManager.getConnection(url,user,password);



    }


    //查询 account

    public static boolean Query(String account) {

        String query = "select * from atm where 账号=" + "'" +account+"'";

        boolean bol = false;

        try {
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(query);
            if (rs.next()) {
                bol = true;
                MainInterface.user = new UserManage(rs.getString(2), rs.getString(6), rs.getDouble(5), rs.getString(1), rs.getString(4), rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bol;
    }


    public static boolean Query(String account,boolean  F) {

        String query = "select * from atm where 账号=" + "'" +account+"'";
        boolean bol = false;
        try {
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(query);
            if (rs.next()) {
                bol = true;
                MainInterface.userTemp = new UserManage(rs.getString(2), rs.getString(6), rs.getDouble(5), rs.getString(1), rs.getString(4), rs.getString(3));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bol;
    }


    //添加

 static    public void Insert(String account, String password, Double balance, String username, String phoneNum, String gender) {

        String insert = "insert into atm values" + "(" + "'"+ username + "','"+account + "','"+gender +"','"+ phoneNum +"','"+ balance + "','"+password +"'"+ ")";

        try {
            Statement sql = con.createStatement();
            int ok = sql.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //修改

    public static void Update(String account, String newPassword) {

        String update = "update atm set 密码=" + newPassword + " where 账号=" + "'" +account+"'";

        try {
            Statement sql = con.createStatement();
            int ok = sql.executeUpdate(update);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean Update(String account, double Money,boolean tf) {

        String update = tf? "update atm set 余额=" + (MainInterface.user.getBalance() + Money) + "where 账号=" +"'" +account+"'":"update atm set 余额=" + Money + "where 账号=" + "'" +account+"'";

        if( MainInterface.user.getBalance()+Money>=0) {

            try {
                Statement sql = con.createStatement();
                int ok = sql.executeUpdate(update);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;

    }

    public static void Delete(String account, String password) {

        String delete = "delete from atm where 账号=" + "'" +account+"'";


        if (MainInterface.user.getPassword().equals(password)) {

            try {
                Statement sql = con.createStatement();
                int ok = sql.executeUpdate(delete);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }




}
