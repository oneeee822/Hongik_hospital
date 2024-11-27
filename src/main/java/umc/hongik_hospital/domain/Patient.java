package umc.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.hongik_hospital.domain.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Column(nullable = false, length = 20)
    private String pname;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();
}

