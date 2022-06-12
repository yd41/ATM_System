package Deposit;

import LoginInterface.LoginInterface;
import MS.SQL;
import MainInterface.BackGroundPanel;
import MainInterface.MainInterface;
import MainInterface.GetScreenSize;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Deposit extends MainInterface {




    public static void Init() throws IOException {

        JFrame jf=new JFrame(user.getUsername() +"  您好!");
        jf.setBounds(( GetScreenSize.getScreeWidth()- WIDTH) / 2, (GetScreenSize.getScreeHeight() - HEIGHT) / 2, WIDTH/2, HEIGHT/2);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I1.jfif")));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();




        Box dBox=Box.createHorizontalBox();
        JLabel dLabel=new JLabel("存款：");
        dBox.add(Box.createVerticalStrut(10));
        JTextField dField=new JTextField(15);
        dBox.add(dLabel);
        dBox.add(Box.createHorizontalStrut(20));
        dBox.add(dField);




       JButton okBtn=new JButton("确     认");

       okBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String money=dField.getText().trim();

               if(IsNum.isNumberString(money)){

                  double Money = Double.parseDouble(money);

if(Money>0){
                     if(SQL.Update(user.getCount(),Money,true)) {

                         user.setBalance(user.getBalance()+Money);

                         JOptionPane.showMessageDialog(jf, "你已成功充值：" + Money + "元");
//                        JOptionPane.showMessageDialog(jf,MainInterface.user.getBalance());

                         try {
                             LoginInterface.Init();

                         } catch (IOException ex) {
                             ex.printStackTrace();
                         }
                         jf.dispose();

                     }

                    }
               else {
    JOptionPane.showMessageDialog(jf,"请输入正数");

}
               }
                    else {
                   dField.setText("");

                   JOptionPane.showMessageDialog(jf,"请输入正数");
                    }

               }
       });

        JButton backBtn=new JButton("上一步");

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
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
