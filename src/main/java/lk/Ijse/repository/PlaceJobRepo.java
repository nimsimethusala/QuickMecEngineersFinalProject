package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.PlaceJob;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceJobRepo {
    public static boolean placeOrder(PlaceJob placeJob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isJobSaved = JobRepo.save(placeJob.getJob());
            if (isJobSaved) {
                boolean isQtyUpdated = ItemRepo.update(placeJob.getJobList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = JobDetailRepo.save(placeJob.getJobList());
                    if (isOrderDetailSaved) {
                        boolean isSpareQtyUpdate = SpareRepo.update(placeJob.getJob());
                        if (isSpareQtyUpdate) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;

        } catch (Exception e) {
            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }
}
