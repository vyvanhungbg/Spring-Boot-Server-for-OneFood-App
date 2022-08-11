package com.OneFood.ServerOneFood.service;


import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.Notification;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.reponsitory.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final MyService myService;


    public static INotificationListener listener;

    public interface INotificationListener{
        void update(Notification notification);
        void create(Notification notification);
        void delete(Notification notification);
    }

    public NotificationService(NotificationRepository notificationRepository, MyService myService) {

        this.notificationRepository = notificationRepository;
        this.myService = myService;
    }


    public ResponseEntity<ResponseObject> getAllNotification(){
        List<Notification> notifications =  notificationRepository.findAll();
        if(notifications.isEmpty())
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Empty notification list ", notifications));
        List<Notification> notificationFilter = new ArrayList<>(notifications);
        Long idUser = myService.getPrincipal();
        if(!myService.isRoleAdmin())
            notificationFilter = notifications.stream().filter(notification -> notification.getIdUser() == idUser).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find "+notificationFilter.size()+" notification ", notificationFilter));

    }

    public ResponseEntity<ResponseObject> addNewNotification(Notification newNotification) throws ErrorExecutionFailedException {
        if(notificationRepository.existsById(newNotification.getIdNotification()))
            throw new ErrorExecutionFailedException("New notification create failed .Because this item already exists");
        Notification notification = notificationRepository.save(newNotification);
        if(notification == null)
            throw new ErrorExecutionFailedException("New notification create failed ");
        try{
            listener.create(notification);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New notification successfully created ",notification));

    }

    public ResponseEntity<ResponseObject> updateNotificationById(Long id, Notification newNotification) throws ErrorNotFoundException, ErrorExecutionFailedException {

        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find notification with id "+id));

       notification.setNotificationContent(newNotification.getNotificationContent());
       notification.setNotificationImage(newNotification.getNotificationImage());
       notification.setNotificationTime(newNotification.getNotificationTime());
       notification.setNotificationStatus(newNotification.getNotificationStatus());
       notification.setNotificationTitle(newNotification.getNotificationTitle());

        Notification updatedNotification = notificationRepository.save(notification);

        if(updatedNotification == null || newNotification== null)
            throw new ErrorExecutionFailedException("Notification update failed ");
        try{
            listener.update(updatedNotification);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Notification successfully updated ",updatedNotification));

    }

    public ResponseEntity<ResponseObject> getNotificationById(Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find notification with id "+id));
        Long idUser = myService.getPrincipal();
        if(idUser!=notification.getIdUser())
            throw new ErrorAccessDeniedException("Access is denied");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful notification with id "+id,notification));
    }

    public ResponseEntity<ResponseObject> deleteNotificationById(Long id) throws ErrorNotFoundException {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find notification with id "+id));

        notificationRepository.delete(notification);
        try{
            listener.delete(notification);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful notification with id "+id,notification));
    }
}
