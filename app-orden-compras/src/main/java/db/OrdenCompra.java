package db;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="orden_compra")//plural
@Data
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cliente_id")
    private Integer clienteId;
    @Column(name = "producto_id")
    private String productoId;
    private BigDecimal precio;
}

