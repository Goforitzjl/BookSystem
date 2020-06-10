package Pro;

import java.util.Iterator;
import java.util.Vector;

public class PartnerTableHandle {


    public static Vector<Vector<String>> getValues() {
        Vector<Partner> PartnerList = PartnerDao.getInstance().getPartnerList("所有");
        Vector<Vector<String>> tableValues = convertTable(PartnerList);
        return tableValues;
    }


    public static Vector<Vector<String>> convertTable(Vector<Partner> PartnerList) {
        Vector<Vector<String>> tableValues = new Vector<>();

        Iterator it = PartnerList.iterator();
        while (it.hasNext()) {
            Partner Partner = (Partner) it.next();
            String id=String.valueOf(Partner.getId());
            String name = Partner.getName();
            String type = Partner.getType();
            String discount=String.valueOf(Partner.getDiscount());
            String address = Partner.getAddress();
            String bank_name = Partner.getBank_name();
            String bank_account = Partner.getBank_count();
            String tax_number = Partner.getTax_number();
            String contact_person = Partner.getContact_person();
            String contact_tel = Partner.getContact_tel();
            String c_t = String.valueOf(Partner.getCreate_time());
            String u_t = String.valueOf(Partner.getUpdate_time());



            Vector<String> rowValues = new Vector<>();
            rowValues.add(id);
            rowValues.add(name);
            rowValues.add(type);
            rowValues.add(discount);
            rowValues.add(address);
            rowValues.add(bank_name);
            rowValues.add(bank_account);
            rowValues.add(tax_number);
            rowValues.add(contact_person);
            rowValues.add(contact_tel);
            rowValues.add(c_t);
            rowValues.add(u_t);

            tableValues.add(rowValues);

        }
        return tableValues;
    }
}


