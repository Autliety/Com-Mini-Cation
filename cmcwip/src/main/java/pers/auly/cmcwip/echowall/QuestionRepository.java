package pers.auly.cmcwip.echowall;

import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;

interface QuestionRepository extends CrudRepository<Question, Long> {
    
    Stream<Question> findByUserIdOrderByDate(Long id);
    
}
