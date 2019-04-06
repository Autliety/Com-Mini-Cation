package pers.auly.cmcwip.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class UserService {
    
    private UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    User login(String signature, String username) {
        User user =  userRepository.findBySignature(signature)
            .map(u -> u.setNickName(username))
            .map(u -> u.setFreq(u.getFreq() + 1))
            .orElse(User.builder().signature(signature).nickName(username).build());
        userRepository.save(user);
        return user;
    }
}
