package com.example.project1.user;

import com.example.project1.emailVerify.EmailVerifyService;
import com.example.project1.user.model.User;
import com.example.project1.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerifyService emailVerifyService;

    @Transactional
    public void signup(UserDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = userRepository.save(dto.toEntity(encodedPassword, "USER"));

        emailVerifyService.signup(user.getIdx(), user.getEmail());

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username);

        if (result.isPresent()) {
            // 7번 로직
            User user = result.get();
            return user;
        }

        return null;
    }

    @Transactional
    public void verify(String uuid) {
        User user = emailVerifyService.verify(uuid);
        if(user != null) {
            user.verify();
            userRepository.save(user);
        }
    }

}
