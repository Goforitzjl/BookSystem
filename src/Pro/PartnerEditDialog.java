package Pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartnerEditDialog extends JDialog implements ActionListener {

    String name;
    String type;
    String address;
    Float discount;

    String bankname;
    String bankaccount;
    String taxnumber;
    String contactperson;
    String contacttel;
    Date create_time;
    Date update_time;

    JLabel namelabel;
    JTextField nametext;
    JLabel typelabel;
    JTextField typetext;
    JLabel discountlabel;
    JTextField discounttext;
    JLabel addresslabel;
    JTextField addresstext;
    JLabel banknamelabel;
    JTextField banknametext;
    JLabel bankaccountlabel;
    JTextField bankaccounttext;
    JLabel taxnumberlabel;
    JTextField taxnumbertext;
    JLabel contactpersonlabel;
    JTextField contactpersontext;
    JLabel contacttellabel;
    JTextField contactteltext;
    JLabel updatetimelabel;
    JTextField updatetimetext;
    JLabel createtimelabel;
    JTextField createtimetext;
    JButton save;
    JButton cancel;
    PartnerSysMainUi parent;
    String title;
    int id;

    public PartnerEditDialog(PartnerSysMainUi parent, String title) {
        super(parent, title, true);
        this.parent = parent;
        this.title = title;
        init();
    }

    public void init() {

        namelabel=new JLabel("单位名称");
        nametext=new JTextField();
        typelabel=new JLabel("单位类型");
        typetext=new JTextField();
        discountlabel=new JLabel("折扣率");
        discounttext=new JTextField();
        addresslabel=new JLabel("单位地址");
        addresstext=new JTextField();;
        banknamelabel=new JLabel("开户银行");
        banknametext=new JTextField();;
        bankaccountlabel=new JLabel("银行账号");
        bankaccounttext=new JTextField();;
        taxnumberlabel=new JLabel("税号");
        taxnumbertext=new JTextField();;
        contactpersonlabel=new JLabel("联系人");
        contactpersontext=new JTextField();;
        contacttellabel=new JLabel("联系人电话");
        contactteltext=new JTextField();;
        updatetimelabel=new JLabel("更新时间");
        updatetimetext=new JTextField();;
        createtimelabel=new JLabel("创建时间");
        createtimetext=new JTextField();;

        if (this.title.equals("更新图书")) {
            String[] Partner = this.parent.getselectPartner();
            this.id = Integer.valueOf(Partner[0]);
            nametext.setText(Partner[1]);
            typetext.setText(Partner[2]);
            discounttext.setText(Partner[3]);
            addresstext.setText(Partner[4]);
            banknametext.setText(Partner[5]);
            bankaccounttext.setText(Partner[6]);
            taxnumbertext.setText(Partner[7]);
            contactpersontext.setText(Partner[8]);
            contactteltext.setText(Partner[9]);
            updatetimetext.setText(Partner[10]);
            createtimetext.setText(Partner[11]);
        }
        buildGUI();
    }


    public void buildGUI() {
//        getContentPane()   ------>???
        setBounds(600, 200, 400, 380);
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
                .addComponent(namelabel).addComponent(typelabel).addComponent(discountlabel).addComponent(addresslabel)
        .addComponent(banknamelabel).addComponent(bankaccountlabel).addComponent(taxnumberlabel).addComponent(contactpersonlabel)
        .addComponent(contacttellabel).addComponent(updatetimelabel).addComponent(createtimelabel));
        hGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(nametext).addComponent(typetext).addComponent(discounttext).addComponent(addresstext)
                .addComponent(banknametext).addComponent(bankaccounttext).addComponent(taxnumbertext).addComponent(contactpersontext)
                .addComponent(contactteltext).addComponent(updatetimetext).addComponent(createtimetext))
                .addComponent(toolpanel);
        contentpanelLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = contentpanelLayout.createSequentialGroup();
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(namelabel).addComponent(nametext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(typelabel).addComponent(typetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(discountlabel).addComponent(discounttext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(addresslabel).addComponent(addresstext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(banknamelabel).addComponent(banknametext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(bankaccountlabel).addComponent(bankaccounttext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(taxnumberlabel).addComponent(taxnumbertext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(contactpersonlabel).addComponent(contactpersontext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(contacttellabel).addComponent(contactteltext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(updatetimelabel).addComponent(updatetimetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(createtimelabel).addComponent(createtimetext));
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
                type = typetext.getText();
                discount = Float.valueOf(discounttext.getText());
                address = addresstext.getText();
                bankname = banknametext.getText();
                bankaccount = bankaccounttext.getText();
                taxnumber = taxnumbertext.getText();
                contactperson = contactpersontext.getText();
                contacttel = contactteltext.getText();
                try {
                    create_time = sdf.parse(createtimetext.getText());
                    update_time = sdf.parse(updatetimetext.getText());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "你输入的日格式有误(yyyy-MM-dd)",
                            "提示", JOptionPane.ERROR_MESSAGE);
                }
                if (this.title.equals("更新图书")) {
                    Partner Partner1 = new Partner(id,name,type,discount,address,bankname,bankaccount,taxnumber,contactperson,contacttel,create_time,update_time);
                    count = PartnerDao.getInstance().updatePartner(Partner1);
                } else if (this.title.equals("添加图书")) {
                    Partner Partner2 = new Partner(id,name,type,discount,address,bankname,bankaccount,taxnumber,contactperson,contacttel,create_time,update_time);
                    count = PartnerDao.getInstance().addPartner(Partner2);
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

