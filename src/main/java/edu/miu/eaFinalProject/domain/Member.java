package edu.miu.eaFinalProject.domain;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private String email;
    private String password;

    @OneToMany
    @JoinColumn(name="member_id")
    private List<Badge> badges;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "member_role")
    private List<Role> roles;
}
