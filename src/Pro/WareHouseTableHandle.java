package Pro;

import java.util.Iterator;
import java.util.Vector;

public class WareHouseTableHandle {


    public static Vector<Vector<String>> getValues() {
        Vector<WareHouse> wareHouseList = WareHouseDao.getInstance().getWareHouseList();
        Vector<Vector<String>> tableValues = convertTable(wareHouseList);
        return tableValues;
    }


    public static Vector<Vector<String>> convertTable(Vector<WareHouse> wareHouseList) {
        Vector<Vector<String>> tableValues = new Vector<>();

        Iterator it = wareHouseList.iterator();
        while (it.hasNext()) {
            WareHouse wareHouse = (WareHouse) it.next();
            String id = String.valueOf(wareHouse.getWarehouseid());
            String name = wareHouse.getWarename();
            String address = wareHouse.getAddress();
            String c_t = String.valueOf(wareHouse.getCreatetime());
            String u_t = String.valueOf(wareHouse.getUpdatetime());

            Vector<String> rowValues = new Vector<>();
            rowValues.add(id);
            rowValues.add(name);
            rowValues.add(address);
            rowValues.add(c_t);
            rowValues.add(u_t);

            tableValues.add(rowValues);

        }
        return tableValues;
    }
}
