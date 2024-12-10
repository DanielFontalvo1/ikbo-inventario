package com.gestion.ikbo_inventario.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
    @SequenceGenerator(name = "seq_producto", sequenceName = "productos_seq", allocationSize = 1)
    private int idProducto;

    @Column(name = "nombre_producto", length = 100)
    private String nombreProducto;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name="precio_unitario")
    private Float precioUnitario;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "estado", nullable = true)
    private Estado estado;

}
