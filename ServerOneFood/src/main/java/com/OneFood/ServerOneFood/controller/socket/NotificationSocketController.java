package com.OneFood.ServerOneFood.controller.socket;

import com.OneFood.ServerOneFood.model.Notification;
import com.OneFood.ServerOneFood.service.NotificationService;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationSocketController implements NotificationService.INotificationListener{

    private SocketIONamespace notificationSocket;

    @Autowired
    private NotificationService notificationService;


    @Autowired
    public NotificationSocketController(SocketIOServer server){
        this.notificationSocket = server.addNamespace("/notification");
        this.notificationSocket.addConnectListener(onConnectEvent);
        this.notificationSocket.addDisconnectListener(onDisconnectEvent);
        this.notificationSocket.addEventListener("send", String.class, onSendMess);
        NotificationService.listener = this;
    }

    private DataListener<String> onSendMess = new DataListener<String>() {
        @Override
        public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
            System.out.println("Chat /chat namespace: " + s);
            notificationSocket.getBroadcastOperations().sendEvent("send", s);
            ackRequest.sendAckData("Message sent!");


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
        }
    };


    @Override
    public void update(Notification notification) {
        notificationSocket.getBroadcastOperations().sendEvent("send", "Socket notification update"+notification.getIdNotification());
        System.out.println("Socket notification update");
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
