package com.example.toy.restfull.services;

import com.example.toy.database.entity.UserEntity;
import com.example.toy.database.repository.UserRepository;
import com.example.toy.domain.ResultCode;
import com.example.toy.domain.ResultVo;
import com.example.toy.domain.user.UserPostReqVo;
import com.example.toy.restfull.controllers.exception.BadRequestException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResultVo registerUser(UserPostReqVo userVo) throws BadRequestException {
        Optional<UserEntity> user = userRepository.findById(userVo.getEmail());

        if (user.isPresent()) {
            throw new BadRequestException(ResultCode.OK_ALREADY.getDescription());
        }

        UserEntity createUser = UserEntity.builder()
                                          .email(userVo.getEmail())
                                          .name(userVo.getName())
                                          .subscriptionType(userVo.getSubscriptionType())
//                                          .password(passwordEncoder.encode(userVo.getPassword()))
                                          .build();

        userRepository.save(createUser);

        return new ResultVo(ResultCode.OK, createUser);
    }
}
