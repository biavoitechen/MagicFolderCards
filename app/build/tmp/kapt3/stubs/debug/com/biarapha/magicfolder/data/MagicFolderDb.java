package com.biarapha.magicfolder.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/biarapha/magicfolder/data/MagicFolderDb;", "Landroidx/room/RoomDatabase;", "()V", "cardDao", "Lcom/biarapha/magicfolder/data/CardDao;", "deckDao", "Lcom/biarapha/magicfolder/data/DeckDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.biarapha.magicfolder.data.Card.class, com.biarapha.magicfolder.data.Deck.class}, version = 2, exportSchema = false)
public abstract class MagicFolderDb extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.biarapha.magicfolder.data.MagicFolderDb INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.biarapha.magicfolder.data.MagicFolderDb.Companion Companion = null;
    
    public MagicFolderDb() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.biarapha.magicfolder.data.CardDao cardDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.biarapha.magicfolder.data.DeckDao deckDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/biarapha/magicfolder/data/MagicFolderDb$Companion;", "", "()V", "INSTANCE", "Lcom/biarapha/magicfolder/data/MagicFolderDb;", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "get", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.biarapha.magicfolder.data.MagicFolderDb get(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}