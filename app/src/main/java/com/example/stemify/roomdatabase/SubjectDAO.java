package com.example.stemify.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.stemify.ui.moduleA.Subject;

import java.util.List;

@Dao
public interface SubjectDAO {
    @Query("SELECT * FROM subjects")
    List<Subject> getAll();

    @Insert
    void insertSubject(Subject subject);

    @Query("DELETE FROM subjects")
    void deleteAll();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

//    @Delete
//    void delete(User user);
}
