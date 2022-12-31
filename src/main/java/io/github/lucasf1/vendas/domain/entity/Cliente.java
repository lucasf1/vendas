package io.github.lucasf1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {
    
    private Integer id;
    private String nome;

    public Cliente(String nome){
        this.nome = nome;
    }
}
