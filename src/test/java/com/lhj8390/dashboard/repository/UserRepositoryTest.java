package com.lhj8390.dashboard.repository;

import com.lhj8390.dashboard.model.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 생성")
    public void create_user() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("test1234!");

        User user = User.builder()
                .username("test2")
                .email("email@email.com")
                .password(password)
                .build();
        User result = userRepository.save(user);

        assertThat(result).isNotNull();
        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getPassword(), user.getPassword());
    }
}
