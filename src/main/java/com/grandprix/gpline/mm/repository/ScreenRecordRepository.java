package com.grandprix.gpline.mm.repository;

import com.grandprix.gpline.mm.model.ScreenRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRecordRepository extends JpaRepository<ScreenRecord, Integer> {

    @Query("SELECT s FROM ScreenRecord s "
            + " WHERE s.syncId LIKE concat('%', :login, '%') AND ("
            + " (s.recordBegin <= :startDate AND s.recordEnd >= :startDate AND"
            + "  s.recordBegin <= :endDate AND s.recordEnd >= :endDate) OR"
            + " ((s.recordBegin <= :startDate AND s.recordEnd >= :startDate) OR"
            + "  (s.recordBegin <= :endDate AND s.recordEnd >= :endDate) OR"
            + "  ( s.recordBegin >= :startDate AND s.recordEnd <= :endDate))) "
            + " ORDER BY s.recordBegin"
    )
    public List<ScreenRecord> getScreenRecords(
            @Param("login") String login,
            @Param("startDate") Long startDate,
            @Param("endDate") Long endDate
    );
}
