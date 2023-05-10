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


}
