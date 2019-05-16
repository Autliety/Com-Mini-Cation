package pers.auly.cmcwip.modules.echowall;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import pers.auly.cmcwip.utils.exceptions.NotFoundException;
import pers.auly.cmcwip.utils.SessionUtils;

@Service
class EchoWallService {
    
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    
    public EchoWallService(QuestionRepository questionRepository,
        AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }
    
    List<Question> getMyQuestions() {
        return questionRepository.findAllByUserOrderByUpdateTime(SessionUtils.current());
    }
    
    List<Question> getAllQuestions() {
        return questionRepository.findAllByOrderByUpdateTime();
    }
    
    Question getQuestion(int id) {
        return questionRepository.findById(id)
            .orElseThrow(NotFoundException::new);
    }
    
    List<Answer> getAnswersForQuestion(Question q) {
        return answerRepository.findAllByQuestionOrderByUpdateTime(q);
    }
    
    void createQuestion(String title, String context, Set<String> tags) {
        Question question = Question.builder().title(title).context(context).tags(tags).build();
        questionRepository.save(question);
    }
    
    void answerQuestion(Question q, String context) {
        Answer answer = Answer.builder().question(q).context(context).build();
        answerRepository.save(answer);
    }
    
}
