package jp.co.oracle.websockets;

import javax.ejb.EJB;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import jp.co.oracle.ejbs.ClientManageSinglEJB;

@ServerEndpoint("/infotrans")
public class InfoTransServerEndpoint {

    @EJB
    ClientManageSinglEJB clManager;

    
    @OnOpen
    public void initOpen(Session session) {
        clManager.addClient(session);
    }

    @OnClose
    public void closeWebSocket(Session session) {
        clManager.removeClient(session);
    }    
}
