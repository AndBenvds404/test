package dtos;

import db.OrdenCompra;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class OrdenCompraDto {
    private Integer id;
    private Integer clienteId;
    private String productoId;
    private BigDecimal precio;

    public static OrdenCompraDto from(OrdenCompra obj) {
        OrdenCompraDto ret = new OrdenCompraDto();

        ret.setId(obj.getId());
        ret.setClienteId(obj.getClienteId());
        ret.setProductoId(obj.getProductoId());
        ret.setPrecio(obj.getPrecio());

        return ret;
    }

}
