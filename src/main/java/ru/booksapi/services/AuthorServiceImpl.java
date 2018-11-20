package ru.booksapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booksapi.entities.Author;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.AuthorService;
import ru.booksapi.repostitories.AuthorsRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Service of books authors
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public Map<Integer,Map<String,String>> getAuthorById(Long id) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        Author author = null;
        if(authorsRepository.findById(id).isPresent()){
            author = authorsRepository.findById(id).get();
            resultMap.put(1, convertAuthorToMap(author));
        }else {
            throw new ServiceExeption("Author not found");
        }

        return resultMap;
    }

    @Override
    public Map<Integer,Map<String,String>> getAllAuthors() throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        int i = 1;
        for(Author item:authorsRepository.findAll()) {
            resultMap.put(i++, convertAuthorToMap(item));
            logger.debug("Author id is " + item.getId());
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        if (resultMap.size() == 0) throw new ServiceExeption("Empty authors map");
        return resultMap;
    }

    @Override
    public void updateAuthorById(Map<String, String> updatingAuthor) throws ServiceExeption {
        Author author = null;
        if(authorsRepository.findById(Long.valueOf(updatingAuthor.get("id"))).isPresent()) {
            author = authorsRepository.findById(Long.valueOf(updatingAuthor.get("id"))).get();
        }else {
            throw new ServiceExeption("Author not found");
        }
        author.setDateOfBirthDay(LocalDate.parse(updatingAuthor.get("name")));
        author.setFirstName(updatingAuthor.get("name"));
        author.setLastName(updatingAuthor.get("name"));
    }

    @Override
    public void putNewAuthor(Map<String, String> newAuthor) throws ServiceExeption {
        Author author = new Author();
        if(authorsRepository.findById(Long.valueOf(newAuthor.get("authorId"))).isPresent()) {
            throw new ServiceExeption("Author was created");
        }
        author.setDateOfBirthDay(LocalDate.parse(newAuthor.get("name")));
        author.setFirstName(newAuthor.get("name"));
        author.setLastName(newAuthor.get("name"));
    }

    @Override
    public void deleteAuthorById(Map<String, String> map) throws ServiceExeption {
        if(map.isEmpty()) throw new ServiceExeption("Empty patameters");
        if(map.get("id")==null) throw new ServiceExeption("Empty patameters");
        String id = map.get("id");
        authorsRepository.deleteById(Long.valueOf(id));
    }

    private Map<String,String> convertAuthorToMap(Author author){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("firstName",author.getFirstName());
        resultMap.put("lastName",author.getLastName());
        resultMap.put("dateOfBirthDay",author.getDateOfBirthDay().toString());
        return resultMap;
    }
}
