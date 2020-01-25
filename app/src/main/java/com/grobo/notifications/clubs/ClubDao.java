package com.grobo.notifications.clubs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ClubDao {

    @Query("select * from clubs ORDER BY name ASC")
    LiveData<List<ClubItem>> loadAllClubs();

    @Query("select * from clubs where id = :id")
    ClubItem loadClubById(String id);

    @Insert(onConflict = REPLACE)
    void insertClub(ClubItem clubItem);

    @Insert(onConflict = REPLACE)
    void insertOrReplaceClub(ClubItem... clubItems);

    @Query("DELETE FROM clubs")
    void deleteAllClubs();
}
