package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1, customer.getId());
            pstm.setObject(2, customer.getName());
            pstm.setObject(3, customer.getAddress());
            pstm.setObject(4, customer.getTel());

            return pstm.executeUpdate() > 0;

    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET customer_name = ?, address = ?, contact = ? WHERE customer_id = ?";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1, customer.getName());
            pstm.setObject(2, customer.getAddress());
            pstm.setObject(3, customer.getTel());
            pstm.setObject(4, customer.getId());

            return pstm.executeUpdate() > 0;

    }

    public static boolean delete(String cusId) throws SQLException {
        String sql = "DELETE FROM customer WHERE customer_id = ?";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, cusId);

            return pstm.executeUpdate() > 0;
    }

    public static Customer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String cusId = resultSet.getString(1);
                String cusName = resultSet.getString(2);
                String address = resultSet.getString(3);
                int contact = Integer.parseInt(resultSet.getString(4));

                Customer customer = new Customer(cusId, cusName, address, contact);

                return customer;
            }

        return null;
    }

    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customerList = new ArrayList<>();

        while (resultSet.next()){
            String cusId = resultSet.getString(1);
            String cusName = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);

            Customer customer = new Customer(cusId, cusName, address, contact);
            customerList.add(customer);
        }

        return customerList;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT customer_id FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static String getName(String cusId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, cusId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String cusName = resultSet.getString(2);

            return cusName;
        }
        return null;
    }

    public static String getNextCustomerId() throws SQLException {
        String sql = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1" ;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String lastJobID = resultSet.getString(1);

            if (!(lastJobID == null)) {
                int index = Integer.parseInt(lastJobID.substring(1));
                index++;

                if (index < 10) {
                    return "C00" + index;
                } else if (index < 100) {
                    return "C0" + index;
                }

            }
        }
        return "C001";
    }

    public static String generateNextCustomerId() throws SQLException {
        String sql = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("C0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "C00"+id;
            }else {
                if (length < 3){
                    return "C0"+id;
                }else {
                    return "C"+id;
                }
            }
        }
        return "C001";
    }

    public static List<String> GetCustomerTel() throws SQLException {
        String sql = "SELECT contact FROM customer WHERE contact = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<String> telList = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String tel = resultSet.getString(4);
            telList.add(tel);
        }
        return telList;
    }
}
