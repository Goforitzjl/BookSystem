package Pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseDetailEditDialog extends JDialog implements ActionListener {

    String name;
    String author;
    String barcode;
    String presell;
    String cost;
    String discount;
    String discountcost;
    String quantity;
    String amount;
    String discountamount;

    JLabel namelabel;
    JTextField nametext;
    JLabel authorlabel;
    JTextField authortext;
    JLabel barcodelabel;
    JTextField barcodetext;
    JLabel preselllabel;
    JTextField preselltext;
    JLabel costlabel;
    JTextField costtext;
    JLabel discountlabel;
    JTextField discounttext;
    JLabel discountcostlabel;
    JTextField discountcosttext;
    JLabel quantitylabel;
    JTextField quantitytext;
    JLabel amountlabel;
    JTextField amounttext;
    JLabel discountamountlabel;
    JTextField discountamounttext;

    JButton save;
    JButton cancel;
    PurchaseDetailSysMainUi parent;
    String title;
    int id;

    public PurchaseDetailEditDialog(PurchaseDetailSysMainUi parent, String title) {
        super(parent, title, true);
        this.parent = parent;
        this.title = title;
        init();
    }

    public void init() {
        String discount=this.parent.getPartnerDiscount();
        namelabel = new JLabel("图书名称");
        nametext = new JTextField();
        authorlabel = new JLabel("作者");
        authortext = new JTextField();
        barcodelabel = new JLabel("条形码");
        barcodetext = new JTextField();
        preselllabel = new JLabel("预售价");
        preselltext = new JTextField();
        costlabel = new JLabel("进货原价");
        costtext = new JTextField();
        discountlabel = new JLabel("折扣率");
        discounttext = new JTextField();
        discounttext.setText(discount);
        discountcostlabel = new JLabel("进货折扣价");
        discountcosttext = new JTextField();
        quantitylabel = new JLabel("图书数量");
        quantitytext = new JTextField();
        amountlabel = new JLabel("总金额");
        amounttext = new JTextField();
        discountamountlabel = new JLabel("折后总金额");
        discountamounttext = new JTextField();


        if (this.title.equals("更新图书")) {
            String[] values = this.parent.getTablemodelValues();
            nametext.setText(values[0]);
            authortext.setText(values[1]);
            barcodetext.setText(values[2]);
            preselltext.setText(values[3]);
            costtext.setText(values[4]);
            discounttext.setText(values[5]);
            discountcosttext.setText(values[6]);
            quantitytext.setText(values[7]);
            amounttext.setText(values[8]);
            discountamounttext.setText(values[9]);
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
                .addComponent(namelabel).addComponent(authorlabel).addComponent(barcodelabel).addComponent(preselllabel)
                .addComponent(costlabel).addComponent(discountlabel).addComponent(discountcostlabel).addComponent(quantitylabel)
                .addComponent(amountlabel).addComponent(discountamountlabel));
        hGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(nametext).addComponent(authortext).addComponent(barcodetext).addComponent(preselltext)
                .addComponent(costtext).addComponent(discounttext).addComponent(discountcosttext).addComponent(quantitytext)
                .addComponent(amounttext).addComponent(discountamounttext)
                .addComponent(toolpanel));
        contentpanelLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = contentpanelLayout.createSequentialGroup();
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(namelabel).addComponent(nametext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(authorlabel).addComponent(authortext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(barcodelabel).addComponent(barcodetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(preselllabel).addComponent(preselltext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(costlabel).addComponent(costtext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(discountlabel).addComponent(discounttext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(discountcostlabel).addComponent(discountcosttext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(quantitylabel).addComponent(quantitytext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(amountlabel).addComponent(amounttext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(discountamountlabel).addComponent(discountamounttext));
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
                ;
                name = nametext.getText();
                author = authortext.getText();
                barcode = barcodetext.getText();
                presell = preselltext.getText();
                cost = costtext.getText();
                discount = discounttext.getText();
                discountcost = discountcosttext.getText();
                quantity = quantitytext.getText();
                amount = amounttext.getText();
                discountamount = discountamounttext.getText();
                String[] values = {name, author, barcode, presell, cost, discount, discountcost, quantity, amount, discountamount};
                if (this.title.equals("更新图书")) {
                    this.parent.setTablemodelValues(values);
                } else if (this.title.equals("添加图书")) {
                    this.parent.addTablemodelValues(values);
                }
                this.dispose();
                break;
            case "CANCEL":
                this.dispose();
                break;
            default:
                break;

        }
    }

}

