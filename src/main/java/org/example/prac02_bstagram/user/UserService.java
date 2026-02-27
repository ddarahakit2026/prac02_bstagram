package org.example.prac02_bstagram.user;

import org.example.prac02_bstagram.common.exception.BaseException;
import org.example.prac02_bstagram.user.model.AuthUserDetails;
import org.example.prac02_bstagram.user.model.User;
import org.example.prac02_bstagram.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.example.prac02_bstagram.common.model.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto.SignupRes signup(UserDto.SignupReq dto) {

        // 이메일 중복 확인
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw BaseException.from(SIGNUP_DUPLICATE_EMAIL);
        }


        User user = dto.toEntity();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);


        return UserDto.SignupRes.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> BaseException.from(LOGIN_INVALID_USERINFO)
        );

        return AuthUserDetails.from(user);
    }

}
