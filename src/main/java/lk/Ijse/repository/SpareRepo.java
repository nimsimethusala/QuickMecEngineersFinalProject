package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Customer;
import lk.Ijse.model.Job;
import lk.Ijse.model.JobDetail;
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
        String sql = "INSERT INTO spare VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, spares.getSpareId());
        pstm.setObject(2,spares.getName());
        pstm.setObject(3, spares.getCount());
        pstm.setObject(4, spares.getPrice());
        pstm.setObject(5, spares.getSupplierId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Spares spares) throws SQLException {
        String sql = "UPDATE spare SET Name = ?, count = ?, price = ?, supplier_id = ? WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, spares.getName());
        pstm.setObject(2, spares.getCount());
        pstm.setObject(3, spares.getPrice());
        pstm.setObject(4, spares.getSupplierId());
        pstm.setObject(5, spares.getSpareId());

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
            String supplierId = resultSet.getString(5);

            Spares spares = new Spares(spareId, name, count, price, supplierId);
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

    public static boolean update(Job job) throws SQLException {
        boolean isUpdateCount = updateQty(job.getSpareId(), job.getSpareCount());

        if(!isUpdateCount) {
            return false;
        }
        return true;
    }

    private static boolean updateQty(String spareId, int spareCount) throws SQLException {
        String sql = "UPDATE spare SET count = count - ? WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, spareCount);
        pstm.setString(2, spareId);

        return pstm.executeUpdate() > 0;
    }

    public static String generateNextSpareId() throws SQLException {
        String sql = "SELECT Spare_id FROM spare ORDER BY Spare_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return splitSpareId(resultSet.getString(1));
        }
        return splitSpareId(null);
    }

    private static String splitSpareId(String string) {
        if(string != null) {
            String[] strings = string.split("SP0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "SP00"+id;
            }else {
                if (length < 3){
                    return "SP0"+id;
                }else {
                    return "SP"+id;
                }
            }
        }
        return "SP001";
    }

    public static Spares searchById(String id) throws SQLException {
        String sql = "SELECT * FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String spareId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int count = resultSet.getInt(3);
            double price = resultSet.getDouble(4);
            String supplierId = resultSet.getString(5);

            Spares spares = new Spares(spareId, name, count, price,supplierId);

            return spares;
        }
        return null;
    }
}
