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


    @Transactional
    public void verify(String uuid) {
        User user = emailVerifyService.verify(uuid);
        if(user != null) {
            user.verify();
            userRepository.save(user);
        }
    }

    @Transactional(readOnly = true)
    public UserDto.UserResponse read(Long idx) {
        Optional<User> result = userRepository.findById(idx);

        if(result.isPresent()) {
            User user = result.get();

            return UserDto.UserResponse.from(user);
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
