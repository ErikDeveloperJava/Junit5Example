package junit5.example.repository;

import junit5.example.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class UserRepository {

    private static final Logger LOGGER = LogManager.getLogger();

    private Map<Integer, User> userMap = new LinkedHashMap<>();
    private int incrId = 1;

    public void add(User user){
        user.setId(incrId++);
        userMap.put(user.getId(),user);
        LOGGER.info("{} successfully saved",user);
    }

    public Optional<User> getById(int userId){
        return Optional.ofNullable(userMap.get(userId));
    }

    public List<User> getAll(){
        return new ArrayList<>(userMap.values());
    }
}