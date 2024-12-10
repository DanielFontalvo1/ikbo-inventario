package com.gestion.ikbo_inventario.models.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private String respuesta;
    private int valor;

}
