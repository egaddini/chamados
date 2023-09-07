package br.edu.ifsc.chamados.models.user;

import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.auth.Token;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGetAuthorities() {
        User user = new User();
        user.setRole(Role.ADMIN);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(Role.ADMIN.name())));
    }

    @Test
    public void testIsAccountNonExpired() {
        User user = new User();

        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        User user = new User();

        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        User user = new User();
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        User user = new User();
        assertTrue(user.isEnabled());
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User();

        user.setId(1);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPhone(1234567890L);
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setActive(true);

        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals(1234567890L, user.getPhone());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(Role.USER, user.getRole());
        assertTrue(user.getActive());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setId(1);
        user2.setEmail("john.doe@example.com");

        User user3 = new User();
        user3.setId(2);
        user3.setEmail("jane.doe@example.com");

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    public void testTokensList() {
        User user = new User();
        Token token1 = new Token();
        Token token2 = new Token();
        Token token3 = new Token();
        user.getTokens().add(token1);
        user.getTokens().add(token2);
        user.getTokens().add(token3);

        assertEquals(3, user.getTokens().size());
        assertTrue(user.getTokens().contains(token1));
        assertTrue(user.getTokens().contains(token2));
        assertTrue(user.getTokens().contains(token3));
    }

    @Test
    public void testTimestamp() {
        User user = new User();
        user.setTimestamp(123456L);

        assertEquals(123456L, user.getTimestamp());
    }

}


