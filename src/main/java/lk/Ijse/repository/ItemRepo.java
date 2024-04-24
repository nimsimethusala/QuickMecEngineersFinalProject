package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemRepo {
    public static boolean delete(String itemId) throws SQLException {
        String sql = "DELETE FROM item WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, itemId);

        return pstm.executeUpdate() > 0;
    }

    /*public static boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO item VALUES(item_No = ?, Name = ?, Item_count = ?, job_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, item.getItemID());
        pstm.setObject(2, item.getItemName());
        pstm.setObject(3, item.getItemCount());

    }*/
}
