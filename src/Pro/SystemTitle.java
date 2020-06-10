package Pro;

import javax.swing.*;
import java.awt.*;

public class SystemTitle extends JFrame{

    public JPanel getSysTitle(){
        JPanel jp=new JPanel(new BorderLayout());

        final Font font = new Font("黑体", Font.BOLD, 35);
        final JLabel jlabel = new JLabel("图书进销存管理系统");
        jlabel.setFont(font);
        jp.add(BorderLayout.EAST,jlabel);
        return jp;
    }
}
