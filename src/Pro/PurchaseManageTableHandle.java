package Pro;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class PurchaseManageTableHandle {


    public static Vector<Vector<String>> getValues() {
        Vector<PurchaseDetail> purchaseDetails = PurchaseDetailDao.getInstance().getPurchaseDetailList();
        Iterator it = purchaseDetails.iterator();
        Vector<PurchaseManage> purchaseManages = new Vector<>();
        while (it.hasNext()) {
            PurchaseDetail purchaseDetail = (PurchaseDetail) it.next();
            int bookid = purchaseDetail.getBookid();
            Book book = BooksDao.getInstance().selectBookById(bookid);
            String bookname = book.getBook_name();
            String barcode = book.getBarcode();
            int quantity = purchaseDetail.getQuantity();
            int partnerid = purchaseDetail.getPartnerid();
            String partnername = PartnerDao.getInstance().getNameById(partnerid);
            int warehouseid = purchaseDetail.getWarehouseid();
            String warehousename = WareHouseDao.getInstance().getNameById(warehouseid);
            int billmainid = purchaseDetail.getBillmainid();
            String status = BillMainDao.getInstance().getStatusById(billmainid);
            Float costamount = purchaseDetail.getCostamount();
            Float discountamount = purchaseDetail.getDiscountamount();
            Date date = BillMainDao.getInstance().getPosttimeById(billmainid);
            PurchaseManage purchaseManage = new PurchaseManage(bookname, barcode, quantity, partnername, warehousename, status, costamount, discountamount, date);
            purchaseManages.add(purchaseManage);
        }
        Vector<Vector<String>> tableValues = convertTable(purchaseManages);
        return tableValues;
    }


    public static Vector<Vector<String>> convertTable(Vector<PurchaseManage> PurchaseManageList) {
        Vector<Vector<String>> tableValues = new Vector<>();

        Iterator it = PurchaseManageList.iterator();
        while (it.hasNext()) {
            PurchaseManage PurchaseManage = (PurchaseManage) it.next();
            String bookname = PurchaseManage.getBookname();
            String barcode = PurchaseManage.getBarcode();
            String quantity = String.valueOf(PurchaseManage.getQuantity());
            String partnername = PurchaseManage.getPartner();
            String warehousename = PurchaseManage.getWarehouse();
            String status = PurchaseManage.getStatus();
            String amount = String.valueOf(PurchaseManage.getAmount());
            String discountamount = String.valueOf(PurchaseManage.getDiscountamount());

            Vector<String> rowValues = new Vector<>();
            rowValues.add(bookname);
            rowValues.add(barcode);
            rowValues.add(quantity);
            rowValues.add(partnername);
            rowValues.add(warehousename);
            rowValues.add(status);
            rowValues.add(amount);
            rowValues.add(discountamount);
            tableValues.add(rowValues);

        }
        return tableValues;
    }
}
