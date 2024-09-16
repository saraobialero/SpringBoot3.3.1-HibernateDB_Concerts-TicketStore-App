package com.project.utils;

import com.project.model.*;
import com.project.model.enums.PaymentType;
import com.project.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DbInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReplyRepository replyRepository;
    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final SellerRepository sellerRepository;

    public DbInitializer(UserRepository userRepository,
                         PasswordEncoder passwordEncoder,
                         ReplyRepository replyRepository,
                         TicketRepository ticketRepository,
                         OrderRepository orderRepository,
                         SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.replyRepository = replyRepository;
        this.ticketRepository = ticketRepository;
        this.orderRepository = orderRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        initializeReplies();
        initializeSellers();
        initializeTickets();
        initializeOrders();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            List<User> users = new ArrayList<>();
            users.add(User.builder()
                    .email("user@mail.com")
                    .password(passwordEncoder.encode("Psw123"))
                    .name("John")
                    .surname("Doe")
                    .build());
            users.add(User.builder()
                    .email("jane@mail.com")
                    .password(passwordEncoder.encode("Psw456"))
                    .name("Jane")
                    .surname("Smith")
                    .build());
            users.add(User.builder()
                    .email("bob@mail.com")
                    .password(passwordEncoder.encode("Psw789"))
                    .name("Bob")
                    .surname("Johnson")
                    .build());
            userRepository.saveAll(users);
        }
    }

    private void initializeReplies() {
        if (replyRepository.count() == 0) {
            List<Reply> replies = new ArrayList<>();
            LocalDate startDate = LocalDate.of(2024, 7, 10);
            Random random = new Random();

            for (int i = 0; i < 20; i++) {
                LocalDate replyDate = startDate.plusDays(random.nextInt(365));
                replies.add(Reply.builder()
                        .replyDate(replyDate)
                        .build());
            }

            replyRepository.saveAll(replies);
        }
    }

    private void initializeSellers() {
        if (sellerRepository.count() == 0) {
            List<Seller> sellers = new ArrayList<>();
            sellers.add(Seller.builder().companyName("MusicMasters").build());
            sellers.add(Seller.builder().companyName("ConcertKings").build());
            sellers.add(Seller.builder().companyName("ShowTimeSellers").build());
            sellers.add(Seller.builder().companyName("TicketPros").build());
            sellerRepository.saveAll(sellers);
        }
    }

    @Transactional
    private void initializeTickets() {
        if (ticketRepository.count() == 0) {
            List<Seller> sellers = sellerRepository.findAll();
            List<Reply> replies = replyRepository.findAll();
            Random random = new Random();

            List<Ticket> tickets = new ArrayList<>();
            String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
            String[] locations = {"Madison Square Garden", "Staples Center", "United Center", "Toyota Center", "Talking Stick Resort Arena"};
            String[] bands = {"The Rolling Stones", "Foo Fighters", "Coldplay", "Imagine Dragons", "Maroon 5"};

            // Create a ticket for each reply
            for (Reply reply : replies) {
                tickets.add(Ticket.builder()
                        .city(cities[random.nextInt(cities.length)])
                        .location(locations[random.nextInt(locations.length)])
                        .reply(reply)
                        .band(bands[random.nextInt(bands.length)])
                        .price(BigDecimal.valueOf(50 + random.nextInt(150)))
                        .qta(50 + random.nextInt(200))
                        .seller(sellers.get(random.nextInt(sellers.size())))
                        .build());
            }

            ticketRepository.saveAll(tickets);
        }
    }

    @Transactional
    private void initializeOrders() {
        if (orderRepository.count() == 0) {
            List<User> users = userRepository.findAll();
            List<Ticket> tickets = ticketRepository.findAll();
            Random random = new Random();

            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                orders.add(Order.builder()
                        .qta(1 + random.nextInt(5))
                        .user(users.get(random.nextInt(users.size())))
                        .ticket(tickets.get(random.nextInt(tickets.size())))
                        .paymentType(PaymentType.values()[random.nextInt(PaymentType.values().length)])
                        .build());
            }

            orderRepository.saveAll(orders);
        }
    }
}