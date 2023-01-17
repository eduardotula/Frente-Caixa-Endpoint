package com.loja65.inbound.adapter.dto.consulta;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionariosResponseDto {

    private List<String> funcionarios;
}
