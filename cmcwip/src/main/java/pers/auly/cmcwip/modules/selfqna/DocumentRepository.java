package pers.auly.cmcwip.modules.selfqna;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Integer> {
    
    List<Document> findAllByTitleContainingOrSummaryContaining(String keyWord, String keyWord2);
    
}
