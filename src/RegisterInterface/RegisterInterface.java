package RegisterInterface;

import MS.SQL;
import MainInterface.BackGroundPanel;
import MainInterface.GetScreenSize;
import MainInterface.MainInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Locale;


public class RegisterInterface extends MainInterface{

   static JFrame jf = new JFrame("注册界面");
    static public void Init() throws IOException {

        jf.setBounds((GetScreenSize.getScreeWidth() - WIDTH) / 2, (GetScreenSize.getScreeHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("image/中国农业银行.jfif")));


        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("image/image3.jpeg")));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();

        //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("姓   名:     ",10);
        JTextField uField = new JTextField(15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装银行卡
        Box cBox = Box.createHorizontalBox();
        JLabel cLabel = new JLabel("银行卡号: ",10);
        JTextField cField = new JTextField(15);
        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(20));
        cBox.add(cField);

        //组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码:     ",10);
        JTextField pField = new JTextField(15);
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);


        //确认密码

        Box checkBox = Box.createHorizontalBox();
        JLabel checkLabel = new JLabel("验证密码:  ",10);
        JTextField checkField = new JTextField(15);
        checkBox.add(checkLabel);
        checkBox.add(Box.createHorizontalStrut(20));
        checkBox.add(checkField);


        //组装手机号

        int num=11;//电话号码位数

        Box tBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("  手机号:   ",10);
        JTextField tField = new JTextField(15);
        tBox.add(tLabel);
        tBox.add(Box.createHorizontalStrut(20));
        tBox.add(tField);

        //组装性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别:     ",10);
        JRadioButton maleBtn = new JRadioButton("男", false);
        JRadioButton fmaleBtn = new JRadioButton("女", false);

        //实现单选效果
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleBtn);
        bg.add(fmaleBtn);

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(fmaleBtn);
        gBox.add(Box.createHorizontalStrut(120));

        //组装验证码
        Box vcBox = Box.createHorizontalBox();
        JLabel vcLabel = new JLabel(" 验证码:     ",10);
        JTextField vcField = new JTextField(4);
        JLabel vcImage = new JLabel(verificationCreat.genCode());
        //设置提示信息
        vcImage.setToolTipText("点击刷新");

        vcImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vcImage.setText(verificationCreat.genCode());
                vcImage.updateUI();
            }
        });


        vBox.add(Box.createVerticalStrut(60));
        vcBox.add(vcLabel);
        vcBox.add(Box.createHorizontalStrut(20));
        vcBox.add(vcField);
        vcBox.add(Box.createHorizontalStrut(20));
        vcBox.add(vcImage);


        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton registerBtn = new JButton("注册");
        JButton backBtn = new JButton("上一步");
        btnBox.add(backBtn);
        btnBox.add(Box.createHorizontalStrut(120));
        btnBox.add(registerBtn);


        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户录入信息
                String username = uField.getText().trim();
                String account = cField.getText().trim();
                String password = pField.getText().trim();
                String phoneNum = tField.getText().trim();
                String gender = bg.isSelected(maleBtn.getModel()) ? "男" : bg.isSelected(fmaleBtn.getModel()) ? "女" : "";
                String checkCode = vcField.getText().trim();
                String checkPassword = checkField.getText().trim();
                if (username.equals("")) {
                    JOptionPane.showMessageDialog(jf, "请输入用户名");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                } else if (account.equals("")) {
                    JOptionPane.showMessageDialog(jf, "请输入卡号");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(jf, "请设置密码");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                } else if (password.length() != 6) {
                    JOptionPane.showMessageDialog(jf, "请设置六位数密码");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                } else if (checkPassword.equals("") || !checkPassword.equals(password)) {
                    JOptionPane.showMessageDialog(jf, "请确认密码");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                }
                else if (phoneNum.equals("")) {
                    JOptionPane.showMessageDialog(jf, "请输入手机号");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();

                } else if (phoneNum.length() != num) {

                    JOptionPane.showMessageDialog(jf, String.format("请输入%s位电话号码", num));
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                } else if (gender.equals("")) {
                    JOptionPane.showMessageDialog(jf, "请选择性别");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                } else if (!checkCode.toUpperCase(Locale.ROOT).equals(vcImage.getText().toUpperCase(Locale.ROOT))) {
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                    JOptionPane.showMessageDialog(jf, "验证码错误，请重试");
                } else {

                    if (SQL.Query(account)) {
                        JOptionPane.showMessageDialog(jf, "用户已存在，无法注册");
                        vcImage.setText(verificationCreat.genCode());
                        vcImage.updateUI();
                    } else {

                        SQL.Insert(account, String.valueOf(password.hashCode()), 0.0, username, phoneNum, gender);
                        JOptionPane.showMessageDialog(jf, "注册成功");
                        uField.setText("");
                        cField.setText("");
                        pField.setText("");
                        uField.setText("");
                        tField.setText("");
                        vcField.setText("");
                        checkField.setText("");
                        vcImage.setText(verificationCreat.genCode());
                        vcImage.updateUI();
                        try {
                            MainInterface.Init();
                            jf.dispose();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    uField.setText("");
                    cField.setText("");
                    pField.setText("");
                    uField.setText("");
                    tField.setText("");
                    vcField.setText("");
                    checkField.setText("");
                    vcImage.setText(verificationCreat.genCode());
                    vcImage.updateUI();
                    MainInterface.Init();
                    jf.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //组装
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(cBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(checkBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(tBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(gBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(vcBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(btnBox);
        vBox.add(Box.createVerticalStrut(30));


        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
