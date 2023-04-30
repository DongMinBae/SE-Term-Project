package org.zerock.booksys.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @Column(name = "rno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno; //번호 순서대로 부

    @Column(name = "arrivaltime")
    private LocalDateTime arrivalTime; //도착시간
    @ManyToOne
    private Customer customer; //다대일 매핑
}
