package MainInterface;
import ATM.UserManage;
import MS.SQL;
import LoginInterface.LoginInterface;
import RegisterInterface.RegisterInterface;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;



public class MainInterface {

  static public   JFrame jf=new JFrame("ATM系统");

  static protected final int WIDTH=1000;
  static protected final int HEIGHT=700;

  public static UserManage user=new UserManage();
  public static UserManage userTemp;
    //组装视图
   static public  void Init() throws IOException {

        jf.setBounds((GetScreenSize.getScreeWidth()-WIDTH)/2,(GetScreenSize.getScreeHeight()-HEIGHT)/2,WIDTH/2,HEIGHT/2);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I7.jfif")));

        Box vBox=Box.createVerticalBox();

        //组装用户名
        Box uBox=Box.createHorizontalBox();
        JLabel uLabel=new JLabel("账   号：");
        JTextField uField=new JTextField(15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box pBox=Box.createHorizontalBox();
        JLabel pLabel=new JLabel("密    码：");
        JTextField pField=new JTextField(15);
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装按钮
        Box btnBox=Box.createHorizontalBox();
        JButton loginBtn=new JButton("登录");
        JButton registerBtn=new JButton("开户");
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(150));
        btnBox.add(registerBtn);


        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的数据
                String account=uField.getText().trim();
                String password=pField.getText().trim();

                password =String.valueOf(password.hashCode());

                if(account.equals("")){
                    JOptionPane.showMessageDialog(jf,"请输入账号");
                }
                else if(password.equals("")){
                    JOptionPane.showMessageDialog(jf,"请输入密码");
                }

                boolean bol= SQL.Query(account);
                if(bol){
                   bol= MainInterface.user.getPassword().equals(password);
                }
                if(bol){
                    JOptionPane.showMessageDialog(jf,"登录成功!");
                    try {
                        uField.setText("");
                        pField.setText("");
                         LoginInterface.Init();
                        jf.dispose();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
//                    jf.dispose();
                }

                else {

                    JOptionPane.showMessageDialog(jf,"用户名或密码错误!");
                }

            }
        });


        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    uField.setText("");
                    pField.setText("");
                   RegisterInterface.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //当前界面消失
                jf.dispose();

            }
        });

        //组装各个部件
        vBox.add(Box.createVerticalStrut(75));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(75));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(75));
        vBox.add(btnBox);

        bgPanel.add(vBox);



        jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
