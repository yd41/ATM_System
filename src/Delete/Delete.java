package Delete;

import LoginInterface.LoginInterface;
import MS.SQL;
import MainInterface.BackGroundPanel;
import MainInterface.GetScreenSize;
import MainInterface.MainInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Delete extends MainInterface {


    public static void Init() throws IOException {

        JFrame jf=new JFrame(user.getUsername() +"  您好!");

        jf.setBounds((GetScreenSize.getScreeWidth() - WIDTH) / 2, (GetScreenSize.getScreeHeight() - HEIGHT) / 2, WIDTH / 2, HEIGHT / 2);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I2.png")));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box opBox = Box.createHorizontalBox();
        JLabel opLabel = new JLabel("请输入密码：");
        JTextField opField = new JTextField(15);
        opBox.add(opLabel);
        opBox.add(Box.createHorizontalStrut(20));
        opBox.add(opField);

        JButton backBtn = new JButton("上一步");
        JButton okBtn = new JButton(" 确    认 ");

        Box hBtn = Box.createHorizontalBox();
        hBtn.add(backBtn);
        hBtn.add(Box.createHorizontalStrut(60));
        hBtn.add(okBtn);

        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(100));
        vBox.add(opBox);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(hBtn);
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(opField.getText().trim().hashCode());
                if (MainInterface.user.getPassword().equals(password)) {

                    int choice = JOptionPane.showConfirmDialog(jf, "确定删除帐户", "销户警告", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {

                        SQL.Delete(MainInterface.user.getCount(), password);
                        MainInterface.user = null;
                        try {
                            jf.dispose();

                            JOptionPane.showMessageDialog(jf,"删除成功!");
                            MainInterface.Init();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            jf.dispose();
                            LoginInterface.Init();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                } else {

                    JOptionPane.showMessageDialog(jf, "密码错误!");
                }
            }
        });


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

        bgPanel.add(vBox);
        bgPanel.add(Box.createVerticalStrut(20));

        jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
