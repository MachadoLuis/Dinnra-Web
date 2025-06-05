package pe.dinnra_web.sistema_gestion.api.util;

import jakarta.annotation.PreDestroy;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ShutDownWebSocketHandler extends TextWebSocketHandler {

    private WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        this.session = session;
    }

    @EventListener(ContextClosedEvent.class)
    public void onShutDown(){
        try{
            if (session != null && session.isOpen()){
                session.sendMessage(new TextMessage("shutdown"));
                System.out.println("Cerrando conexion del webSocket");
            }else {
                System.out.println("Conexion no cerrada session es nula");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
