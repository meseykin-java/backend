package com.grandprix.gpline.mm.service;

import com.grandprix.gpline.mm.repository.RequestFilterRepository;
import com.grandprix.gpline.mm.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    RequestFilterRepository requestFilterRepository;

//    public List<Request> getAllRequests() {
//        Log.info("getAllRequests = " + requestRepository.findAll());
//        return requestRepository.findAll();
//    }
    
//    public long countAllRequests() {
//        Log.info("countAllRequests = " + requestRepository.count());
//        return requestRepository.count();
//    }
    
//    public List<Request> getRequestsByChannel(Integer channelId) {
//        Log.info("getRequests = " + requestRepository.findByChannel(channelId));
//        return requestRepository.findByChannel(channelId);
//    }

//    public List<Request> getRequests(Integer channelId, Long filialId, String operatorLogin, Integer closeStatus, Long regDateStart, Long regDateEnd, String messageText) {
//        Log.info("requests = " + requestRepository.find(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText));
//        return requestRepository.find(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText);
//    }

//    public long countRequests(Integer channelId, Long filialId, String operatorLogin, Integer closeStatus, Long regDateStart, Long regDateEnd, String messageText) {
//        Log.info("countRequests = " + requestRepository.count(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText));
//        return requestRepository.count(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText);
//    }
    
//    public List<String> getAllRequestStatuses() {
//        List<String> requestStatusList =
//              Stream.of(CloseStatus.values())
//              .map(CloseStatus::toString)
//              .collect(Collectors.toList());
//        Log.info("getAllRequestStatuses = " + requestStatusList);
//        return requestStatusList;
//    }
    
//    public List<Request> getRequestsByFilter(RequestFilter requestsFilter) {
//        Log.info("getRequestsByFilter = " + requestFilterRepository.findByFilter(requestsFilter));
//        return requestFilterRepository.findByFilter(requestsFilter);
//    }
    
}
