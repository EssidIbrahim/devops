package tn.esprit.spring.services;


import tn.esprit.common.*;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("{microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse savedOrder(TransactionRequest request)
    {
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment,Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success")? "payment processing successful and order placed": "Failure in payment api, order added to cart";
        repository.save(order);
         return new TransactionResponse(order, paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);

    }
    public String deleteOrder(int id) {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "This Order was been deleted successfully";
        }
        else {
            return "Error occured for this method please retry another time";
        }
    }

    public List<Order> getAllOrders()
    {
        List<Order> orders = new ArrayList<>();
        repository.findAll().forEach(order -> orders.add(order));
        return orders;
    }

    public void saveOrUpdate(Order order)
    {
        repository.save(order);
    }
}
