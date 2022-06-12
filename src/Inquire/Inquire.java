package Inquire;

import ATM.Main;
import LoginInterface.LoginInterface;
import MainInterface.MainInterface;
import MainInterface.BackGroundPanel;
import MainInterface.GetScreenSize;
import RegisterInterface.verificationCreat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


public class Inquire extends MainInterface {


public static void Init() throws IOException {

    JFrame jf=new JFrame(user.getUsername() +"  您好!");
    Main.Inquire_count=1;
    jf.setBounds((GetScreenSize.getScreeWidth()-WIDTH)/2,(GetScreenSize.getScreeHeight()-HEIGHT)/2,WIDTH/2,HEIGHT/2);
    jf.setResizable(false);
    jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


    BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I6.jpg")));



    //个人信息  姓名 账号 余额
    Box vBox=Box.createVerticalBox();

    Box uhBox = Box.createHorizontalBox();
    JLabel uLabel = new JLabel("姓  名：");
    JLabel ulaybel=new JLabel(user.getUsername());

    uhBox.add(uLabel);
    uhBox.add(Box.createHorizontalStrut(20));
    uhBox.add(ulaybel);


    Box chBox = Box.createHorizontalBox();
    JLabel cLabel = new JLabel("账  户：");
    JLabel claybel=new JLabel(user.getCount());
    chBox.add(cLabel);
    chBox.add(Box.createHorizontalStrut(20));
    chBox.add(claybel);


    Box bhBox = Box.createHorizontalBox();
    JLabel bLabel= new JLabel("余  额："+ user.getBalance());

    JButton refreshBtn=new JButton("刷 新");
    refreshBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            bLabel.setText("余  额："+ user.getBalance());
            bLabel.updateUI();

        }
    });



    bhBox.add(bLabel);
//        bhBox.add(Box.createVerticalStrut(20));
    bhBox.add(Box.createHorizontalStrut(20));

    JButton backBtn=new JButton("上一步");
    backBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jf.dispose();
            try {
                LoginInterface.Init();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });

    vBox.add(Box.createVerticalStrut(60));
    vBox.add(uhBox);
    vBox.add(Box.createVerticalStrut(20));
    vBox.add(chBox);
    vBox.add(Box.createVerticalStrut(20));
    vBox.add(bhBox);

    vBox.add(Box.createVerticalStrut(30));
    Box hBox=Box.createHorizontalBox();
    hBox.add(backBtn);
    hBox.add(Box.createHorizontalStrut(40));
    hBox.add(refreshBtn);
    vBox.add(Box.createVerticalStrut(30));
    vBox.add(hBox);


    bgPanel.add(vBox);

    jf.add(bgPanel);
    jf.setVisible(true);


    }



}
