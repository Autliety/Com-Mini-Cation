package pers.auly.cmcwip.modules.selfqna;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pers.auly.cmcwip.utils.exceptions.CmcWebException;
import pers.auly.cmcwip.utils.exceptions.NotFoundException;

@Service
class SelfQnaService {
    
    private final DocumentRepository documentRepository;
    
    public SelfQnaService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    
    Collection<Document> search(String[] keyWords) {
        Map<Integer, Document> resultMap = new LinkedHashMap<>();
        for (String keyWord : keyWords) {
            List<Document> query = documentRepository
                .findAllByTitleContainingOrSummaryContaining(keyWord, keyWord);
            query.forEach(d -> resultMap.put(d.getId(), d));
        }
        return resultMap.values();
    }
    
    Resource getDocumentResource(int id) {
        return documentRepository.findById(id)
            .map(Document::getPath)
            .map((path) -> {
                try {
                    return new ByteArrayResource(Files.readAllBytes(path));
                } catch (IOException e) {
                    throw new CmcWebException("Document file reading error. ", e);
                }
            })
            .orElseThrow(NotFoundException::new);
    }
    
}
