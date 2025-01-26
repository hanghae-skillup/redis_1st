package com.example.redis.movie.out.persistence.jpa;

@jakarta.persistence.Table(name = "reserve", uniqueConstraints = {@jakarta.persistence.UniqueConstraint(name = "reserve_unique_id_idx", columnNames = {"screening_id", "seat_id"})})
@jakarta.persistence.Entity()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB9\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/ReservationEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "id", "", "reserveReceiptId", "", "userId", "screening", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;", "seat", "Lcom/example/redis/theater/out/persistence/jpa/SeatEntity;", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;Lcom/example/redis/theater/out/persistence/jpa/SeatEntity;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getReserveReceiptId", "()Ljava/lang/String;", "setReserveReceiptId", "(Ljava/lang/String;)V", "getScreening", "()Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;", "getSeat", "()Lcom/example/redis/theater/out/persistence/jpa/SeatEntity;", "getUserId", "setUserId", "(Ljava/lang/Long;)V", "Companion", "module-infrastructure"})
public final class ReservationEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "reserve_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(name = "reserve_receipt_id")
    @org.jetbrains.annotations.Nullable()
    private java.lang.String reserveReceiptId;
    @jakarta.persistence.Column(name = "user_id")
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long userId;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "screening_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.ScreeningEntity screening = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "seat_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.theater.out.persistence.jpa.SeatEntity seat = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.redis.movie.out.persistence.jpa.ReservationEntity.Companion Companion = null;
    
    public ReservationEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.Nullable()
    java.lang.String reserveReceiptId, @org.jetbrains.annotations.Nullable()
    java.lang.Long userId, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.ScreeningEntity screening, @org.jetbrains.annotations.NotNull()
    com.example.redis.theater.out.persistence.jpa.SeatEntity seat) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReserveReceiptId() {
        return null;
    }
    
    public final void setReserveReceiptId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getUserId() {
        return null;
    }
    
    public final void setUserId(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.movie.out.persistence.jpa.ScreeningEntity getScreening() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.theater.out.persistence.jpa.SeatEntity getSeat() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/ReservationEntity$Companion;", "", "()V", "fromDomain", "Lcom/example/redis/movie/out/persistence/jpa/ReservationEntity;", "reservation", "Lcom/example/redis/movie/Reservation;", "screening", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;", "seat", "Lcom/example/redis/theater/out/persistence/jpa/SeatEntity;", "module-infrastructure"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.out.persistence.jpa.ReservationEntity fromDomain(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.Reservation reservation, @org.jetbrains.annotations.NotNull()
        com.example.redis.movie.out.persistence.jpa.ScreeningEntity screening, @org.jetbrains.annotations.NotNull()
        com.example.redis.theater.out.persistence.jpa.SeatEntity seat) {
            return null;
        }
    }
}