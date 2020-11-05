package com.grandprix.gpline.mm.controller;

import com.grandprix.gpline.mm.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @Autowired
    RequestService requestService;

//    @GetMapping("/requests/all")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public List<Request> getAllRequests() {
//        return requestService.getAllRequests();
//    }

//    @GetMapping(value = "/requests/all/count")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public long getAllRequestsCount() {
//        return requestService.countAllRequests();
//    }

//    @GetMapping(value = "/requests")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public List<Request> getRequests(
//            @RequestParam(required = false) Integer channelId,
//            @RequestParam(required = false) Long filialId,
//            @RequestParam(required = false) String operatorLogin,
//            @RequestParam(required = false) Integer closeStatus,
//            @RequestParam(required = false) Long regDateStart,
//            @RequestParam(required = false) Long regDateEnd,
//            @RequestParam(required = false) String messageText
//            ) {
//        return requestService.getRequests(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText);
//    }

//    @GetMapping(value = "/requests/count")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public long getRequestsCount(
//            @RequestParam(required = false) Integer channelId,
//            @RequestParam(required = false) Long filialId,
//            @RequestParam(required = false) String operatorLogin,
//            @RequestParam(required = false) Integer closeStatus,
//            @RequestParam(required = false) Long regDateStart,
//            @RequestParam(required = false) Long regDateEnd,
//            @RequestParam(required = false) String messageText
//            ) {
//        return requestService.countRequests(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText);
//    }

//    @GetMapping(value = "/statuses")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public List<String> getAllRequestStatuses() {
//        return requestService.getAllRequestStatuses();
//    }

//    @GetMapping(value = "/requests/channel/{id}")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public List<Request> getRequestsByChannel(@PathVariable Integer id) {
//        return requestService.getRequestsByChannel(id);
//    }
    
//    @PostMapping(value = "/requests/filter")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public List<Request> getRequestsByFilter(@RequestBody RequestFilter requestsFilter) {
//        return requestService.getRequestsByFilter(requestsFilter);
//    }

}
