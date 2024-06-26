package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Customer;
import lk.Ijse.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean delete(String supId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(supplier_id = ?, name = ?, location = ?, contact = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getContact());
        pstm.setObject(4, supplier.getLocation());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET name = ?, location = ?, contact = ? WHERE supplier_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getContact());
        pstm.setObject(4, supplier.getLocation());

        return pstm.executeUpdate() > 0;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()){
            String supId = resultSet.getString(1);
            String supName = resultSet.getString(2);
            String location = resultSet.getString(3);
            int contact = resultSet.getInt(4);

            Supplier supplier = new Supplier(supId, supName, contact, location);
            supplierList.add(supplier);
        }

        return supplierList;
    }

    public static String generateNextSupplierId() throws SQLException {
        String sql = "SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return splitSupplierId(resultSet.getString(1));
        }
        return splitSupplierId(null);
    }

    private static String splitSupplierId(String string) {
        if(string != null) {
            String[] strings = string.split("S0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "S00"+id;
            }else {
                if (length < 3){
                    return "S0"+id;
                }else {
                    return "S"+id;
                }
            }
        }
        return "S001";
    }

    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplier_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String supName = resultSet.getString(2);
            String location = resultSet.getString(3);
            int contact = resultSet.getInt(4);

            Supplier supplier = new Supplier(supId, supName, contact, location);

            return supplier;
        }
        return null;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT supplier_id FROM supplier";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
