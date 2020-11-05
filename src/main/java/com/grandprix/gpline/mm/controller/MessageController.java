package com.grandprix.gpline.mm.controller;

import com.grandprix.gpline.mm.model.Message;
import com.grandprix.gpline.mm.service.MessageService;
import com.grandprix.gpline.mm.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/messages/contact/{contactId}")
    @CrossOrigin(origins = "*")
    public List<Message> getContactMessages(@PathVariable String contactId){
        List<Message> messages = messageService.getMessagesByContactId(contactId);
        return messages;
    }

    @GetMapping("/messages/contacts/request/{requestId}")
    @CrossOrigin(origins = "*")
    public List<Message> getRequestMessages(@PathVariable String requestId){
        List<Message> messages = messageService.getMessagesByRequestId(requestId);
        return messages;
    }

    @GetMapping("/message/contactId/{contactId}/date/{date}")
    @CrossOrigin(origins = "*")
    public Message getMessage(@PathVariable String contactId, @PathVariable Long date){
        Message message = messageService.findByContactIdAndCreateDate(contactId, date);
        return message;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String index() {
        Log.debug("A DEBUG Message");
        return "Привет! Приложение работает";
    }
}
