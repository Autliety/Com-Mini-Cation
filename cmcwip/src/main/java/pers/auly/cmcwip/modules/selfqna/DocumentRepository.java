package pers.auly.cmcwip.modules.selfqna;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

interface DocumentRepository extends CrudRepository<Document, Integer> {
    
    List<Document> findAllByTitleContainsOrSummaryContains(String keyWord, String keyWord2);
    
}
