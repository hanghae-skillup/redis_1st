package com.example.domain.model.entity;

import com.example.domain.model.base.AuditingFields;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Theater extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String name;


    protected Theater() {}

    private Theater(String name) {
        this.name = name;
    }

    public static Theater of(String name) {
        return new Theater(name);
    }

}
