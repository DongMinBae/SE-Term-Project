package org.zerock.booksys.service;


import org.zerock.booksys.dto.CustomerDTO;

public interface CustomerService {

    Long register(CustomerDTO customerDTO);

    void modify(CustomerDTO customerDTO);

    void remove(Long cno);

    CustomerDTO readOne(Long cno);
}
