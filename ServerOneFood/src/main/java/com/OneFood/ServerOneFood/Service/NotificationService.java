package com.OneFood.ServerOneFood.Service;


import com.OneFood.ServerOneFood.Model.Notification;
import com.OneFood.ServerOneFood.Model.ResponseObject;
import com.OneFood.ServerOneFood.Reponsitory.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public ResponseEntity<ResponseObject> getAllNotification(){
        List<Notification> notifications =  notificationRepository.findAll();
        if(notifications.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty notification list ", notifications));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find all successful notification ", notifications));

    }

    public ResponseEntity<ResponseObject> addNewNotification(Notification newNotification){
        Notification notification = notificationRepository.save(newNotification);
        if(notification == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"New notification create failed ",notification));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New notification successfully created ",notification));

    }

    public ResponseEntity<ResponseObject> updateNotificationById(Long id, Notification newNotification)  {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if(notification==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find notification with id "+id,null));
       notification.setNotificationContent(newNotification.getNotificationContent());
       notification.setNotificationImage(newNotification.getNotificationImage());
       notification.setNotificationTime(newNotification.getNotificationTime());
       notification.setNotificationStatus(newNotification.getNotificationStatus());
       notification.setNotificationTitle(newNotification.getNotificationTitle());

        Notification updatedNotification = notificationRepository.save(notification);
        if(updatedNotification == null)
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(false,"Notification update failed ",updatedNotification));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Notification successfully updated ",updatedNotification));

    }

    public ResponseEntity<ResponseObject> getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if(notification==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find notification with id "+id,null));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful notification with id "+id,notification));
    }

    public ResponseEntity<ResponseObject> deleteNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if(notification==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false,"Cannot find notification with id "+id,null));
        notificationRepository.delete(notification);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful notification with id "+id,notification));
    }
}
