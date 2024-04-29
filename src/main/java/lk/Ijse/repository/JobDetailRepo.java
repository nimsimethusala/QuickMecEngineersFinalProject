package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.JobDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JobDetailRepo {
    public static boolean save(List<JobDetail> jobList) throws SQLException {
        for (JobDetail list : jobList) {
            boolean isSaved = save(list);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(JobDetail list) throws SQLException {
        String sql = "INSERT INTO job_details VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, list.getItemId());
        pstm.setInt(2, list.getItemCount());
        pstm.setString(3, list.getModel());
        pstm.setString(4, list.getJobId());

        return pstm.executeUpdate() > 0;
    }
}
