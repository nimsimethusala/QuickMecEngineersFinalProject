package lk.Ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobTm {
    private String jobId;
    private Date date;
    private String vehicleModel;
    private String customerName;
    private String itemName;
    private int itemCount;
    private String spareName;
    private String defectDescription;
}
