package com.grandprix.gpline.mm.repository;

import com.grandprix.gpline.mm.model.Request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
//    @Query("SELECT r FROM Request r WHERE r.channelId = ?1")
//    public List<Request> findByChannel(Integer channelId);
    
//    @Query("SELECT r FROM Request r WHERE "
//            + "(:channelId IS NULL OR r.channelId = :channelId) "
//            + "AND (:filialId IS NULL OR r.filialId = :filialId) "
//            + "AND (:operatorLogin IS NULL OR r.id "
//            + "  IN (SELECT c.request.id FROM Contact c WHERE c.operatorLogin = :operatorLogin)) "
//            + "AND (:closeStatus IS NULL OR r.requestStatus = :closeStatus) "
//            + "AND (:regDateStart IS NULL OR r.registrationDate >= :regDateStart) "
//            + "AND (:regDateEnd IS NULL OR r.registrationDate <= :regDateEnd) "
//            + "AND (:messageText IS NULL OR r.id "
//            + "  IN (SELECT c.request.id FROM Contact c WHERE c.id "
//            + "    IN (SELECT m.contactId FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(:messageText))))"
//            //+ "    IN (SELECT m.contact.id FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(concat('%', :messageText, '%')))))"
//    )

//    public List<Request> find(
//            @Param("channelId") Integer channelId,
//            @Param("filialId") Long filialId,
//            @Param("operatorLogin") String operatorLogin,
//            @Param("closeStatus") Integer closeStatus,
//            @Param("regDateStart") Long regDateStart,
//            @Param("regDateEnd") Long regDateEnd,
//            @Param("messageText") String messageText
//    );
    
//    @Query("SELECT COUNT(r) FROM Request r WHERE "
//            + "(:channelId IS NULL OR r.channelId = :channelId) "
//            + "AND (:filialId IS NULL OR r.filialId = :filialId) "
//            + "AND (:operatorLogin IS NULL OR r.id "
//            + "  IN (SELECT c.request.id FROM Contact c WHERE c.operatorLogin = :operatorLogin)) "
//            + "AND (:closeStatus IS NULL OR r.requestStatus = :closeStatus) "
//            + "AND (:regDateStart IS NULL OR r.registrationDate >= :regDateStart) "
//            + "AND (:regDateEnd IS NULL OR r.registrationDate <= :regDateEnd) "
//            + "AND (:messageText IS NULL OR r.id "
//            + "  IN (SELECT c.request.id FROM Contact c WHERE c.id "
//            + "    IN (SELECT m.contactId FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(:messageText))))"
//            //+ "    IN (SELECT m.contact.id FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(concat('%', :messageText, '%')))))"
//    )

//    public long count(
//            @Param("channelId") Integer channelId,
//            @Param("filialId") Long filialId,
//            @Param("operatorLogin") String operatorLogin,
//            @Param("closeStatus") Integer closeStatus,
//            @Param("regDateStart") Long regDateStart,
//            @Param("regDateEnd") Long regDateEnd,
//            @Param("messageText") String messageText
//    );
}
