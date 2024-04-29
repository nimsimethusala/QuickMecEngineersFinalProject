package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
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
        while (resultSet.next()){
            String itemName = resultSet.getString(2);

            return itemName;
        }

        return null;
    }

    public static boolean update(List<JobDetail> jobList) throws SQLException {
        for (JobDetail list : jobList) {
            boolean isUpdateCount = updateQty(list.getItemId(), list.getItemCount());

            if(!isUpdateCount) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String itemId, int itemCount) throws SQLException {
        String sql = "UPDATE item SET Item_count = Item_count - ? WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, itemCount);
        pstm.setString(2, itemId);

        return pstm.executeUpdate() > 0;
    }
}
