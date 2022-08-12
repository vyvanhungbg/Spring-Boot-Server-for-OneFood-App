package com.OneFood.ServerOneFood.controller.socket;

import com.OneFood.ServerOneFood.model.Notification;
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
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        this.notificationSocket.addEventListener("register", String.class, onRegisterNotification);
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

    private DataListener<String> onRegisterNotification = new DataListener<String>() {
        @Override
        public void onData(SocketIOClient socketIOClient, String userID, AckRequest ackRequest) throws Exception {
            userRegister.put( socketIOClient,userID);
            notificationSocket.getBroadcastOperations().sendEvent("register",socketIOClient, userID);
            System.out.println("user register : " + userRegister.getValueByKey(socketIOClient));
            System.out.println("user client : " + userRegister.getKeyByValue(userID).getSessionId());
        }
    };


    private ConnectListener onConnectEvent = new ConnectListener() {
        @Override
        public void onConnect(SocketIOClient client) {
            System.out.println("Connected to /notification namespace! " + client.getSessionId());
        }
    };

    private DisconnectListener onDisconnectEvent = new DisconnectListener() {
        @Override
        public void onDisconnect(SocketIOClient client) {
           // notificationSocket.getBroadcastOperations().sendEvent("leave");
            System.out.println("Disconnected from /chat namespace! " + client.getSessionId());
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
        client.sendEvent("send", "Socket notification update"+notification.getIdNotification());
        System.out.println("Socket notification update with user ID : "+notification.getIdUser());
    }

    @Override
    public void create(Notification notification) {
        notificationSocket.getBroadcastOperations().sendEvent("send", "Socket notification create"+notification.getIdNotification());
        System.out.println("Socket notification create");
    }

    @Override
    public void delete(Notification notification) {
        notificationSocket.getBroadcastOperations().sendEvent("send", "Socket notification delete "+notification.getIdNotification());
        System.out.println("Socket notification delete");
    }
}
