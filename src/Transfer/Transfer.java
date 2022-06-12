package Transfer;

import ATM.Main;
import Deposit.IsNum;
import LoginInterface.LoginInterface;
import MS.SQL;
import MainInterface.BackGroundPanel;
import MainInterface.GetScreenSize;
import MainInterface.MainInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Transfer extends MainInterface {

    static public void Init() throws IOException {

        JFrame jf=new JFrame(user.getUsername() +"  您好!");
        jf.setBounds((GetScreenSize.getScreeWidth() - WIDTH) / 2, (GetScreenSize.getScreeHeight() - HEIGHT) / 2, WIDTH / 2, HEIGHT / 2);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I8.jfif")));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();


        Box cBox = Box.createHorizontalBox();
        JLabel cLabel = new JLabel("账户:");
        cBox.add(Box.createVerticalStrut(10));
        JTextField cField = new JTextField(15);
        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(20));
        cBox.add(cField);


        Box mBox = Box.createHorizontalBox();
        JLabel mLabel = new JLabel("金额:");
        mBox.add(Box.createVerticalStrut(10));
        JTextField mField = new JTextField(15);
        mBox.add(mLabel);
        mBox.add(Box.createHorizontalStrut(20));
        mBox.add(mField);


        Box hBtn = Box.createHorizontalBox();
        JButton backBtn = new JButton("上一步");
        JButton okBtn = new JButton("  确   定 ");
        hBtn.add(backBtn);
        hBtn.add(Box.createHorizontalStrut(60));
        hBtn.add(okBtn);

        vBox.add(Box.createVerticalStrut(70));
        vBox.add(cBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(mBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(hBtn);



        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String account = cField.getText().trim();
                String money = mField.getText().trim();
                if(account.equals("")){
                    JOptionPane.showMessageDialog(jf,"请输入账户!");
                    return;
                }
                boolean bol = SQL.Query(account, false);
                if (!bol) {
                    cField.setText("");
                    mField.setText("");
                    JOptionPane.showMessageDialog(jf, "查无此用户");

                } else if (!IsNum.isNumberString(money)) {
                    cField.setText("");
                    mField.setText("");
                    JOptionPane.showMessageDialog(jf, "请输入正数");
                } else {
                    if(Double.parseDouble(money)> Main.upper){
                        mField.setText("");
                        JOptionPane.showMessageDialog(jf, "单笔限额"+Main.upper+"元");
                    }
                   else if (Double.parseDouble(money) > MainInterface.user.getBalance()) {
                        mField.setText("");
                        JOptionPane.showMessageDialog(jf, "余额不足");
                    }
                    else if(Double.parseDouble(money)==0){
                        JOptionPane.showMessageDialog(jf,"请输入正数");
                        mField.setText("");
                    }
                    else {
                  if(MainInterface.user.getCount().equals(account)){
                      cField.setText("");
                      JOptionPane.showMessageDialog(jf,"不可给自身账户转账");
                  }
                  else {
                      cField.setText("");
                      mField.setText("");
                      SQL.Query(account,false);
                      SQL.Update(account, Double.parseDouble(money)+MainInterface.userTemp.getBalance(),false);
                      SQL.Update(MainInterface.user.getCount(), -Double.parseDouble(money),true);
                      MainInterface.user.setBalance(MainInterface.user.getBalance()-Double.parseDouble(money));
                      JOptionPane.showMessageDialog(jf, "转账成功");

                  }
                    }

                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cField.setText("");
                    mField.setText("");
                    LoginInterface.Init();
                    jf.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        bgPanel.add(vBox);
        jf.add(bgPanel);

        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
