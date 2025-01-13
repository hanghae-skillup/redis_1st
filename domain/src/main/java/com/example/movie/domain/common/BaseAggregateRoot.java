package com.example.movie.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.Objects;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Accessors(fluent = true)
@MappedSuperclass
public abstract class BaseAggregateRoot<A extends BaseAggregateRoot<A>> extends AbstractAggregateRoot<A> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    @Column(columnDefinition = "INTEGER UNSIGNED")
    protected Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("생성 일시")
    protected Instant createdAt;

    @LastModifiedDate
    @Comment("수정 일시")
    protected Instant updatedAt;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var that = (BaseAggregateRoot<?>) o;

        if (this.id == null || that.id == null) {
            return false;
        }

        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

}
