package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Defect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefectRepo {
    public static boolean delete(String defectId) throws SQLException {
        String sql = "DELETE FROM defect WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, defectId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Defect defect) throws SQLException {
        String sql = "INSERT INTO defect VALUES(?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, defect.getDefectId());
        pstm.setObject(2, defect.getDescription());
        pstm.setObject(3, defect.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(String defectId, String desc, double price) throws SQLException {
        String sql = "UPDATE defect SET defect_id = ?, description = ?, price = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, defectId);
        pstm.setObject(2, desc);
        pstm.setObject(3, price);

        return pstm.executeUpdate() > 0;

    }

    public static List<Defect> getAll() throws SQLException {
        String sql = "SELECT * FROM defect";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<Defect> defectList = new ArrayList<>();

        while (resultSet.next()){
            String defectId = resultSet.getString(1);
            String desc = resultSet.getString(2);
            double price = resultSet.getDouble(3);

            Defect defect = new Defect(defectId, desc, price);
            defectList.add(defect);
        }

        return defectList;
    }

    public static String getDescription(String defectId) throws SQLException {
        String sql = "SELECT description FROM defect WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, defectId);

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            String desc = resultSet.getString(1);

            return desc;
        }

        return null;
    }



    public static List<String> getId() throws SQLException {
        String sql = "SELECT defect_id FROM defect";

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
