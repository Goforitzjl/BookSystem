package Pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JDialog {
    JButton submit;
    JPasswordField irepwd;
    JLabel name;
    JLabel age;
    JLabel gender;
    JLabel tel;
    JLabel address;

    JTextField iname;
    JTextField iage;
    JTextField igender;
    JTextField itel;
    JTextField iaddress;
    JPasswordField ipwd;

    Register mg = null;


    public Register(Login login) {
        super(login, "注册界面", true);
        mg = this;
    }


    public void start() {
        setSize(350, 400);
        setLocation(500, 200);
        Container c = getContentPane();
        c.setLayout(new GridLayout(8, 1, 0, 0));
        final Font font = new Font("黑体", Font.PLAIN, 15);
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();
        JPanel jPanel6 = new JPanel();
        JPanel jPanel7 = new JPanel();
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel7.setLayout(new FlowLayout(FlowLayout.CENTER));


        name = new JLabel("姓名:");
        iname = new JTextField(15);
        name.setFont(font);
        iname.setFont(font);

//            iname.getText(); Jtextcomponent
        JLabel pwd = new JLabel("密码:");
        ipwd = new JPasswordField(15);
        pwd.setFont(font);
        ipwd.setFont(font);


        JLabel repwd = new JLabel("确定密码:");
        irepwd = new JPasswordField(15);
        repwd.setFont(font);
        irepwd.setFont(font);

        age = new JLabel("年龄:");
        iage = new JTextField(15);
        age.setFont(font);
        iage.setFont(font);

        gender = new JLabel("性别:");
        igender = new JTextField(15);
        gender.setFont(font);
        igender.setFont(font);

        tel = new JLabel("电话号码:");
        itel = new JTextField(15);
        tel.setFont(font);
        itel.setFont(font);

        address = new JLabel("地址:");
        iaddress = new JTextField(15);
        address.setFont(font);
        iaddress.setFont(font);


        submit = new JButton("提交");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JPasswordFild.getPassword()返回的是char[]
                if (String.valueOf(ipwd.getPassword()).equals(String.valueOf(irepwd.getPassword()))) {
                    addUser();
                } else {
                    JOptionPane.showMessageDialog(null, "两次密码输入不一致", "", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        jPanel1.add(name);
        jPanel1.add(iname);
        jPanel2.add(pwd);
        jPanel2.add(ipwd);
        jPanel3.add(repwd);
        jPanel3.add(irepwd);
        jPanel4.add(age);
        jPanel4.add(iage);
        jPanel5.add(gender);
        jPanel5.add(igender);
        jPanel6.add(tel);
        jPanel6.add(itel);
        jPanel7.add(address);
        jPanel7.add(iaddress);

        c.add(jPanel1);
        c.add(jPanel2);
        c.add(jPanel3);
        c.add(jPanel4);
        c.add(jPanel5);
        c.add(jPanel6);
        c.add(jPanel7);
        c.add(submit);
        setVisible(true);
    }

    public void addUser() {
        LUser user = new LUser();
        user.setAge(Integer.valueOf(iage.getText()));
        user.setName(iname.getText());
        user.setGender(igender.getText());
        user.setPassword(String.valueOf(ipwd.getPassword()));
        user.setTel(itel.getText());
        user.setAddress(iaddress.getText());
        user.setIs_superuser(false);
        LusersDao uo = new LusersDao();
        if (uo.addUser(user) == 1) {

            JOptionPane.showMessageDialog(null, "注册成功", "", JOptionPane.PLAIN_MESSAGE);
            mg.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "注册失败，请重试", "", JOptionPane.ERROR_MESSAGE);
        }

    }


    //?????
//    class Action implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("here");
//            if (String.valueOf(ipwd.getPassword()).equals(String.valueOf(irepwd.getPassword()))) {
//                JOptionPane.showMessageDialog(null, "注册成功", "", JOptionPane.PLAIN_MESSAGE);
//                mg.dispose();
//
//            } else {
//                JOptionPane.showMessageDialog(null, "两次密码输入不一致", "", JOptionPane.ERROR_MESSAGE);
//
//            }
//        }
//    }


}


