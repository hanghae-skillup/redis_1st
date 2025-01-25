package com.example.redis.cmmn;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/redis/cmmn/MySQLFullTextDialect;", "Lorg/hibernate/dialect/MySQLDialect;", "()V", "initializeFunctionRegistry", "", "functionContributions", "Lorg/hibernate/boot/model/FunctionContributions;", "MatchFunction", "MatchsFunction", "module-infrastructure"})
public class MySQLFullTextDialect extends org.hibernate.dialect.MySQLDialect {
    
    public MySQLFullTextDialect() {
        super();
    }
    
    @java.lang.Override()
    public void initializeFunctionRegistry(@org.jetbrains.annotations.NotNull()
    org.hibernate.boot.model.FunctionContributions functionContributions) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0016\u00a8\u0006\u000e"}, d2 = {"Lcom/example/redis/cmmn/MySQLFullTextDialect$MatchFunction;", "Lorg/hibernate/query/sqm/function/NamedSqmFunctionDescriptor;", "()V", "render", "", "sqlAppender", "Lorg/hibernate/sql/ast/spi/SqlAppender;", "arguments", "", "Lorg/hibernate/sql/ast/tree/SqlAstNode;", "returnType", "Lorg/hibernate/query/ReturnableType;", "translator", "Lorg/hibernate/sql/ast/SqlAstTranslator;", "module-infrastructure"})
    public static final class MatchFunction extends org.hibernate.query.sqm.function.NamedSqmFunctionDescriptor {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.redis.cmmn.MySQLFullTextDialect.MatchFunction INSTANCE = null;
        
        private MatchFunction() {
            super(null, false, null, null);
        }
        
        @java.lang.Override()
        public void render(@org.jetbrains.annotations.NotNull()
        org.hibernate.sql.ast.spi.SqlAppender sqlAppender, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.hibernate.sql.ast.tree.SqlAstNode> arguments, @org.jetbrains.annotations.Nullable()
        org.hibernate.query.ReturnableType<?> returnType, @org.jetbrains.annotations.NotNull()
        org.hibernate.sql.ast.SqlAstTranslator<?> translator) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0016\u00a8\u0006\u000e"}, d2 = {"Lcom/example/redis/cmmn/MySQLFullTextDialect$MatchsFunction;", "Lorg/hibernate/query/sqm/function/NamedSqmFunctionDescriptor;", "()V", "render", "", "sqlAppender", "Lorg/hibernate/sql/ast/spi/SqlAppender;", "arguments", "", "Lorg/hibernate/sql/ast/tree/SqlAstNode;", "returnType", "Lorg/hibernate/query/ReturnableType;", "translator", "Lorg/hibernate/sql/ast/SqlAstTranslator;", "module-infrastructure"})
    public static final class MatchsFunction extends org.hibernate.query.sqm.function.NamedSqmFunctionDescriptor {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.redis.cmmn.MySQLFullTextDialect.MatchsFunction INSTANCE = null;
        
        private MatchsFunction() {
            super(null, false, null, null);
        }
        
        @java.lang.Override()
        public void render(@org.jetbrains.annotations.NotNull()
        org.hibernate.sql.ast.spi.SqlAppender sqlAppender, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.hibernate.sql.ast.tree.SqlAstNode> arguments, @org.jetbrains.annotations.Nullable()
        org.hibernate.query.ReturnableType<?> returnType, @org.jetbrains.annotations.NotNull()
        org.hibernate.sql.ast.SqlAstTranslator<?> translator) {
        }
    }
}