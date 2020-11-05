package com.grandprix.gpline.mm.service;

import com.grandprix.gpline.mm.model.Message;
import com.grandprix.gpline.mm.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagesByContactId(String contactId) {
        return messageRepository.findByContactIdOrderByCreateDate(contactId);
    }

    public List<Message> getMessagesByRequestId(String requestId) {
        return messageRepository.getMessagesByRequestId(requestId);
    }

    public Message findByContactIdAndCreateDate(String contactId, Long date) {
        return messageRepository.getMessageByContactIdAndCreateDate(contactId, date);
    }
}
