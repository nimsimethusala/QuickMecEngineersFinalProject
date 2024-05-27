package lk.Ijse.repository;

import lk.Ijse.db.DbConnection;
import lk.Ijse.model.Customer;
import lk.Ijse.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {

    public static boolean delete(String empId) throws SQLException {
        String sql = "DELETE FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, empId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getEmpId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getAttendance());
        pstm.setObject(4,employee.getAddress());
        pstm.setObject(5, employee.getContact());
        pstm.setObject(6, employee.getSalary());
        pstm.setObject(7, employee.getCost());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET Name = ?, attendence = ?, address = ?, contact = ?, salary = ?, cost = ? WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getAttendance());
        pstm.setObject(3, employee.getAddress());
        pstm.setObject(4, employee.getContact());
        pstm.setObject(5, employee.getSalary());
        pstm.setObject(6, employee.getCost());
        pstm.setObject(7, employee.getEmpId());

        return pstm.executeUpdate() > 0;
    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<Employee> empList = new ArrayList<>();

        while (resultSet.next()){
            String empId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String attendence = resultSet.getString(3);
            String address = resultSet.getString(4);
            int contact = resultSet.getInt(5);
            double salary = resultSet.getDouble(6);
            double cost = resultSet.getDouble(7);

            Employee employee = new Employee(empId, name, attendence, contact, address, salary, cost);
            empList.add(employee);
        }

        return empList;
    }

    public static double getEmployeeCost(String empId) throws SQLException {
        String sql = "SELECT cost FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, empId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            double cost = resultSet.getDouble(1);
            return cost;
        }
        return 0.0;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT Emp_id FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static String generateNextEmployeeId() throws SQLException {
        String sql = "SELECT Emp_id FROM employee ORDER BY Emp_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return splitEmployeeId(resultSet.getString(1));
        }
        return splitEmployeeId(null);
    }

    private static String splitEmployeeId(String string) {
        if(string != null) {
            String[] strings = string.split("E0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "E00"+id;
            }else {
                if (length < 3){
                    return "E0"+id;
                }else {
                    return "E"+id;
                }
            }
        }
        return "E001";
    }

    public static String getName(String empId) throws SQLException {
        String sql = "SELECT Name FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, empId);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            String empName = resultSet.getString(1);

            return empName;
        }
        return null;
    }

    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            String employeeId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String attendence = resultSet.getString(3);
            String address = resultSet.getString(4);
            int contact = resultSet.getInt(5);
            double salary = resultSet.getDouble(6);
            double cost = resultSet.getDouble(7);

            Employee employee = new Employee(employeeId, name, attendence, contact, address, salary, cost);
            return employee;
        }

        return null;
    }

    public static int getArrivedEmpCount() throws SQLException {
        String sql = "SELECT attendence FROM employee WHERE attendence = 'Yes'";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        int count = 0;

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            count += 1;

            return count;
        }
        return 0;
    }

    public static int getAbsentEmpCount() throws SQLException {
        String sql = "SELECT attendence FROM employee WHERE attendence = 'No'";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        int count = 0;

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            count += 1;

            return count;
        }
        return 0;
    }
}
