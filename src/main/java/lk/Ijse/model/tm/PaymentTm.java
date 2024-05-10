package lk.Ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentTm {
    private String paymentId;
    private String jobId;
    private double defectTotal;
    private double employeeTotal;
    private double spareTotal;
    private double total;
}
