package org.zerock.booksys.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "arrivaltime")
    private String arrivalTime; //도착시간(이 부분이 동혁님이 modify/delete에서 사용할 컬럼인거 같은데 이거를 지금은 String으로 적어놨는데 편의에 맞게 고쳐서 사용해 주세요.

    @ManyToOne
    private Customer customer; //다대일 매핑
}
