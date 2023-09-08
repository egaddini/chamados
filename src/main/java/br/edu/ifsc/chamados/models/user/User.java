package br.edu.ifsc.chamados.models.user;

import br.edu.ifsc.chamados.api.models.user.IUser;
import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.auth.Token;
import br.edu.ifsc.chamados.models.call.CallSector;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`USER`")
public class User implements UserDetails, IUser {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name",unique = true, nullable = false)
    private String firstname;
    @Column(name = "last_name", unique = true, nullable = false)
    private String lastname;
    @Column(unique = true, nullable = false)
    private Long phone;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private Boolean active = false;
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Token> tokens;
    @Column(name = "creation_dt", nullable = false)
    private LocalDateTime creationDT;
//    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<CallParticipants> callParticipants;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserSector> userSector;

    @Version
    private Long timestamp;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<CallSector> getUserSector() {
        return userSector.stream().map(e -> e.getSector()).collect(Collectors.toSet());
    }
}