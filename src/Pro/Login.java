package Pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
    public static void main(String[] args) {
        new Login().start();
    }

    JButton login;
    JButton register;

    JTextField iname;
    JPasswordField ipwd;


    public void start() {
        gui();
        login.setActionCommand("login");
        login.addActionListener(new Action());
        register.setActionCommand("register");
        register.addActionListener(new Action());

    }

    public void gui() {
        setTitle("登录界面");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocation(500, 200);
        Container c = getContentPane();
        c.setLayout(new GridLayout(3, 1, 5, 5));
        JLabel title = new JLabel("图书管理系统登录");
        Font font = new Font("黑体", Font.BOLD, 20);
        Font font1 = new Font("黑体", Font.BOLD, 35);
        Font font2 = new Font("黑体", Font.BOLD, 25);
        Font font3 = new Font("黑体", Font.PLAIN, 15);
        title.setFont(font1);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel jPanel2 = new JPanel();
        JPanel jPanel21 = new JPanel();
        JPanel jPanel22 = new JPanel();
        JPanel jPanel3 = new JPanel();

        jPanel2.setLayout(new GridLayout(2, 1, 5, 5));
        JLabel name = new JLabel("姓名：");
        name.setFont(font);
        iname = new JTextField(15);
        iname.setFont(font3);
        JLabel pwd = new JLabel("密码: ");
        pwd.setFont(font);
        ipwd = new JPasswordField(15);
        ipwd.setFont(font3);

        jPanel21.add(name);
        jPanel21.add(iname);

        jPanel22.add(pwd);
        jPanel22.add(ipwd);

        jPanel2.add(jPanel21);
        jPanel2.add(jPanel22);

        jPanel3.setLayout(new GridLayout(1, 2));
        login = new JButton("登录");
        login.setFont(font2);
        register = new JButton("注册");
        register.setFont(font2);
        jPanel3.add(login);
        jPanel3.add(register);
        c.add(title);
        c.add(jPanel2);
        c.add(jPanel3);


        setVisible(true);
    }

    public void register() {

        new Register(this).start();
    }

    public void login() {
        User user = new User();
        user.setName(iname.getText());
        user.setPassword(String.valueOf(ipwd.getPassword()));
        boolean flag = new UsersDao().checkUser(user);
        if (flag) {
            this.dispose();
            new Index().start();
            System.out.println("登录成功");
        } else {
            JOptionPane.showMessageDialog(null, "密码或用户错误", "", JOptionPane.ERROR_MESSAGE);
            System.out.println("登录失败");
        }

    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "login":
                    login();
                    break;
                case "register":
                    register();
                    break;
                case "submit":

                default:
                    break;
            }
        }
    }

}
