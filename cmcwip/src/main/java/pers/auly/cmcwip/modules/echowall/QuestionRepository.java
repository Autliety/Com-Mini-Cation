package pers.auly.cmcwip.modules.echowall;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import pers.auly.cmcwip.security.user.User;

interface QuestionRepository extends CrudRepository<Question, Integer> {
    
    List<Question> findAllByOrderByUpdateTime();
    
    List<Question> findAllByUserOrderByUpdateTime(User user);
    
}
