package umc.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String hname;

    @Column(nullable = false, length = 20)
    private String address;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Department> departMentList = new ArrayList<>();
}
