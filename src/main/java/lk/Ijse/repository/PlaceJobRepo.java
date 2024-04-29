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
            boolean isOrderSaved = JobRepo.save(placeJob.getJob());
            if (isOrderSaved) {
                boolean isQtyUpdated = ItemRepo.update(placeJob.getJobList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = JobDetailRepo.save(placeJob.getJobList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
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
