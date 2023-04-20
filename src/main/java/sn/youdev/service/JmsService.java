package sn.youdev.service;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import sn.youdev.entities.Taco;
import sn.youdev.entities.TacoOrder;

@Service
public class JmsService {
    private final JmsTemplate jms;

    @Autowired
    public JmsService(JmsTemplate jms) {
        this.jms = jms;
    }

    public void sendOrder(Taco tacoOrder) {
        jms.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(tacoOrder);
            }
        });
    }
}
