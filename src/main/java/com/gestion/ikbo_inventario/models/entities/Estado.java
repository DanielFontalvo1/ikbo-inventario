package com.gestion.ikbo_inventario.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="estados")
public class Estado {

    @Id
    @Column(name ="id_estado")
    private int idEstado;

    @Column(name = "descripcion", length = 100)
    private String descripcion;
}
