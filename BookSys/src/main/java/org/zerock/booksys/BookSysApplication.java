package org.zerock.booksys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookSysApplication.class, args);
    }

}
