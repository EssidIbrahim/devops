package tn.esprit.common;


import tn.esprit.spring.entities.Order;

public class TransactionResponse {
    private Order order;
    private double amount;
    private String transactionId;
    private String message;
}
