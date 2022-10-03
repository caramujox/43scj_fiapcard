package com.gmail.morais.caioa.scjfiapcard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransacaoDTO {
    private Long id;
    private Long idAluno;
    private Double valor;
}
