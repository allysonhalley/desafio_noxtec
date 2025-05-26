package com.noxtec.desafio_noxtec.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity(name = "contato")
@Table(name = "contato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contato{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "contato_nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "contato_email", length = 255)
    private String email;

    @Column(name = "contato_celular", nullable = false,length = 11, unique = true)
    private String celular;

    @Column(name = "contato_telefone", length = 10)
    private String telefone;

    @Column(name = "contato_sn_favorito", length = 1)
    private Character favorito = 'N';

    @Column(name = "contato_sn_ativo", length = 1)
    private Character ativo = 'S';

    @Column(name = "contato_dh_cad", updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCadastro;
}
