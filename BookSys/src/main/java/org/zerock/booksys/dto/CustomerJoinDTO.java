package org.zerock.booksys.dto;

import lombok.Data;
import org.zerock.booksys.domain.Customer;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class CustomerJoinDTO {
    private String cId;

    private String cPassword;

    private String name;

    private String phoneNumber;

    private boolean social = false;

    public Customer toDomain(){
        Customer customer = Customer.builder()
                .cId(this.cId)
                .cPassword(this.cPassword)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .build();

        return customer;
    }

}
