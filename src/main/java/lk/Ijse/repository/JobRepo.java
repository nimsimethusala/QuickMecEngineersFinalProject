package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Customer;
import lk.Ijse.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRepo {
    public static boolean save(Job job) throws SQLException {
        String sql = "INSERT INTO job VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, job.getJobId());
        pstm.setString(2, job.getModel());
        pstm.setDate(3, job.getDate());
        pstm.setString(4, job.getCustomerId());
        pstm.setInt(5, job.getItemCount());
        pstm.setString(6, job.getDefectId());
        pstm.setString(7, job.getSpareId());
        pstm.setInt(8, job.getSpareCount());
        pstm.setObject(9, job.getEmpCost());

        return pstm.executeUpdate() > 0;
    }

    public static String getNextJobID() throws SQLException {
        String sql = "SELECT job_No FROM job ORDER BY job_No DESC LIMIT 1" ;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String lastJobID = resultSet.getString(1);

            if (!(lastJobID == null)) {
                int index = Integer.parseInt(lastJobID.substring(1));
                index++;

                if (index < 10) {
                    return "J00" + index;
                } else if (index < 100) {
                    return "J0" + index;
                }

            }
        }
        return "J001";
    }

    public static List<Job> getAll() throws SQLException {
        String sql = "SELECT * FROM job";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Job> jobList = new ArrayList<>();

        while (resultSet.next()){
            String jobId = resultSet.getString(1);
            String model = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            String cusId = resultSet.getString(4);
            int itemCount = resultSet.getInt(5);
            String defectId = resultSet.getString(6);
            String spareId = resultSet.getString(7);
            int spareCount = resultSet.getInt(8);
            double empCost = resultSet.getDouble(9);

            Job job = new Job(jobId, model, date, cusId, defectId, itemCount, spareId, spareCount, empCost);
            jobList.add(job);
        }

        return jobList;
    }

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

    public static String getEmployeeId(String jobId) throws SQLException {
        String sql = "SELECT Emp_id FROM job WHERE job_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, jobId);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            String empId = resultSet.getString(9);

            return empId;
        }
        return null;
    }

    public static String getSpareId(String jobId) throws SQLException {
        String sql = "SELECT Spare_id FROM job WHERE job_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, jobId);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            String spareId = resultSet.getString(7);

            return spareId;
        }
        return null;
    }
}
