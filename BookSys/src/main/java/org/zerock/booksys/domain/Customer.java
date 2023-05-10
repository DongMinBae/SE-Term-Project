package org.zerock.booksys.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Customer {

    @Id
    @Column(name = "cid")
    private String cId;

    @Column(name = "cpassword")
    private String cPassword;

    @Column(name = "name")
    private String name; //이름

    @Column(name = "phonenumber")
    private String phoneNumber; // 전화번호

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<CustomerRole> roleSet = new HashSet<>();

    @OneToMany(mappedBy = "customer",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true
            )
    private Set<Reservation> reservationSet = new HashSet<>(); // 예약 다대일 매여

}
