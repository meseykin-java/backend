package com.grandprix.gpline.mm.controller;

import java.util.ArrayList;
import java.util.List;

import com.grandprix.gpline.mm.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grandprix.gpline.mm.model.filter.ContactFilterPost;
import com.grandprix.gpline.mm.service.ContactService;

@RestController
public class ContactController {

    @Autowired
    ContactService contactService;

//    @GetMapping("/contacts/all")
//    @CrossOrigin(origins = "*")
//    public List<Contact> getAllContacts() {
//        return contactService.getAllContacts();
//    }
    
    @GetMapping("/contacts/request/{requestId}")
    @CrossOrigin(origins = "*")
    public List<Contact> getContacts(@PathVariable String requestId){
        //Log.info("getContacts() requestId=" + requestId);
        List<Contact> contacts = contactService.getContactsByRequestId(requestId);
        //Log.debug("getContacts() contacts="+contacts);
        return contacts;
    }

//    @GetMapping("/contacts")
//    @CrossOrigin(origins = "*")
//    public List<Contact> getAllContacts(
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate,
//            @RequestParam(required = false) Integer limit,
//            @RequestParam(required = false) Integer offset,
//            @RequestParam(required = false) String order
//    ){
//        Log.info("getAllContacts() startDate=" + startDate);
//        Log.info("getAllContacts() endDate=" + endDate);
//        return contactService.getAllContacts(startDate, endDate, order, limit, offset);
//    }
    
//    @GetMapping("/contacts/count")
//    @CrossOrigin(origins = "*")
//    public int getAllContactsCount(
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate
//    ){
//        Log.info("getAllContactsCount() startDate=" + startDate);
//        Log.info("getAllContactsCount() endDate=" + endDate);
//        return contactService.getAllContactsCount(startDate, endDate);
//    }
    
//    @GetMapping(value = "/contacts/channel/{id}")
//    @CrossOrigin(origins = "*")
//    public List<Contact> getContactsByChannel(@PathVariable Integer id) {
//        return contactService.getContactsByChannel(id);
//    }
    
//    @GetMapping(value = "/contacts/full")
//    @CrossOrigin(origins = "*")
//    public List<Contact> getContacts(
//            @RequestParam(required = false) Integer channelId,
//            @RequestParam(required = false) Long filialId,
//            @RequestParam(required = false) String operatorLogin,
//            @RequestParam(required = false) Integer closeStatus,
//            @RequestParam(required = false) Long regDateStart,
//            @RequestParam(required = false) Long regDateEnd,
//            @RequestParam(required = false) String messageText,
//            @RequestParam(required = false) Integer limit,
//            @RequestParam(required = false) Integer offset,
//            @RequestParam(required = false) String order
//        ) {
//        return contactService.getContacts(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd,
//                messageText, limit, offset, order);
//    }

//    @GetMapping(value = "/contacts/full/count")
//    @CrossOrigin(origins = "*")
//    public long getRequestsCount(
//            @RequestParam(required = false) Integer channelId,
//            @RequestParam(required = false) Long filialId,
//            @RequestParam(required = false) String operatorLogin,
//            @RequestParam(required = false) Integer closeStatus,
//            @RequestParam(required = false) Long regDateStart,
//            @RequestParam(required = false) Long regDateEnd,
//            @RequestParam(required = false) String messageText
//        ) {
//        return contactService.countContacts(channelId, filialId, operatorLogin, closeStatus, regDateStart,
//                regDateEnd, messageText);
//    }

/**
 * Для поиска текущих контактов из браузера. По умолчанию параметр limit равен 100. 
 * Программные тесты данного сервиса отсутствуют.
 */
    @GetMapping(value = "/contacts/filter")
    @CrossOrigin(origins = "*")
    public List<Contact> getContactsByFilter(
            @RequestParam(required = false) Integer channelId,
            @RequestParam(required = false) String filialId,
            @RequestParam(required = false) String operatorLogin,
            @RequestParam(required = false) Integer closeStatus,
            @RequestParam(required = false) Long regDateStart,
            @RequestParam(required = false) Long regDateEnd,
            @RequestParam(required = false) String messageText,
            
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) String order
            ) {
        ContactFilterPost contactFilterPost = new ContactFilterPost();
        if (channelId != null)
            contactFilterPost.setChannelIdList(new ArrayList<Integer>() {{add(channelId);}});
        if (filialId != null)
            contactFilterPost.setFilialIdList(new ArrayList<String>() {{add(filialId);}});
        if (operatorLogin != null)
            contactFilterPost.setOperatorLogin(operatorLogin);
        if (closeStatus != null)
            contactFilterPost.setCloseStatusList(new ArrayList<Integer>() {{add(closeStatus);}});
        if (regDateStart != null)
            contactFilterPost.setRegDateStart(regDateStart);
        if (regDateEnd != null)
            contactFilterPost.setRegDateEnd(regDateEnd);
        if (messageText != null)
            contactFilterPost.setMessageText(messageText);
        if (limit == null)
            limit = 10;
        return contactService.getContactsByFilter(contactFilterPost, limit, offset, order);
    }

    /**
     * Подсчитывает количество контактов. Запрос задается из браузера 
     * Программные тесты данного сервиса отсутствуют.
     */
        @GetMapping(value = "/contacts/filter/count")
        @CrossOrigin(origins = "*")
        public Long countsByFilter(
                @RequestParam(required = false) Integer channelId,
                @RequestParam(required = false) String filialId,
                @RequestParam(required = false) String operatorLogin,
                @RequestParam(required = false) Integer closeStatus,
                @RequestParam(required = false) Long regDateStart,
                @RequestParam(required = false) Long regDateEnd,
                @RequestParam(required = false) String messageText
                ) {
            ContactFilterPost contactFilterPost = new ContactFilterPost();
            if (channelId != null)
                contactFilterPost.setChannelIdList(new ArrayList<Integer>() {{add(channelId);}});
            if (filialId != null)
                contactFilterPost.setFilialIdList(new ArrayList<String>() {{add(filialId);}});
            if (operatorLogin != null)
                contactFilterPost.setOperatorLogin(operatorLogin);
            if (closeStatus != null)
                contactFilterPost.setCloseStatusList(new ArrayList<Integer>() {{add(closeStatus);}});
            if (regDateStart != null)
                contactFilterPost.setRegDateStart(regDateStart);
            if (regDateEnd != null)
                contactFilterPost.setRegDateEnd(regDateEnd);
            if (messageText != null)
                contactFilterPost.setMessageText(messageText);
            return contactService.countAllContactsByFilter(contactFilterPost);
        }

    @PostMapping(value = "/contacts/filter/all")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public List<Contact> getAllContactsByFilter(
            @RequestBody ContactFilterPost contactFilter
            ) {
        return contactService.getAllContactsByFilter(contactFilter);
    }

    @PostMapping(value = "/contacts/filter/all/count")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Long countAllContactsByFilter(
            @RequestBody ContactFilterPost contactFilter
            ) {
        return contactService.countAllContactsByFilter(contactFilter);
    }
}

