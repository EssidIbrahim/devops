package tn.esprit.spring.controller;

import tn.esprit.common.*;
import tn.esprit.common.TransactionResponse;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping ("/Orders")
    private List<Order> getAllOrders()
    {
        return service.getAllOrders();
    }
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
        return service.savedOrder(request);
    }

    @DeleteMapping(value="/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteCandidate(@PathVariable(value= "id") int id){
        return new ResponseEntity<>(service.deleteOrder(id),HttpStatus.OK);
    }

   
}
