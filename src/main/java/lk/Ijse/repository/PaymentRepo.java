package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    public static int getTotalDefectCost(String jobId) throws SQLException {
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

    public static int getTotalEmployeeCost(String jobId) throws SQLException {
        String sql = "SELECT cost FROM job WHERE Employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, );
    }
}