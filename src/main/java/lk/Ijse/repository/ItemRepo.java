package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Customer;
import lk.Ijse.model.Item;
import lk.Ijse.model.JobDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    public static boolean delete(String itemId) throws SQLException {
        String sql = "DELETE FROM item WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, itemId);

        return pstm.executeUpdate() > 0;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT item_No FROM item";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static String getName(String itemId) throws SQLException {
        String sql = "SELECT Name FROM item WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, itemId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String itemName = resultSet.getString(1);

            return itemName;
        }

        return null;
    }

    public static boolean update(List<JobDetail> jobList) throws SQLException {
        for (JobDetail list : jobList) {
            boolean isUpdateCount = updateQty(list.getItemId(), list.getItemCount(), list.getSpareCount());

            if(!isUpdateCount) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String itemId, int itemCount, int spareCount) throws SQLException {

        System.out.println(itemId);
        System.out.println(itemCount);
        System.out.println(spareCount);

        String sql = "UPDATE item SET Item_count = Item_count - ? WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, itemCount);
        pstm.setString(2, itemId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO item VALUES(?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, item.getItemID());
        pstm.setObject(2, item.getItemName());
        pstm.setObject(3, item.getDefectId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateItem(Item item) throws SQLException {
        String sql = "UPDATE customer SET Name = ?, defect_id = ? WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, item.getItemID());
        pstm.setObject(2, item.getItemName());
        pstm.setObject(3, item.getDefectId());

        return pstm.executeUpdate() > 0;
    }

    public static List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM item";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Item> itemList = new ArrayList<>();

        while (resultSet.next()){
            String itemId = resultSet.getString(1);
            String itemName = resultSet.getString(2);
            String defectId = resultSet.getString(3);

            Item item = new  Item(itemId,itemName,defectId);
            itemList.add(item);
        }

        return itemList;
    }

    public static String generateNextItemId() throws SQLException {
        String sql = "SELECT item_No FROM item ORDER BY item_No DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return splitItemId(resultSet.getString(1));
        }
        return splitItemId(null);
    }

    private static String splitItemId(String string) {
        if(string != null) {
            String[] strings = string.split("I0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "I00"+id;
            }else {
                if (length < 3){
                    return "I0"+id;
                }else {
                    return "I"+id;
                }
            }
        }
        return "I001";
    }
}
