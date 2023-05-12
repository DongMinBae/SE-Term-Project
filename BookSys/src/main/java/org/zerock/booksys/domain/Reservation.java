package org.zerock.booksys.domain;

import lombok.*;
import org.zerock.booksys.dto.ArrivalTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @Column(name = "rno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno; //번호 순서대로 부여

    @Column(name = "number")
    private int number; // 인원수(selectday에서 받아올 인자)

    @Column(name = "selectedDate")
    private Date selectedDate; // 선택한 날짜(selectday에서 받아올 인자)

    @Column(name = "tableno")
    private int tableNumber;

    @Column(name = "arrivaltime")
    @Enumerated(value = EnumType.ORDINAL)
    private ArrivalTime arrivalTime; //도착시간

    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer customer; //다대일 매핑
}
