package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.model.Notification;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @GetMapping("")
    ResponseEntity<ResponseObject> getAllNotification(){
        return notificationService.getAllNotification();
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@RequestBody Notification notification){
        return notificationService.addNewNotification(notification);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getNotificationsById(@PathVariable Long id){
        return notificationService.getNotificationById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateNotificationsById(@PathVariable(value = "id") Long id, @RequestBody Notification newNotification)  {
        return notificationService.updateNotificationById(id,newNotification);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteNotificationsById(@PathVariable(value = "id") Long id){
        return notificationService.deleteNotificationById(id);
    }
}
