package com.grandprix.gpline.mm.repository;

import com.grandprix.gpline.mm.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    public List<Message> findByContactIdOrderByCreateDate(String contactId);

    @Query("SELECT m FROM Message m, Contact c WHERE m.contactId = c.id AND c.request.id = :requestId ORDER BY c.request.id, m.createDate")
    public List<Message> getMessagesByRequestId(@Param("requestId") String requestId);

    @Query("SELECT m FROM Message m WHERE m.contactId = :contactId AND m.createDate = " +
            "(SELECT max (d.createDate) FROM Message d WHERE d.contactId = :contactId AND d.createDate <= :date)"
    )
    public Message getMessageByContactIdAndCreateDate(
            @Param("contactId") String contactId,
            @Param("date") Long date
    );
}


