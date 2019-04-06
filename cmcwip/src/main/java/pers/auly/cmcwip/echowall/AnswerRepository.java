package pers.auly.cmcwip.echowall;

import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;

interface AnswerRepository extends CrudRepository<Answer, Long> {
    
    Stream<Answer> findByUserIdOrderByDate(Long id);
    
    Stream<Answer> findByQuestionIdOrderByDate(Long id);
    
}
