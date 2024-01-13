package com.example.stemify.roomdatabase;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.stemify.ui.moduleA.Subject;
import com.google.firebase.database.core.Tag;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Subject.class}, version = 1)
public abstract class ModADatabase extends RoomDatabase {
    private static volatile ModADatabase INSTANCE;
    public abstract SubjectDAO subjectDAO();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ModADatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ModADatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ModADatabase.class, "note_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Populates database with data on startup
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more notes, just add them.
                SubjectDAO subjectDAO = INSTANCE.subjectDAO();
                subjectDAO.deleteAll();

                // insert a default instance
                subjectDAO.insertSubject(new Subject("Math"));
                subjectDAO.insertSubject(new Subject("Science"));

                Log.e(, "Callback executed!");
            });
        }
    };
}
