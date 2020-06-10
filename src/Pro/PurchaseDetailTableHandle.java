package Pro;

import java.util.Iterator;
import java.util.Vector;

public class PurchaseDetailTableHandle {


    public static Vector<Vector<String>> getValues() {
        Vector<PurchaseDetail> purchaseDetailList = PurchaseDetailDao.getInstance().getPurchaseDetailList();
        Vector<Vector<String>> tableValues = convertTable(purchaseDetailList);
        return tableValues;
    }

    public static Vector<Vector<String>> convertTable(Vector<PurchaseDetail> PurchaseDetailList) {
        Vector<Vector<String>> tableValues = new Vector<>();
        Iterator it = PurchaseDetailList.iterator();
        while (it.hasNext()) {
            PurchaseDetail PurchaseDetail = (PurchaseDetail) it.next();
            String id = String.valueOf(PurchaseDetail.getNid());
            String billmainid = String.valueOf(PurchaseDetail.getBillmainid());
            String partnerid = String.valueOf(PurchaseDetail.getPartnerid());
            String warehouseid = String.valueOf(PurchaseDetail.getWarehouseid());
            String bookid = String.valueOf(PurchaseDetail.getBookid());
            String quantity = String.valueOf(PurchaseDetail.getQuantity());
            String sellprice = String.valueOf(PurchaseDetail.getSellprice());
            String costprice = String.valueOf(PurchaseDetail.getCostprice());
            String discountprice = String.valueOf(PurchaseDetail.getCostprice());
            String sellamount = String.valueOf(PurchaseDetail.getSellamount());
            String costamount = String.valueOf(PurchaseDetail.getCostamount());
            String discountamount = String.valueOf(PurchaseDetail.getDiscountamount());


            Vector<String> rowValues = new Vector<>();
            rowValues.add(id);
            rowValues.add(billmainid);
            rowValues.add(partnerid);
            rowValues.add(warehouseid);
            rowValues.add(bookid);
            rowValues.add(quantity);
            rowValues.add(sellprice);
            rowValues.add(costprice);
            rowValues.add(discountprice);
            rowValues.add(sellamount);
            rowValues.add(costamount);
            rowValues.add(discountamount);

            tableValues.add(rowValues);

        }
        return tableValues;
    }

}
