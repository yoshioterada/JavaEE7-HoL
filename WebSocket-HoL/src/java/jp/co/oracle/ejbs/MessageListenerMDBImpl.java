/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.oracle.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/inforegtopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "${com.sun.aas.instanceName}"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/inforegtopic")
})
public class MessageListenerMDBImpl implements MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListenerMDBImpl.class.getPackage().getName());

    @EJB
    ClientManageSinglEJB clManager;
    
    public MessageListenerMDBImpl() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            clManager.sendMessage(text);
        } catch (JMSException ex) {
            logger.log(Level.SEVERE, "onMessage() failed", ex);
        }
    }
}
