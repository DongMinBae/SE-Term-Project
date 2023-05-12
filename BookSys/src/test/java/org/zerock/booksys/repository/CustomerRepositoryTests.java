package org.zerock.booksys.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.booksys.domain.Customer;

@SpringBootTest
@Log4j2
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void TestInsert(){
        Customer customer = Customer.builder()
                .cId("asdf")
                .name("홍길동")
                .phoneNumber("010-1234-5678")
                .build();
        Customer result = customerRepository.save(customer);

        log.info("cno : " + result.getCId());
    }


}
