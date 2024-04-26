package lk.Ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Spares {
    private String spareId;
    private String name;
    private String type;
    private int count;
    private double price;

}
