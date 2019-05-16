package pers.auly.cmcwip.modules.echowall;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

interface AnswerRepository extends CrudRepository<Answer, Integer> {
    
    List<Answer> findAllByQuestionOrderByUpdateTime(Question question);
    
}
