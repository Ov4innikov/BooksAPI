package ru.booksapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booksapi.entities.Author;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.AuthorService;
import ru.booksapi.repostitories.AuthorsRepository;
import ru.booksapi.repostitories.BooksRepository;

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
    private BooksRepository booksRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public Map<Integer,Map<String,String>> getAuthorById(Integer id) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        Author author = null;
        if(authorsRepository.findById(id).isPresent()){
            author = authorsRepository.findById(id).get();
            resultMap.put(id, convertAuthorToMap(author));
        }else {
            throw new ServiceExeption("Author not found");
        }

        return resultMap;
    }

    @Override
    public Map<Integer,Map<String,String>> getAllAuthors() throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        for(Author item:authorsRepository.findAll()) {
            resultMap.put(item.getId(), convertAuthorToMap(item));
            logger.debug("Author id is " + item.getId());
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        if (resultMap.size() == 0) throw new ServiceExeption("Empty authors map");
        return resultMap;
    }

    @Override
    public void updateAuthorById(Map<String, String> updatingAuthor) throws ServiceExeption {
        Author author = null;
        if(authorsRepository.findById(Integer.valueOf(updatingAuthor.get("id"))).isPresent()) {
            author = authorsRepository.findById(Integer.valueOf(updatingAuthor.get("id"))).get();
        }else {
            throw new ServiceExeption("Author not found");
        }
        if(updatingAuthor.get("dateOfBirthDay")!=null) {
            author.setDateOfBirthDay(LocalDate.parse(updatingAuthor.get("dateOfBirthDay")));
        }
        if(updatingAuthor.get("firstName")!=null) {
            author.setFirstName(updatingAuthor.get("firstName"));
        }
        if(updatingAuthor.get("lastName")!=null) {
            author.setLastName(updatingAuthor.get("lastName"));
        }
        authorsRepository.save(author);
    }

    @Override
    public void putNewAuthor(Map<String, String> newAuthor) throws ServiceExeption {
        Author author = new Author();

        author.setDateOfBirthDay(LocalDate.parse(newAuthor.get("dateOfBirthDay")));
        author.setFirstName(newAuthor.get("firstName"));
        author.setLastName(newAuthor.get("lastName"));
        authorsRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Map<String, String> deletedAuthor) throws ServiceExeption {
        if(deletedAuthor.isEmpty()) throw new ServiceExeption("Empty patameters");
        if(deletedAuthor.get("id").isEmpty()) throw new ServiceExeption("Empty patameters");
        String id = deletedAuthor.get("id");
        for(Book item:booksRepository.findAll()) {
            if(item.getId()==Integer.valueOf(id)) throw new ServiceExeption("Some book is link with this author!");
        }
        if(authorsRepository.findById(Integer.valueOf(deletedAuthor.get("id"))).isPresent()) {
            authorsRepository.deleteById(Integer.valueOf(id));
        }else {
            throw new ServiceExeption("Author not found");
        }


    }

    private Map<String,String> convertAuthorToMap(Author author){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("firstName",author.getFirstName());
        resultMap.put("lastName",author.getLastName());
        resultMap.put("dateOfBirthDay",author.getDateOfBirthDay().toString());
        return resultMap;
    }
}
