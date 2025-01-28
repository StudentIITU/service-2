package com.damir.notificationservice.events;

import com.damir.notificationservice.domain.NotificationService;
import com.damir.notificationservice.domain.OrderEventEntity;
import com.damir.notificationservice.domain.OrderEventRepository;
import com.damir.notificationservice.domain.models.OrderCancelledEvent;
import com.damir.notificationservice.domain.models.OrderCreatedEvent;
import com.damir.notificationservice.domain.models.OrderDeliveredEvent;
import com.damir.notificationservice.domain.models.OrderErrorEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class OrderEventHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);
    private final NotificationService notificationService;
    private final OrderEventRepository orderEventRepository;

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Order Created event: {}", event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderCreated event with eventId: {}", event.eventId());
            return;
        }
        ;
        notificationService.sendOrderCreatedNotification(event);
        OrderEventEntity eventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(eventEntity);
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        log.info("Order Delivered event: {}", event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderDelivered event with eventId: {}", event.eventId());
            return;
        }
        ;
        notificationService.sendOrderDeliveredNotification(event);
        OrderEventEntity eventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(eventEntity);
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        log.info("Order Cancelled event: {}", event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderCancelled event with eventId: {}", event.eventId());
            return;
        }
        ;
        notificationService.sendOrderCancelledNotification(event);
        OrderEventEntity eventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(eventEntity);
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event) {
        log.info("Order Error event: {}", event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderError event with eventId: {}", event.eventId());
            return;
        }
        ;
        notificationService.sendOrderErrorEventNotification(event);
        OrderEventEntity eventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(eventEntity);
    }
}
