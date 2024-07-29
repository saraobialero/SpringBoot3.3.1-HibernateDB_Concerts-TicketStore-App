package com.project.utils;

import com.project.model.Order;
import com.project.model.Product;
import com.project.model.Ticket;
import com.project.model.User;
import com.project.model.enums.PaymentType;
import com.project.repository.OrderRepository;
import com.project.repository.ProductRepository;
import com.project.repository.TicketRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final TicketRepository ticketRepository;
    private OrderRepository orderRepository;

    public DbInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepository productRepository, TicketRepository ticketRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
        this.ticketRepository = ticketRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        initializeProducts();
        initializeTickets();
        initializeOrders();
    }

    private void initializeUsers() {
         if(userRepository.findByEmail("user@mail").isEmpty()) {
            userRepository.save(User.builder()
                    .email("user@mail")
                    .password(passwordEncoder.encode("Psw123"))
                    .name("User")
                    .surname("Surname")
                    .build());
        }
    }

    private void initializeProducts() {
        if (productRepository.count() == 0) {
            productRepository.saveAll(List.of(
                    Product.builder()
                            .name("name1")
                            .description("description1")
                            .date(LocalDate.of(2024, 8, 15))
                            .price(new BigDecimal("250.00"))
                            .build(),
                    Product.builder()
                            .name("name2")
                            .description("description2")
                            .date(LocalDate.of(2024, 8, 15))
                            .price(new BigDecimal("150.00"))
                            .build()
            ));
        }
    }
    private void initializeTickets() {
        if (ticketRepository.count() == 0) {
            ticketRepository.saveAll(List.of(
                    Ticket.builder()
                            .product(productRepository.findById(1).get())
                            .build(),
                    Ticket.builder()
                            .product(productRepository.findById(2).get())
                            .build()
            ));
        }
    }

    private void initializeOrders() {
        if (orderRepository.count() == 0) {
            orderRepository.saveAll(List.of(
                    Order.builder()
                            .ticket(ticketRepository.findById(1).get())
                            .qta(1)
                            .user(userRepository.findById(1).get())
                            .paymentType(PaymentType.NOT_DEFINED)
                            .totalPrice(new BigDecimal("250.00"))
                            .build()
            ));
        }
    }
}
