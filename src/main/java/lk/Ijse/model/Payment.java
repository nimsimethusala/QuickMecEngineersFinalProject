package lk.Ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private String paymentId;
    private String jobId;
    private double defectTotal;
    private double empTotal;
    private double spareTotal;
    private double totalCost;
}
