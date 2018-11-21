package ru.booksapi.interfaces;

import ru.booksapi.exceptions.ServiceExeption;
import java.util.Map;

/**
 * Interface for service of desires
 *
 * @author Eugene Ovchinnikov
 */
public interface DesireService {

    public Map<Integer,Map<String,String>> getDesireById(Integer id) throws ServiceExeption;
    public Map<Integer,Map<String,String>> getDesiresByUserId(String id) throws ServiceExeption;
    public Map<Integer,Map<String,String>> getAllDesires() throws ServiceExeption;
    public void updateDesireById(Map<String,String> map) throws ServiceExeption;
    public void putNewDesire(Map<String,String> map)  throws ServiceExeption;
    public void deleteDesireById(Map<String,String> map) throws ServiceExeption;
}
