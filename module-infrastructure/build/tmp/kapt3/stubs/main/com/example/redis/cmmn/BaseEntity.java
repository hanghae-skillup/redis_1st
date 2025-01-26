package com.example.redis.cmmn;

@jakarta.persistence.MappedSuperclass()
@jakarta.persistence.EntityListeners(value = {org.springframework.data.jpa.domain.support.AuditingEntityListener.class})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0017\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\bR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010\u00a8\u0006\u0015"}, d2 = {"Lcom/example/redis/cmmn/BaseEntity;", "", "createAt", "Ljava/time/LocalDateTime;", "createBy", "", "updateAt", "updateBy", "(Ljava/time/LocalDateTime;Ljava/lang/String;Ljava/time/LocalDateTime;Ljava/lang/String;)V", "getCreateAt", "()Ljava/time/LocalDateTime;", "setCreateAt", "(Ljava/time/LocalDateTime;)V", "getCreateBy", "()Ljava/lang/String;", "setCreateBy", "(Ljava/lang/String;)V", "getUpdateAt", "setUpdateAt", "getUpdateBy", "setUpdateBy", "module-infrastructure"})
public class BaseEntity {
    @org.springframework.data.annotation.CreatedDate()
    @jakarta.persistence.Column(name = "create_at")
    @org.jetbrains.annotations.Nullable()
    private java.time.LocalDateTime createAt;
    @jakarta.persistence.Column(name = "create_by")
    @org.jetbrains.annotations.Nullable()
    private java.lang.String createBy;
    @org.springframework.data.annotation.LastModifiedDate()
    @jakarta.persistence.Column(name = "update_at")
    @org.jetbrains.annotations.Nullable()
    private java.time.LocalDateTime updateAt;
    @jakarta.persistence.Column(name = "update_by")
    @org.jetbrains.annotations.Nullable()
    private java.lang.String updateBy;
    
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
    
    public final void setCreateAt(@org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCreateBy() {
        return null;
    }
    
    public final void setCreateBy(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime getUpdateAt() {
        return null;
    }
    
    public final void setUpdateAt(@org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUpdateBy() {
        return null;
    }
    
    public final void setUpdateBy(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public BaseEntity() {
        super();
    }
}