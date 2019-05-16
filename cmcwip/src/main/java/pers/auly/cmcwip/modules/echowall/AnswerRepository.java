package pers.auly.cmcwip.modules.echowall;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import pers.auly.cmcwip.security.user.User;

interface AnswerRepository extends CrudRepository<Answer, Integer> {
    
    List<Answer> findAllByQuestionOrderByUpdateTime(Question question);
    
}
