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
public class Member extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) private String name;
    @Column(nullable = false, length = 150, unique = true) private String email;


    protected Member() {}

    private Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static Member of(String name, String email) {
        return new Member(name, email);
    }

}
