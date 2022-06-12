package LoginInterface;

import Change.Change;
import Delete.Delete;
import Deposit.Deposit;
import Inquire.Inquire;
import MainInterface.BackGroundPanel;
import MainInterface.GetScreenSize;
import MainInterface.MainInterface;
import Transfer.Transfer;
import Withdraw.Withdraw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class LoginInterface extends MainInterface {



 static   public void Init( ) throws IOException {

     JFrame jf=new JFrame(user.getUsername() +"  您好!");
     jf.setBounds(( GetScreenSize.getScreeWidth()-WIDTH)/2,(GetScreenSize.getScreeHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));

        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/I5.jpg")));
        bgPanel.setLayout(new BorderLayout());


        //个人信息  姓名 账号 余额
        Box vBox=Box.createVerticalBox();

        Box uhBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("          姓  名：");
        JLabel ulaybel=new JLabel(user.getUsername());
        uhBox.add(uLabel);
        uhBox.add(Box.createHorizontalStrut(20));
        uhBox.add(ulaybel);


        Box chBox = Box.createHorizontalBox();
        JLabel cLabel = new JLabel("            账  户：");
        JLabel claybel=new JLabel(user.getCount());
        chBox.add(cLabel);
        chBox.add(Box.createHorizontalStrut(20));
        chBox.add(claybel);



        vBox.add(Box.createVerticalStrut(20));
        vBox.add(uhBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(chBox);
        vBox.add(Box.createVerticalStrut(20));

        //存款
        JButton depositBtn=new JButton("存        款");
        //取款
        JButton withdrawBtn=new JButton("取        款");
        //改密
        JButton changeBtn=new JButton("修改密码");
        //转账
        JButton transferBtn=new JButton("转        账");
        //退出
        JButton exitBtn=new JButton("退        出");

        JButton inquireBtn=new JButton("查        询");

        JButton deleteBtn=new JButton("注        销");


        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Deposit.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                jf.dispose();
            }
        });

        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Withdraw.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                jf.dispose();
            }
        });

        changeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Change.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                jf.dispose();
            }
        });
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Transfer.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                jf.dispose();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    jf.dispose();
                  MainInterface.Init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });


inquireBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jf.dispose();
            Inquire.Init();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});

deleteBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jf.dispose();
            Delete.Init();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});



        Box box1= Box.createVerticalBox();

        bgPanel.add(vBox,BorderLayout.WEST);

        box1.add(Box.createVerticalStrut(100));
        box1.add(depositBtn);
        box1.add(Box.createVerticalStrut(50));
        box1.add(withdrawBtn);
        box1.add(Box.createVerticalStrut(50));
        box1.add(transferBtn);
        box1.add(Box.createVerticalStrut(50));
        box1.add(inquireBtn);
        box1.add(Box.createVerticalStrut(50));
        box1.add(changeBtn);
        box1.add(Box.createVerticalStrut(50));
        box1.add(exitBtn);
        box1.add(Box.createVerticalStrut(50));
        box1.add(deleteBtn);


     bgPanel.add(box1,BorderLayout.EAST);




        jf.add(bgPanel);
        jf.setResizable(false);
        jf.setVisible(true);

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }



}
