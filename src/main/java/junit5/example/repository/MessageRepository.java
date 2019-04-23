package junit5.example.repository;

import junit5.example.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageRepository {

    private static final Logger LOGGER = LogManager.getLogger();

    private Map<Integer, Message> messageMap = new LinkedHashMap<>();
    private int incrMessage = 1;

    public void add(Message message){
        message.setId(incrMessage++);
        messageMap.put(message.getId(),message);
        LOGGER.info("{} successfully saved",message);
    }

    public List<Message> getAllByFromId(int fromId){
        return messageMap
                .values()
                .stream()
                .filter(message -> message.getFrom().getId() == fromId)
                .collect(Collectors.toList());

    }

    public void deleteById(int messageId){
        Message message = messageMap.remove(messageId);
        incrMessage--;
        LOGGER.info("{} successfully deleted",message);
    }

    public int size(){
        return incrMessage - 1;
    }
}