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
public class Customer {

    @Id
    @Column(name = "cno")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 순서대로 부여
    private Long cno;

    @Column(name = "name")
    private String name; //이름

    @Column(name = "phonenumber")
    private String phoneNumber; // 전화번호

    @OneToMany(mappedBy = "customer",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true
            )
    private Set<Reservation> reservationSet = new HashSet<>(); // 예약 다대일 매여

}
