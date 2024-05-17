package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.PlaceJob;
import lk.Ijse.model.Spares;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceJobRepo {
    public static boolean placeOrder(PlaceJob placeJob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isJobSaved = JobRepo.save(placeJob.getJob());
            System.out.println("1 "+ isJobSaved);
            if (isJobSaved) {
                boolean isQtyUpdated = ItemRepo.update(placeJob.getJobList());
                System.out.println("2 "+ isQtyUpdated);
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = JobDetailRepo.save(placeJob.getJobList());
                    System.out.println("3 "+ isOrderDetailSaved);
                    if (isOrderDetailSaved) {
                        boolean isSpareQtyUpdate = SpareRepo.update(placeJob.getJob());
                        System.out.println("4 "+ isSpareQtyUpdate);
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
