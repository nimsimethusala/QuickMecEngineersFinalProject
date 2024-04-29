package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobRepo {

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT job_No FROM job ORDER BY job_No DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            String jobId = resultSet.getString(1);
            return jobId;
        }

        return null;
    }

    public static boolean save(Job job) throws SQLException {
        String sql = "INSERT INTO job VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, job.getJobId());
        pstm.setString(2, job.getModel());
        pstm.setDate(3, job.getDate());
        pstm.setString(4, job.getCustomerId());
        pstm.setString(5, job.getItemId());
        pstm.setInt(6, job.getItemCount());
        pstm.setString(7, job.getDefectId());
        pstm.setString(8, job.getSpareId());

        return pstm.executeUpdate() > 0;
    }
}
