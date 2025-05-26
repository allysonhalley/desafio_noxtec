package com.noxtec.desafio_noxtec.specification;

import com.noxtec.desafio_noxtec.model.Contato;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class ContatoSpecifications {
    public static Specification<Contato> filtroDinamico(String nome, String email, String celular) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nome != null && !nome.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }
            if (email != null && !email.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }
            if (celular != null && !celular.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("celular")), "%" + celular.toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
