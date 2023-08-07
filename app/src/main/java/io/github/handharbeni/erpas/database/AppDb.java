package io.github.handharbeni.erpas.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import io.github.handharbeni.erpas.database.helpers.DataConverter;
import io.github.handharbeni.erpas.database.helpers.DateConverter;
import io.github.handharbeni.erpas.database.interfaces.InterfaceParked;
import io.github.handharbeni.erpas.database.migrations.Migrations;
import io.github.handharbeni.erpas.database.models.Parked;
import io.github.handharbeni.erpas.utils.Constant;

@TypeConverters({
        DateConverter.class,
        DataConverter.class
})
@Database(
        entities = {
                Parked.class
        },
        version = Constant.DB_VERSION,
        exportSchema = Constant.DB_EXPORT
)
public abstract class AppDb extends RoomDatabase {
    public abstract InterfaceParked parked();

    private static volatile AppDb INSTANCE;

    public static AppDb getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDb.class,
                                    Constant.DB_NAME
                            )
                            .addMigrations(
                                    Migrations.MIGRATION_0_1
                            )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}