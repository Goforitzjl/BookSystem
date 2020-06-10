package Pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.xdevapi.JsonArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class WareHouseEditDialog extends JDialog implements ActionListener{
    String name;
    String address;
    Date create_time;
    Date update_time;

    JLabel namelabel;
    JTextField nametext;
    JLabel addresslabel;
    JTextField addresstext;
    JLabel createtimelabel;
    JTextField createtimetext;
    JLabel updatetimelabel;
    JTextField updatetimetext;
    JButton save;
    JButton cancel;
    WareHouseSysMainUi parent;
    String title;
    int id;

    public WareHouseEditDialog(WareHouseSysMainUi parent, String title) {
        super(parent, title, true);
        this.parent = parent;
        this.title = title;
        init();
    }

    public void init() {

        namelabel = new JLabel("仓库名字");
        nametext = new JTextField();
        addresslabel = new JLabel("仓库地址");
        addresstext = new JTextField();
        createtimelabel = new JLabel("创建时间");
        createtimetext = new JTextField();
        updatetimelabel = new JLabel("更新时间");
        updatetimetext = new JTextField();

        if (this.title.equals("更新图书")) {
            String[] wareHouse = this.parent.getselectWareHouse();
            this.id = Integer.valueOf(wareHouse[0]);
            nametext.setText(wareHouse[1]);
            addresstext.setText(wareHouse[2]);
            createtimetext.setText(wareHouse[3]);
            updatetimetext.setText(wareHouse[4]);
        }
        buildGUI();
    }


    public void buildGUI() {
//        getContentPane()   ------>???
        setBounds(600, 200, 400, 320);
        JPanel contentpanel = new JPanel();
        GroupLayout contentpanelLayout = new GroupLayout(contentpanel);
        contentpanel.setLayout(contentpanelLayout);
        contentpanelLayout.setAutoCreateGaps(true);
        contentpanelLayout.setAutoCreateContainerGaps(true);

        save = new JButton("保存");
        save.setActionCommand("SAVE");
        save.addActionListener(this);
        cancel = new JButton("取消");
        cancel.setActionCommand("CANCEL");
        cancel.addActionListener(this);
        JPanel toolpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        toolpanel.add(save);
        toolpanel.add(cancel);

        GroupLayout.SequentialGroup hGroup = contentpanelLayout.createSequentialGroup();
        hGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(namelabel).addComponent(addresslabel).addComponent(createtimelabel).addComponent(updatetimelabel));
        hGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(nametext).addComponent(addresstext).addComponent(createtimetext).addComponent(updatetimetext)
                .addComponent(toolpanel));
        contentpanelLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = contentpanelLayout.createSequentialGroup();
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(namelabel).addComponent(nametext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(addresslabel).addComponent(addresstext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(createtimelabel).addComponent(createtimetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(updatetimelabel).addComponent(updatetimetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(toolpanel));
        contentpanelLayout.setVerticalGroup(vGroup);

        add(contentpanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "SAVE":
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                int count = -1;
                name = nametext.getText();
                address = addresstext.getText();
                try {
                    create_time = sdf.parse(createtimetext.getText());
                    update_time = sdf.parse(updatetimetext.getText());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "你输入的日格式有误(yyyy-MM-dd)",
                            "提示", JOptionPane.ERROR_MESSAGE);
                }
                if (this.title.equals("更新图书")) {
                    WareHouse wareHouse1=new WareHouse(id,name,address,create_time,update_time);
                    count = WareHouseDao.getInstance().updateWareHouse(wareHouse1);
                } else if (this.title.equals("添加图书")) {
                    WareHouse wareHouse2=new WareHouse(name,address,create_time,update_time);
                    count=WareHouseDao.getInstance().addWareHouse(wareHouse2);
                }


                if (count != 1) {
                    JOptionPane.showMessageDialog(this, "保存失败", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "保存成功", "提示", JOptionPane.PLAIN_MESSAGE);
//                    this.parent.parent.refreshBookDatas();
                    this.dispose();
                }
                break;
            case "CANCEL":
                this.dispose();
                break;
            default:
                break;

        }
    }


}


