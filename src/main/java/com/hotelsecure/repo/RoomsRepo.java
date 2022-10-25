package com.hotelsecure.repo;

import com.hotelsecure.tables.Guests;
import com.hotelsecure.tables.GuestsInRoom;
import com.hotelsecure.tables.Rooms;
import com.twilio.twiml.video.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomsRepo extends JpaRepository<Rooms, Long> {
    @Query(value = "SELECT * FROM Rooms r WHERE r.number=:number", nativeQuery = true)
    public List<Rooms> roomByLocalId(@Param("number") Long name);

    @Query(value = "select * from rooms\n" +
            "where id not in (\n" +
            "select room from guests);", nativeQuery = true)
    public List<Rooms> getAvaileableRooms();

    @Query(value = "select *\n" +
            "from rooms \n" +
            "where id in (\n" +
            "select room from guests)", nativeQuery = true)
    public List<Rooms> getUnAvaileableRooms();

    @Query(value = "select * from guests\n" +
            "where room in (\n" +
            "select id from rooms\n" +
            "where capacity = 2);", nativeQuery = true)
    public List<Guests> getCouples();

    @Query(value = "select " +
            "(select count(*) from guests) * 100 /" +
            "(select sum(capacity) from rooms)\n", nativeQuery = true)
    public int getPresentOfCapisity();

//    @Query(value = "select r.id as room_local_id, r.capacity, r.number,g.*\n" +
//            "            from rooms r, guests g\n" +
//            "            where r.id = g.room\n" +
//            "            and r.id in (select distinct room from guests \n" +
//            "where room in (\n" +
//            "select id from rooms\n" +
//            "where capacity = 2))", nativeQuery = true)
//    public List<Object> getGeustInRoomsCouples();

}
