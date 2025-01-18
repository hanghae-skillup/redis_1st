package com.example.redis.theater.out.persistence.jpa;

@jakarta.persistence.Table(name = "seat")
@jakarta.persistence.Entity()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/example/redis/theater/out/persistence/jpa/SeatEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "row", "", "col", "theater", "Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;", "id", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;Ljava/lang/Long;)V", "getCol", "()Ljava/lang/String;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRow", "getTheater", "()Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;", "module-infrastructure"})
public final class SeatEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.Column(name = "seat_row")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String row = null;
    @jakarta.persistence.Column(name = "seat_col")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String col = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "theater_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.theater.out.persistence.jpa.TheaterEntity theater = null;
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "seat_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    
    public SeatEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String row, @org.jetbrains.annotations.NotNull()
    java.lang.String col, @org.jetbrains.annotations.NotNull()
    com.example.redis.theater.out.persistence.jpa.TheaterEntity theater, @org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCol() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.theater.out.persistence.jpa.TheaterEntity getTheater() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
}