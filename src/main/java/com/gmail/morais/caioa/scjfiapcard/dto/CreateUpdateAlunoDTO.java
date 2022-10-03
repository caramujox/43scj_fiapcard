package com.gmail.morais.caioa.scjfiapcard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateAlunoDTO {
    private String nome;
    private String cpf;
    private String rm;
}
