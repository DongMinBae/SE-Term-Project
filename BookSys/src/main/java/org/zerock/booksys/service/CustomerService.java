package org.zerock.booksys.service;

import org.zerock.booksys.dto.CustomerJoinDTO;

public interface CustomerService {

    static class MidExistException extends Exception{

    }
    void join(CustomerJoinDTO customerJoinDTO) throws MidExistException;
}
