package com.project.utils;

import com.project.model.Product;
import com.project.model.User;
import com.project.repository.ProductRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeConcerts();
        initializeRolesAndUsers();
    }

    private void initializeRolesAndUsers() {
         if(userRepository.findByEmail("user@mail").isEmpty()) {
            userRepository.save(User.builder()
                    .email("user@mail")
                    .password(passwordEncoder.encode("prova1234"))
                    .name("User")
                    .surname("Surname")
                    .build());
        }
    }

    private void initializeConcerts() {
        if (productRepository.count() == 0) {
            productRepository.saveAll(List.of(
                    Product.builder()
                            .city("New York")
                            .band("The Rolling Stones")
                            .reply("Madison Square Garden")
                            .availablePlace(1000)
                            .date(LocalDate.of(2024, 8, 15))
                            .price(new BigDecimal("150.00"))
                            .build(),
                    Product.builder()
                            .city("Los Angeles")
                            .band("Coldplay")
                            .reply("SoFi Stadium")
                            .availablePlace(1500)
                            .date(LocalDate.of(2024, 9, 20))
                            .price(new BigDecimal("120.00"))
                            .build(),
                    Product.builder()
                            .city("Chicago")
                            .band("Imagine Dragons")
                            .reply("United Center")
                            .availablePlace(1200)
                            .date(LocalDate.of(2024, 7, 25))
                            .price(new BigDecimal("110.00"))
                            .build(),
                    Product.builder()
                            .city("Houston")
                            .band("Metallica")
                            .reply("Toyota Center")
                            .availablePlace(1300)
                            .date(LocalDate.of(2024, 10, 5))
                            .price(new BigDecimal("140.00"))
                            .build(),
                    Product.builder()
                            .city("Phoenix")
                            .band("U2")
                            .reply("State Farm Stadium")
                            .availablePlace(900)
                            .date(LocalDate.of(2024, 11, 15))
                            .price(new BigDecimal("160.00"))
                            .build(),
                    Product.builder()
                            .city("Philadelphia")
                            .band("Maroon 5")
                            .reply("Wells Fargo Center")
                            .availablePlace(1100)
                            .date(LocalDate.of(2024, 10, 18))
                            .price(new BigDecimal("125.00"))
                            .build(),
                    Product.builder()
                            .city("San Antonio")
                            .band("Linkin Park")
                            .reply("AT&T Center")
                            .availablePlace(1400)
                            .date(LocalDate.of(2024, 12, 2))
                            .price(new BigDecimal("135.00"))
                            .build(),
                    Product.builder()
                            .city("San Diego")
                            .band("The Beatles Tribute")
                            .reply("Pechanga Arena")
                            .availablePlace(800)
                            .date(LocalDate.of(2024, 8, 21))
                            .price(new BigDecimal("100.00"))
                            .build(),
                    Product.builder()
                            .city("Dallas")
                            .band("Queen")
                            .reply("American Airlines Center")
                            .availablePlace(1500)
                            .date(LocalDate.of(2024, 8, 17))
                            .price(new BigDecimal("150.00"))
                            .build(),
                    Product.builder()
                            .city("San Jose")
                            .band("The Who")
                            .reply("SAP Center")
                            .availablePlace(700)
                            .date(LocalDate.of(2024, 8, 25))
                            .price(new BigDecimal("130.00"))
                            .build(),
                    Product.builder()
                            .city("Austin")
                            .band("Red Hot Chili Peppers")
                            .reply("Frank Erwin Center")
                            .availablePlace(1200)
                            .date(LocalDate.of(2024, 7, 14))
                            .price(new BigDecimal("140.00"))
                            .build(),
                    Product.builder()
                            .city("Jacksonville")
                            .band("Foo Fighters")
                            .reply("VyStar Veterans Memorial Arena")
                            .availablePlace(900)
                            .date(LocalDate.of(2024, 10, 29))
                            .price(new BigDecimal("120.00"))
                            .build(),
                    Product.builder()
                            .city("Columbus")
                            .band("Guns N' Roses")
                            .reply("Nationwide Arena")
                            .availablePlace(800)
                            .date(LocalDate.of(2024, 12, 9))
                            .price(new BigDecimal("135.00"))
                            .build(),
                    Product.builder()
                            .city("Charlotte")
                            .band("Bon Jovi")
                            .reply("Spectrum Center")
                            .availablePlace(1000)
                            .date(LocalDate.of(2024, 12, 23))
                            .price(new BigDecimal("125.00"))
                            .build(),
                    Product.builder()
                            .city("Fort Worth")
                            .band("Aerosmith")
                            .reply("Dickies Arena")
                            .availablePlace(1400)
                            .date(LocalDate.of(2024, 7, 10))
                            .price(new BigDecimal("145.00"))
                            .build(),
                    Product.builder()
                            .city("Indianapolis")
                            .band("The Eagles")
                            .reply("Gainbridge Fieldhouse")
                            .availablePlace(1200)
                            .date(LocalDate.of(2024, 8, 2))
                            .price(new BigDecimal("135.00"))
                            .build(),
                    Product.builder()
                            .city("Seattle")
                            .band("Pearl Jam")
                            .reply("Climate Pledge Arena")
                            .availablePlace(1100)
                            .date(LocalDate.of(2024, 9, 13))
                            .price(new BigDecimal("140.00"))
                            .build(),
                    Product.builder()
                            .city("Denver")
                            .band("Fleetwood Mac")
                            .reply("Ball Arena")
                            .availablePlace(1300)
                            .date(LocalDate.of(2024, 10, 20))
                            .price(new BigDecimal("150.00"))
                            .build(),
                    Product.builder()
                            .city("Washington")
                            .band("Journey")
                            .reply("Capital One Arena")
                            .availablePlace(1000)
                            .date(LocalDate.of(2024, 11, 18))
                            .price(new BigDecimal("130.00"))
                            .build(),
                    Product.builder()
                            .city("Boston")
                            .band("The Beach Boys")
                            .reply("TD Garden")
                            .availablePlace(900)
                            .date(LocalDate.of(2024, 12, 5))
                            .price(new BigDecimal("115.00"))
                            .build(),
                    Product.builder()
                            .city("Nashville")
                            .band("Nirvana Tribute")
                            .reply("Bridgestone Arena")
                            .availablePlace(800)
                            .date(LocalDate.of(2024, 10, 12))
                            .price(new BigDecimal("110.00"))
                            .build(),
                    Product.builder()
                            .city("Las Vegas")
                            .band("Elton John")
                            .reply("T-Mobile Arena")
                            .availablePlace(1600)
                            .date(LocalDate.of(2024, 12, 28))
                            .price(new BigDecimal("180.00"))
                            .build(),
                    Product.builder()
                            .city("Portland")
                            .band("Green Day")
                            .reply("Moda Center")
                            .availablePlace(950)
                            .date(LocalDate.of(2024, 12, 17))
                            .price(new BigDecimal("115.00"))
                            .build(),
                    Product.builder()
                            .city("Oklahoma City")
                            .band("Twenty One Pilots")
                            .reply("Paycom Center")
                            .availablePlace(1050)
                            .date(LocalDate.of(2024, 11, 22))
                            .price(new BigDecimal("105.00"))
                            .build(),
                    Product.builder()
                            .city("Tucson")
                            .band("The Killers")
                            .reply("Tucson Arena")
                            .availablePlace(850)
                            .date(LocalDate.of(2024, 11, 30))
                            .price(new BigDecimal("110.00"))
                            .build(),
                    Product.builder()
                            .city("Fresno")
                            .band("Panic! At The Disco")
                            .reply("Save Mart Center")
                            .availablePlace(1150)
                            .date(LocalDate.of(2024, 9, 8))
                            .price(new BigDecimal("100.00"))
                            .build(),
                    Product.builder()
                            .city("Sacramento")
                            .band("Muse")
                            .reply("Golden 1 Center")
                            .availablePlace(1250)
                            .date(LocalDate.of(2024, 7, 14))
                            .price(new BigDecimal("130.00"))
                            .build(),
                    Product.builder()
                            .city("Long Beach")
                            .band("Blink-182")
                            .reply("Long Beach Arena")
                            .availablePlace(750)
                            .date(LocalDate.of(2024, 8, 19))
                            .price(new BigDecimal("95.00"))
                            .build(),
                    Product.builder()
                            .city("Kansas City")
                            .band("Weezer")
                            .reply("T-Mobile Center")
                            .availablePlace(1100)
                            .date(LocalDate.of(2024, 9, 26))
                            .price(new BigDecimal("100.00"))
                            .build()
            ));
        }
    }
}
