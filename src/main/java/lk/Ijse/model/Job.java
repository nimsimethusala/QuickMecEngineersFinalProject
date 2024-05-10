package lk.Ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    private String jobId;
    private String model;
    private Date date;
    private String customerId;
    private String defectId;
    private int itemCount;
    private String spareId;
    private int spareCount;
    private double empCost;
}
