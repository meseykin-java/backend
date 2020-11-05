package com.grandprix.gpline.mm.repository;

import com.grandprix.gpline.mm.model.Contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    public List<Contact> findByRequestIdOrderByStartDate(String requestId);

//    public List<Contact> findByStartDateBetween(Long contactStart, Long contactEnd, Pageable pageable);
//    public int countByStartDateBetween(Long contactStart, Long contactEnd);
    
//    @Query("SELECT c FROM Contact c "
//            + " WHERE :channelId IS NULL OR c.request.channelId = :channelId "
//           )
//    public List<Contact> findByChannel(
//            @Param("channelId") Integer channelId
//    );

//    @Query("SELECT c FROM Contact c WHERE "
//            + "(:channelId IS NULL OR c.request.channelId = :channelId) "
//            + "AND (:filialId IS NULL OR c.request.filialId = :filialId) "
//            + "AND (:operatorLogin IS NULL OR c.request.id "
//            + "  IN (SELECT c1.request.id FROM Contact c1 WHERE c1.operatorLogin = :operatorLogin)) "
//            + "AND (:closeStatus IS NULL OR c.request.requestStatus = :closeStatus) "
//            + "AND (:regDateStart IS NULL OR c.request.registrationDate >= :regDateStart) "
//            + "AND (:regDateEnd IS NULL OR c.request.registrationDate <= :regDateEnd) "
//            + "AND (:messageText IS NULL OR c.request.id "
//            + "  IN (SELECT c1.request.id FROM Contact c1 WHERE c1.id "
//            + "    IN (SELECT m.contactId FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(:messageText)))) "
//            //+ "    IN (SELECT m.contact.id FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(concat('%', :messageText, '%'))))"
//            )
//    public Page<Contact> find(
//            @Param("channelId") Integer channelId,
//            @Param("filialId") Long filialId,
//            @Param("operatorLogin") String operatorLogin,
//            @Param("closeStatus") Integer closeStatus,
//            @Param("regDateStart") Long regDateStart,
//            @Param("regDateEnd") Long regDateEnd,
//            @Param("messageText") String messageText,
//            Pageable pageable
//    );

//    @Query("SELECT COUNT(c) FROM Contact c WHERE "
//            + "(:channelId IS NULL OR c.request.channelId = :channelId) "
//            + "AND (:filialId IS NULL OR c.request.filialId = :filialId) "
//            + "AND (:operatorLogin IS NULL OR c.operatorLogin = :operatorLogin) "
//            + "AND (:closeStatus IS NULL OR c.request.requestStatus = :closeStatus) "
//            + "AND (:regDateStart IS NULL OR c.request.registrationDate >= :regDateStart) "
//            + "AND (:regDateEnd IS NULL OR c.request.registrationDate <= :regDateEnd) "
//            + "AND (:messageText IS NULL OR c.id "
//            + "    IN (SELECT m.contactId FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(:messageText)))"
//            //+ "    IN (SELECT m.contact.id FROM Message m WHERE UPPER(m.messageText) LIKE UPPER(concat('%', :messageText, '%'))))"
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
