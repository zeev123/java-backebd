package com.hotelsecure.repo;

import com.hotelsecure.tables.Guests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestsRepo extends JpaRepository<Guests, Long> {

    @Query(value = "select * from guests\n" +
            "where room  in (\n" +
            "select id from rooms where \n" +
            "capacity > 2)", nativeQuery = true)
    public List<Guests> familiesRooms();

    @Query(value = "select * from guests\n" +
            "where room in (\n" +
            "select id from rooms\n" +
            "where capacity = 2)", nativeQuery = true)
    public List<Guests> GetCouples();

    @Query(value = "select * from guests " +
            "where room  =:room", nativeQuery = true)
    public List<Guests> getGuestsByRoom(@Param("room") Long room);


    @Query(value = "delete from guests " +
            "where room  =:room", nativeQuery = true)
    public void deleteGuestsByRoom(@Param("room") Long room);
}