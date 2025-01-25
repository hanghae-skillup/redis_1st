package com.example.redis.cmmn;

@jakarta.persistence.MappedSuperclass()
@jakarta.persistence.EntityListeners(value = {org.springframework.data.jpa.domain.support.AuditingEntityListener.class})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0017\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\bR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/example/redis/cmmn/BaseEntity;", "", "createAt", "Ljava/time/LocalDateTime;", "createBy", "", "updateAt", "updateBy", "(Ljava/time/LocalDateTime;Ljava/lang/String;Ljava/time/LocalDateTime;Ljava/lang/String;)V", "getCreateAt", "()Ljava/time/LocalDateTime;", "getCreateBy", "()Ljava/lang/String;", "getUpdateAt", "getUpdateBy", "module-infrastructure"})
public class BaseEntity {
    @org.springframework.data.annotation.CreatedDate()
    @jakarta.persistence.Column(name = "create_at")
    @org.jetbrains.annotations.Nullable()
    private final java.time.LocalDateTime createAt = null;
    @jakarta.persistence.Column(name = "create_by")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String createBy = null;
    @org.springframework.data.annotation.LastModifiedDate()
    @jakarta.persistence.Column(name = "update_at")
    @org.jetbrains.annotations.Nullable()
    private final java.time.LocalDateTime updateAt = null;
    @jakarta.persistence.Column(name = "update_by")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String updateBy = null;
    
    public BaseEntity(@org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime createAt, @org.jetbrains.annotations.Nullable()
    java.lang.String createBy, @org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime updateAt, @org.jetbrains.annotations.Nullable()
    java.lang.String updateBy) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime getCreateAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCreateBy() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime getUpdateAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUpdateBy() {
        return null;
    }
    
    public BaseEntity() {
        super();
    }
}