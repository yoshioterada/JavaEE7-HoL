package jp.co.oracle.cdis;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Named(value = "indexManage")
@RequestScoped
public class IndexPageMgdBean {

    @Inject
    @JMSConnectionFactory("jms/topicCon")
    JMSContext context; 
    
    @Resource(mappedName = "jms/inforegtopic")
    Topic topic;
    
    private String message;

    public IndexPageMgdBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String pushSendButton() {
        context.createProducer().send(topic, getMessage());
        return "";
    }
}
