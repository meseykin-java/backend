package com.grandprix.gpline.mm.service;

import java.util.List;

import com.grandprix.gpline.mm.model.Contact;
import com.grandprix.gpline.mm.utils.Log;
import com.grandprix.gpline.mm.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grandprix.gpline.mm.model.filter.ContactFilterPost;
import com.grandprix.gpline.mm.repository.ContactFilterRepository;
import com.grandprix.gpline.mm.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    ContactFilterRepository contactFilterRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactService(ContactFilterRepository contactFilterRepository) {
        this.contactFilterRepository = contactFilterRepository;
    }

//    public List<Contact> getAllContacts() {
//        return contactRepository.findAll();
//    }

    public List<Contact> getContactsByRequestId(String requestId) {
        return contactRepository.findByRequestIdOrderByStartDate(requestId);
    }

//    public List<Contact> getAllContacts(String contactStartDate, String contactEndDate, String order, int limit, int offset) {
//        Long startDate = new Long(contactStartDate);
//        Long endDate = new Long(contactEndDate);
//        Pageable pageRequest = Utils.getPageable(limit, offset, order);
//        return contactRepository.findByStartDateBetween(startDate, endDate, pageRequest);
//    }

//    public int getAllContactsCount(String contactStartDate, String contactEndDate) {
//        Long startDate = new Long(contactStartDate);
//        Long endDate = new Long(contactEndDate);
//        return contactRepository.countByStartDateBetween(startDate, endDate);
//    }

//    public List<Contact> getContactsByChannel(Integer channelId) {
//        return contactRepository.findByChannel(channelId);
//    }

//    public List<Contact> getContacts(Integer channelId, Long filialId, String operatorLogin, Integer closeStatus,
//            Long regDateStart, Long regDateEnd, String messageText, Integer limit, Integer offset, String order) {
//        // Приведение дефолтных значений (-1 и "") к null если они есть
//        if (channelId != null && channelId == -1)
//            channelId = null;
//        if (filialId != null && filialId == -1)
//            filialId = null;
//        if ("".equals(operatorLogin))
//            operatorLogin = null;
//        if (closeStatus != null && closeStatus == -1)
//            closeStatus = null;
//        if (regDateStart != null && regDateStart == -1)
//            regDateStart = null;
//        if (regDateEnd != null && regDateEnd == -1)
//            regDateEnd = null;
//        if ("".equals(messageText))
//            messageText = null;
//
//        if (limit == null)
//            limit = 99999;
//        if (offset == null)
//            offset = 0;
//       Pageable pageRequest = null;
//       if (order == null || order.length() == 0)
//           pageRequest = Utils.getPageable(limit, offset);
//       else
//           pageRequest = Utils.getPageable(limit, offset, order);
//        Page<Contact> page = contactRepository.find(channelId, filialId, operatorLogin, closeStatus,
//                regDateStart, regDateEnd, messageText, pageRequest);
//
//        return page.getContent();
//    }

//    public long countContacts(Integer channelId, Long filialId, String operatorLogin, Integer closeStatus,
//            Long regDateStart, Long regDateEnd, String messageText) {
//     // Приведение дефолтных значений (-1 и "") к null если они есть
//        if (channelId != null && channelId == -1)
//            channelId = null;
//        if (filialId != null && filialId == -1)
//            filialId = null;
//        if ("".equals(operatorLogin))
//            operatorLogin = null;
//        if (closeStatus != null && closeStatus == -1)
//            closeStatus = null;
//        if (regDateStart != null && regDateStart == -1)
//            regDateStart = null;
//        if (regDateEnd != null && regDateEnd == -1)
//            regDateEnd = null;
//        if ("".equals(messageText))
//            messageText = null;
//        return contactRepository.count(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd,
//                messageText);
//    }

    public List<Contact> getContactsByFilter(ContactFilterPost contactFilter, Integer limit, Integer offset, String... orders) {
        if (limit == null)
            limit = 99999;
        if (offset == null)
            offset = 0;
       Log.debug("getContactsByFilter: limit = " + limit + ", offset = " + offset + ", orders = " + orders);
       Pageable pageRequest = null;
       if (orders == null || orders.length == 0 || orders.length == 1 && orders[0] == null)
           pageRequest = Utils.getPageable(limit, offset);
       else
           pageRequest = Utils.getPageable(limit, offset, orders);
        
//        Log.debug("getContactsByFilter = " + contactFilterRepository.findByFilter(contactFilter, pageRequest));
        return contactFilterRepository.findByFilter(contactFilter, pageRequest);
    }
    
    public List<Contact> getAllContactsByFilter(ContactFilterPost contactFilter) {
        Integer limit = contactFilter.getLimit();
        Integer offset = contactFilter.getOffset();
        List<String> orderList = contactFilter.getOrderList();
        if (orderList != null)
            return getContactsByFilter(contactFilter, limit, offset, orderList.toArray(new String[orderList.size()]));
        else
            return getContactsByFilter(contactFilter, limit, offset);
    }
    
    public Long countAllContactsByFilter(ContactFilterPost contactFilter) {
        return contactFilterRepository.countByFilter(contactFilter);
    }
    
}
