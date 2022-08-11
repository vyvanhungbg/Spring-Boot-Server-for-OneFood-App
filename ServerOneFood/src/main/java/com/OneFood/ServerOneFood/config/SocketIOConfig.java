package com.OneFood.ServerOneFood.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class SocketIOConfig {

    @Value("${socket.io.host}")
    public String SOCKET_IO_HOST;

    @Value("${socket.io.port}")
    public Integer SOCKET_IO_PORT;


    @Value ( "${workCount}" )
    private  int workCount ;

    @Value ( "${allowCustomRequests}" )
    private  boolean allowCustomRequests ;

    @Value ( "${upgradeTimeout}" )
    private  int upgradeTimeout ;

    @Value ( "${pingTimeout}" )
    private  int pingTimeout ;

    @Value ( "${pingInterval}" )
    private  int pingInterval ;

    @Value ( "${maxFramePayloadLength}" )
    private  int maxFramePayloadLength ;

    @Value ( "${maxHttpContentLength}" )
    private  int maxHttpContentLength ;

    @Bean
    public com.corundumstudio.socketio.Configuration socketConfig(){
        com.corundumstudio.socketio.Configuration socketConfig = new com.corundumstudio.socketio.Configuration();

        //socketConfig.setHostname(SOCKET_IO_HOST);
        socketConfig.setPort(SOCKET_IO_PORT);

        socketConfig.setWorkerThreads ( workCount ) ;
        socketConfig.setAllowCustomRequests ( allowCustomRequests ) ;
        socketConfig.setUpgradeTimeout ( upgradeTimeout ) ;
        socketConfig.setPingTimeout ( pingTimeout ) ;
        socketConfig.setPingInterval ( pingInterval ) ;
        socketConfig.setMaxHttpContentLength ( maxHttpContentLength ) ;
        socketConfig.setMaxFramePayloadLength ( maxFramePayloadLength ) ;



        //TODO: get all supported transports
        socketConfig.getTransports().forEach(transport->{
            System.out.println("Supported Transport: " + transport.name());
        });

        //TODO: set the maximum payload data
        socketConfig.setMaxFramePayloadLength(1*1024*1024); // megabytes * kilobytes * bytes
        //socketConfig.setTransports(Transport.WEBSOCKET);
        return socketConfig;
    }

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration socket = socketConfig();
        SocketIOServer server = new SocketIOServer(socket);
        System.out.println("_______>Starting SocketIO Server...");
        server.start();
        //server.startAsync();
        System.out.println("_______________>Socket sv host name : "+ socket.getHostname()+" port : "+socket.getPort());
        System.out.println("_______________>Socket sv ping :"+ socket.getPingTimeout()+" err"+socket.getExceptionListener()+"");
        return server;
    }

    //For enable socket.io annotation ( @onConnect, @onEvent,...)
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }

    @PreDestroy
    public void stopSocketIOServer(){
        System.out.println("______>SocketIO Server stop !");
        socketIOServer().stop();
    }

}
