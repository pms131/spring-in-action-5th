package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
public class RabbitOrderReceiver {
    private RabbitTemplate rabbit;
    private MessageConverter converter;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbit, MessageConverter converter) {
        this.rabbit = rabbit;
        this.converter = converter;
    }

    public Order receiveOrder() {
        /*Message message = rabbit.receive("tacocloud.orders");
        return message != null ? (Order) converter.fromMessage(message) : null;*/

        return rabbit.convertSendAndReceiveAsType("tacocloud.order.queue",
                new ParameterizedTypeReference<Order>() {
        });
    }
}
