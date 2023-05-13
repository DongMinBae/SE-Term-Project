package org.zerock.booksys.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.domain.CustomerRole;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.CustomerJoinDTO;
import org.zerock.booksys.repository.CustomerRepository;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(CustomerJoinDTO customerJoinDTO) throws MidExistException {
        String mid = customerJoinDTO.getCId();

        boolean exist = customerRepository.existsById(mid);

        if(exist){
            throw new MidExistException();
        }
        Customer customer = modelMapper.map(customerJoinDTO, Customer.class);
        customer.changePassword(passwordEncoder.encode(customerJoinDTO.getCPassword()));
        customer.addRole(CustomerRole.USER);

        log.info("==========================");
        log.info(customer);
        log.info(customer.getRoleSet());

        customerRepository.save(customer);
    }
}
