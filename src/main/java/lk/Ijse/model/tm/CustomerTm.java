package lk.Ijse.model.tm;

import lk.Ijse.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private int tel;
}
