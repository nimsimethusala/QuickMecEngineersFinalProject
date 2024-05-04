package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Customer;
import lk.Ijse.model.Spares;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpareRepo {

    public static boolean delete(String spareId) throws SQLException {
        String sql = "DELETE FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, spareId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Spares spares) throws SQLException {
        String sql = "INSERT INTO spare VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, spares.getSpareId());
        pstm.setObject(2,spares.getName());
        pstm.setObject(3, spares.getCount());
        pstm.setObject(4, spares.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Spares spares) throws SQLException {
        String sql = "UPDATE spare SET Spare_id = ?, Name = ?, count = ?, price = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, spares.getSpareId());
        pstm.setObject(2, spares.getName());
        pstm.setObject(3, spares.getCount());
        pstm.setObject(4, spares.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public static List<Spares> getAll() throws SQLException {
        String sql = "SELECT * FROM spare";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Spares> spareList = new ArrayList<>();

        while (resultSet.next()){
            String spareId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int count = resultSet.getInt(3);
            double price = resultSet.getDouble(4);

            Spares spares = new Spares(spareId, name, count, price);
            spareList.add(spares);
        }

        return spareList;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT Spare_id FROM spare";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static String getName(String spareId) throws SQLException {
        String sql = "SELECT Name FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, spareId);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            String spareName = resultSet.getString(1);

            return spareName;
        }

        return null;
    }
}
