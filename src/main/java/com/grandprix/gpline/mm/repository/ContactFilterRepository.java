package com.grandprix.gpline.mm.repository;

import java.util.List;

import com.grandprix.gpline.mm.model.Contact;
import org.springframework.data.domain.Pageable;

import com.grandprix.gpline.mm.model.filter.ContactFilterPost;

public interface ContactFilterRepository { //extends JpaRepository<Operator, String> {

    public List<Contact> findByFilter(ContactFilterPost contactFilter, Pageable pageRequest);
    public Long countByFilter(ContactFilterPost contactFilter);

}
