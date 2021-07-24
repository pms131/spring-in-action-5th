package tacos.kitchen.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import tacos.Order;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;
    //private MessageConverter converter;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }


    @Override
    public Order receiveOrder() throws JMSException {
        /*
        Message message = jms.receive("tacocloud.order.queue");
        return (Order) converter.fromMessage(message);
        */

        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}
