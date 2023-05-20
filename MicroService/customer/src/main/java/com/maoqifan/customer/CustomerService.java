package com.maoqifan.customer;

import com.maoqifan.amqp.RabbitMQMessageProducer;
import com.maoqifan.clients.fraud.FraudCheckResponse;
import com.maoqifan.clients.fraud.FraudClient;
import com.maoqifan.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerService {
    @Resource
    CustomerRepository customerRepository;
    @Resource
    FraudClient fraudClient;
    @Resource
    RabbitMQMessageProducer mqMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
//        FraudCheckResponse response = restTemplate.getForObject("http://fraud/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class, customer.getId());
        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());
        if (response != null && response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName())
        );
//        notificationClient.sendNotification(notificationRequest);
        mqMessageProducer
                .publish(
                        notificationRequest,
                        "internal.exchange",
                        "internal.notification.routing-key"
                );

    }

}
