package umc.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.hongik_hospital.domain.enums.Rstatus;

import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INCOMPLETE'")
    private Rstatus rstatus;

    private LocalTime createdAt;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patien_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;



}
