package com.OneFood.ServerOneFood.controller;

import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Notification;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("one-app/v1/notification")
@PreAuthorize("isAuthenticated()")
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
    ResponseEntity<ResponseObject> addNewTypeOfDiscount(@Valid @RequestBody Notification notification) throws ErrorExecutionFailedException {
        return notificationService.addNewNotification(notification);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getNotificationsById(@PathVariable Long id) throws ErrorAccessDeniedException, ErrorNotFoundException {
        return notificationService.getNotificationById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateNotificationsById(  @PathVariable(value = "id") Long id,@Valid @RequestBody Notification newNotification) throws ErrorExecutionFailedException, ErrorNotFoundException {
        return notificationService.updateNotificationById(id,newNotification);
    }

    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteNotificationsById(@PathVariable(value = "id") Long id) throws ErrorNotFoundException {
        return notificationService.deleteNotificationById(id);
    }
}
