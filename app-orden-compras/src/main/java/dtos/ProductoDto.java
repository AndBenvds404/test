package dtos;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ProductoDto {
    private Integer id;
    private String nombre;
    private BigDecimal precio;

}
