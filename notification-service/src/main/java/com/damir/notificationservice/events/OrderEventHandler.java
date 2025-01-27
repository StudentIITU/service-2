package com.damir.notificationservice.events;

import com.damir.notificationservice.config.domain.NotificationService;
import com.damir.notificationservice.domain.models.OrderCancelledEvent;
import com.damir.notificationservice.domain.models.OrderCreatedEvent;
import com.damir.notificationservice.domain.models.OrderDeliveredEvent;
import com.damir.notificationservice.domain.models.OrderErrorEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class OrderEventHandler {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Order Created event: " + event);
        notificationService.sendOrderCreatedNotification(event);
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderDeliveredEvent event) {
        System.out.println("Order Delivered event: " + event);
        notificationService.sendOrderDeliveredNotification(event);
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCancelledEvent event) {
        System.out.println("Order Cancelled event: " + event);
        notificationService.sendOrderCancelledNotification(event);
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderErrorEvent event) {
        System.out.println("Order Error event: " + event);
        notificationService.sendOrderErrorEventNotification(event);
    }

}
