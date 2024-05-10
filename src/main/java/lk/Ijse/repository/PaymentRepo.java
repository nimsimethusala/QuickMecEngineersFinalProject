package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.tm.PaymentTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PaymentRepo {

    public static List<String> getId() throws SQLException {
        String sql = "SELECT job_No FROM job";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static String getNextPaymentID() throws SQLException {
        String sql = "SELECT job_No FROM job ORDER BY job_No DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String lastJobID = resultSet.getString(1);

            if (!(lastJobID == null)) {
                int index = Integer.parseInt(lastJobID.substring(1));
                index++;

                if (index < 10) {
                    return "P00" + index;
                } else if (index < 100) {
                    return "P0" + index;
                }

            }
        }
        return "P001";
    }

    public static double getTotalDefectCost(String jobId) throws SQLException {
        String sql = "SELECT price FROM defect WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, jobId);
        ResultSet resultSet = pstm.executeQuery();

        int price = 0;
        while (resultSet.next()){
            List<Integer> priceList = Collections.singletonList(resultSet.getInt(3));

            for (int i = 0; i < priceList.size(); i++){
                price += i;
            }
            return price;
        }
        return 0;
    }

    public static double getTotalEmployeeCost(String jobId) throws SQLException {
        String sql = "SELECT cost FROM employee WHERE Employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        String empId = JobRepo.getEmployeeId(jobId);
        pstm.setString(1, empId);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            double empCost = resultSet.getDouble(7);

            return empCost;
        }
        return 0;
    }

    public static double getTotalSpareCost(String jobId) throws SQLException {
        String sql = "SELECT cost FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        String SpareId = JobRepo.getSpareId(jobId);
        pstm.setString(1, SpareId);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            double SpareCost = resultSet.getDouble(7);

            return SpareCost;
        }
        return 0;
    }

    public static boolean save(PaymentTm paymentTm) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?, ?, ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, paymentTm.getPaymentId());
        pstm.setObject(2, paymentTm.getJobId());
        pstm.setObject(3, paymentTm.getDefectTotal());
        pstm.setObject(4, paymentTm.getEmployeeTotal());
        pstm.setObject(5, paymentTm.getSpareTotal());
        pstm.setObject(6, paymentTm.getTotal());

        return pstm.executeUpdate() > 0;
    }

    public static String generateNextPaymentId() throws SQLException {
        String sql = "SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1";

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
            String[] strings = string.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "P00"+id;
            }else {
                if (length < 3){
                    return "P0"+id;
                }else {
                    return "P"+id;
                }
            }
        }
        return "P001";
    }
}