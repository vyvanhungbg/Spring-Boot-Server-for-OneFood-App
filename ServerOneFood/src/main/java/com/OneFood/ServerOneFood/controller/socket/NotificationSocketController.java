package com.OneFood.ServerOneFood.controller.socket;

import com.OneFood.ServerOneFood.model.Notification;
import com.OneFood.ServerOneFood.model.ResponseObject;
import com.OneFood.ServerOneFood.service.NotificationService;
import com.OneFood.ServerOneFood.utils.MapTwoWay;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import org.bouncycastle.asn1.eac.BidirectionalMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationSocketController implements NotificationService.INotificationListener{

    private SocketIONamespace notificationSocket;
    private MapTwoWay< SocketIOClient, String> userRegister = new MapTwoWay<>();


    @Autowired
    private NotificationService notificationService;




    @Autowired
    public NotificationSocketController(SocketIOServer server){
        this.notificationSocket = server.addNamespace("/notification");
        this.notificationSocket.addConnectListener(onConnectEvent);
        this.notificationSocket.addDisconnectListener(onDisconnectEvent);
        this.notificationSocket.addEventListener("send", String.class, onSendMess);
        this.notificationSocket.addEventListener("update", Notification.class, onUpdateNotification);
        this.notificationSocket.addEventListener("create", Notification.class, onCreateNotification);
        this.notificationSocket.addEventListener("register", String.class, onRegisterNotification);
        /*this.notificationSocket.addEventListener("getAll", Object.class, onGetAllNotification);*/
        NotificationService.listener = this;
    }

    private DataListener<String> onSendMess = new DataListener<String>() {
        @Override
        public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
            System.out.println("User : " + socketIOClient.getSessionId() + " : "+s);
            notificationSocket.getBroadcastOperations().sendEvent("send", s);
            ackRequest.sendAckData("Message sent!");


        }
    };


    private DataListener<Notification> onUpdateNotification = new DataListener<Notification>() {
        @Override
        public void onData(SocketIOClient socketIOClient, Notification notification, AckRequest ackRequest) throws Exception {
            socketIOClient.sendEvent("update", notification);
            ackRequest.sendAckData("Notification update!");
        }
    };


    private DataListener<Notification> onCreateNotification = new DataListener<Notification>() {
        @Override
        public void onData(SocketIOClient socketIOClient, Notification notification, AckRequest ackRequest) throws Exception {
            socketIOClient.sendEvent("create", notification);
            ackRequest.sendAckData("Notification create!");
        }
    };

    private DataListener<String> onRegisterNotification = new DataListener<String>() {
        @Override
        public void onData(SocketIOClient socketIOClient, String userID, AckRequest ackRequest) throws Exception {
            userRegister.put( socketIOClient,userID);
            socketIOClient.sendEvent("register", userID);
            System.out.println("user register : " + userRegister.getValueByKey(socketIOClient));
            System.out.println("user client : " + userRegister.getKeyByValue(userID).getSessionId());
        }
    };

    /*private DataListener<Object> onGetAllNotification = new DataListener<Object>() {
        @Override
        public void onData(SocketIOClient socketIOClient, Object o, AckRequest ackRequest) throws Exception {
            String idUser = userRegister.getValueByKey(socketIOClient);
            if(idUser == null || idUser.equals(""))
                return;
            ResponseEntity<ResponseObject> response = notificationService.getAllNotification(Long.parseLong(idUser));
            socketIOClient.sendEvent("getAll", socketIOClient, response);
        }
    };*/


    private ConnectListener onConnectEvent = new ConnectListener() {
        @Override
        public void onConnect(SocketIOClient client) {
            System.out.println("Connected to /notification namespace! client :  " + client.getSessionId());
            System.out.println("notification namespace has  " + userRegister.size() +1+ " user connected");
        }
    };

    private DisconnectListener onDisconnectEvent = new DisconnectListener() {
        @Override
        public void onDisconnect(SocketIOClient client) {
           // notificationSocket.getBroadcastOperations().sendEvent("leave");
            System.out.println("Disconnected from /notification namespace! " + client.getSessionId());
            System.out.println("User unregister! " + userRegister.getValueByKey(client));
            userRegister.removeByKey(client);
        }
    };


    @Override
    public void update(Notification notification) {
        SocketIOClient client = userRegister.getKeyByValue(notification.getIdUser()+"");
        System.out.println("user ID has notification  "+notification.getIdUser());
        if(client == null)
            return;
        client.sendEvent("update", notification);
        System.out.println("Socket notification update with user ID : "+notification.getIdUser());
    }

    @Override
    public void create(Notification notification) {
        SocketIOClient client = userRegister.getKeyByValue(notification.getIdUser()+"");
        if(client == null)
            return;
        client.sendEvent("create", notification);
        System.out.println("Socket notification create");
    }

    @Override
    public void delete(Notification notification) {
       /* SocketIOClient client = userRegister.getKeyByValue(notification.getIdUser()+"");
        notificationSocket.getBroadcastOperations().sendEvent("send", "Socket notification delete "+notification.getIdNotification());
*/        System.out.println("Socket notification delete");
    }
}
