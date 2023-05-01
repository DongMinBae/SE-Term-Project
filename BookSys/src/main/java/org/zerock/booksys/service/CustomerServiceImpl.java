package org.zerock.booksys.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.repository.CustomerRepository;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    @Override
    public Long register(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);

        Long cno = customerRepository.save(customer).getCno();

        log.info("Insert cno : " + cno);

        return cno;
    }

    @Override
    public void modify(CustomerDTO customerDTO) {
        Optional<Customer> result = customerRepository.findById(customerDTO.getCno());

        Customer customer = result.orElseThrow();

    }

    @Override
    public void remove(Long cno) {
        customerRepository.deleteById(cno);
    }

    @Override
    public CustomerDTO readOne(Long cno) {
        Optional<Customer> result = customerRepository.findById(cno);

        Customer customer = result.orElseThrow();

        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        return customerDTO;
    }
}
