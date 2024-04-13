package lk.Ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeTm {
    private String empId;
    private String name;
    private double attendance;
    private int contact;
    private String address;
    private double salary;
}
