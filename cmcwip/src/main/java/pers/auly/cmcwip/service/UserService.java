package pers.auly.cmcwip.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.auly.cmcwip.entity.User;
import pers.auly.cmcwip.mapper.UserMapper;

@Slf4j
@Service
public class UserService {
    
    private final UserMapper userMapper;
    
    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    public User getUserByName(String name) {
        User user = userMapper.selectName(name);
        if (user == null) {
            // freq.DEFAULT_VALUE: 1
            user = User.builder().name(name).freq(1).build();
            userMapper.insertEntity(user);
        } else {
            userMapper.updateFreq(user.addFreq());
        }
        log.warn(user.toString());
        return user;
    }
}
