package com.example.myapplication;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Asegúrate de reemplazar 'Event.class' y 'EventDao' con tus clases reales.
@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "your_database_name.db")
                            .fallbackToDestructiveMigration()
                            // Considera agregar .addCallback(roomCallback) si necesitas poblar la base de datos
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Opcional: Si quieres poblar la base de datos la primera vez que se crea, puedes usar un RoomDatabase.Callback
    // private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
    //     @Override
    //     public void onCreate(@NonNull SupportSQLiteDatabase db) {
    //         super.onCreate(db);
    //         // Poblar la base de datos en un hilo aparte
    //         new Thread(() -> {
    //             EventDao dao = INSTANCE.eventDao();
    //             dao.insert(new Event(...)); // Añade tus datos iniciales aquí
    //         }).start();
    //     }
    // };
}
