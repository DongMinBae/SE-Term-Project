package org.zerock.booksys.dto;

import lombok.Data;

@Data
public class CustomerJoinDTO {
    private String cId;
    private String cPassword;
    private String name;
    private String phoneNumber;
}
