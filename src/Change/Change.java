package Change;


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

public class Change extends MainInterface {



    public static void Init() throws IOException {
        JFrame jf=new JFrame(user.getUsername() +"  您好!");

        jf.setBounds((GetScreenSize.getScreeWidth() - WIDTH) / 2, (GetScreenSize.getScreeHeight() - HEIGHT) / 2, WIDTH/2, HEIGHT/2);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I3.png")));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();

        Box opBox=Box.createHorizontalBox();
        JLabel opLabel=new JLabel("请输入原密码：");
        opBox.add(Box.createVerticalStrut(10));
        JTextField opField=new JTextField(15);
        opBox.add(opLabel);
        opBox.add(Box.createHorizontalStrut(20));
        opBox.add(opField);

        Box npBox=Box.createHorizontalBox();
        JLabel npLabel=new JLabel("请输入新密码：");
        npBox.add(Box.createVerticalStrut(10));
        JTextField npField=new JTextField(15);
        npBox.add(npLabel);
        npBox.add(Box.createHorizontalStrut(20));
        npBox.add(npField);


        JButton okBtn=new JButton(" 确 认 ");

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               String oldPassword= user.getPassword();
               String IoldPassword=String.valueOf(opField.getText().trim().hashCode());
               String newPassword= String.valueOf(npField.getText().trim().hashCode());
               if(!oldPassword.equals(IoldPassword))
                {
                    opField.setText("");
                    JOptionPane.showMessageDialog(jf,"密码错误,请重新输入!");
                }
               else {
                   user.setPassword(newPassword);
                   SQL.Update(user.getCount(),newPassword);
                   opField.setText("");
                   npField.setText("");
                   JOptionPane.showMessageDialog(jf,"你已成功更改密码：");
                   try {
                       LoginInterface.Init();
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
                   jf.dispose();
               }
            }
        });


        JButton backBtn=new JButton("上一步");

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    opField.setText("");
                    npField.setText("");
                    jf.dispose();
                    LoginInterface.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Box HBtn=Box.createHorizontalBox();
        HBtn.add(backBtn);
        HBtn.add(Box.createHorizontalStrut(80));
        HBtn.add(okBtn);

        vBox.add(Box.createVerticalStrut(70));
        vBox.add(opBox);
        vBox.add(Box.createVerticalStrut(70));
        vBox.add(npBox);
        vBox.add(Box.createVerticalStrut(70));
        vBox.add(HBtn);
        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

}
