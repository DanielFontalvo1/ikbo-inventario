package com.gestion.ikbo_inventario.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="productos_inventario")
public class ProductoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto_inventario")
    @SequenceGenerator(name = "seq_producto_inventario", sequenceName = "productos_inventario_seq", allocationSize = 1)
    private int consecutivo;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name="fecha_caduca")
    @Temporal(TemporalType.DATE)
    private Date fechaCaduca;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "estado", nullable = true)
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "producto", nullable = false)
    private Producto producto;
}
