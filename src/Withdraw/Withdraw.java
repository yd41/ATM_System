package Withdraw;


import ATM.Main;
import Deposit.IsNum;
import Deposit.IsNum;
import Inquire.Inquire;
import LoginInterface.LoginInterface;
import MS.SQL;
import MainInterface.BackGroundPanel;
import MainInterface.GetScreenSize;
import MainInterface.MainInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Withdraw extends MainInterface{




    public static void Init() throws IOException {

        JFrame jf=new JFrame(user.getUsername() +"  您好!");
        jf.setBounds((GetScreenSize.getScreeWidth() - WIDTH) / 2, (GetScreenSize.getScreeHeight() - HEIGHT) / 2, WIDTH / 2, HEIGHT / 2);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I5.jpg")));
        bgPanel.setBounds(0, 0, WIDTH/2, HEIGHT/2);

        Box vBox = Box.createVerticalBox();


        Box dBox = Box.createHorizontalBox();
        JLabel dLabel = new JLabel("取款：");
        dBox.add(Box.createVerticalStrut(10));
        JTextField dField = new JTextField(15);
        dBox.add(dLabel);
        dBox.add(Box.createHorizontalStrut(20));
        dBox.add(dField);


        JButton okBtn = new JButton("确      认");

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String money = dField.getText().trim();

                if (IsNum.isNumberString(money)&&Double.parseDouble(money)<= Main.upper) {

                    double Money = Double.parseDouble(money);

                    if (Money > 0) {

                        if (SQL.Update(MainInterface.user.getCount(), -Money,true)) {
                            MainInterface.user.setBalance(MainInterface.user.getBalance() - Money);
                            dField.setText("");
                            JOptionPane.showMessageDialog(jf, "你已取出: " + Money + "元");

                            try {
                                jf.dispose();
                                LoginInterface.Init();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                        else {
                            dField.setText("");
                            JOptionPane.showMessageDialog(jf,"余额不足!!!\n当前余额为:"+MainInterface.user.getBalance());
                            try {
                                Withdraw.Init();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }


                    }else {
                        JOptionPane.showMessageDialog(jf, "请输入正数");
                    }

                } else {
                    if(IsNum.isNumberString(money)){
                        JOptionPane.showMessageDialog(jf, "单笔限额"+Main.upper+"元");
                    }
                    else {
                        JOptionPane.showMessageDialog(jf, "请输入正数");
                    }

                }

            }
        });

        JButton backBtn=new JButton("上一步");

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dField.setText("");
                    jf.dispose();
                    LoginInterface.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Box HBtn=Box.createHorizontalBox();
        HBtn.add(backBtn);
        HBtn.add(Box.createHorizontalStrut(60));
        HBtn.add(okBtn);

        vBox.add(Box.createVerticalStrut(100));
        vBox.add(dBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(HBtn);

        bgPanel.add(vBox);
        jf.add(bgPanel);


        jf.setResizable(false);
        jf.setVisible(true);

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
