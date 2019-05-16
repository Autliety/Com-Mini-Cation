package pers.auly.cmcwip.modules.selfqna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    
    private SelfQnaService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    
    Collection<Document> getAll() {
        Collection<Document> c = new ArrayList<>();
        documentRepository.findAll().forEach(c::add);
        return c;
    }
    
    Collection<Document> search(String[] keyWords) {
        Map<Integer, Document> resultMap = new LinkedHashMap<>();
        for (String keyWord : keyWords) {
            List<Document> query = documentRepository
                .findAllByTitleContainsOrSummaryContains(keyWord, keyWord);
            query.forEach(d -> resultMap.put(d.getId(), d));
        }
        return resultMap.values();
    }
    
    Resource getDocumentResource(int id) {
        return documentRepository.findById(id)
            .map(Document::getFileName)
            .map((name) -> {
                try {
                    Path path = Paths.get("testdocs").resolve(name);
                    return new ByteArrayResource(Files.readAllBytes(path));
                } catch (IOException e) {
                    String msg = "Document file reading error. ";
                    throw new CmcWebException(msg, e).reason(msg);
                }
            })
            .orElseThrow(NotFoundException::new);
    }
    
    String getDocumentFileName(int id) {
        return documentRepository.findById(id)
            .map(Document::getFileName)
            .orElseThrow(NotFoundException::new);
    }
    
}
