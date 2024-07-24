package com.abdul.airlinemanager.player;

import com.abdul.airlinemanager.airport.Airport;
import com.abdul.airlinemanager.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Player implements UserDetails, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;

    @Column(nullable = false)
    private String airlineName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "hub_airport_id", nullable = false)
    private Airport airport;

    @Column(nullable = false)
    private Double balance;

    // the two fields below are for email verification which will not be
    // implemented yet
//    private boolean accountLocked;
//    private boolean enabled;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

//    @Override
//    public boolean isAccountNonLocked() {
//        return !accountLocked;
//    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
