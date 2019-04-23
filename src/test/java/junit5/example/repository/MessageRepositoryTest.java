package junit5.example.repository;

import junit5.example.model.Message;
import junit5.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("messageRepository test class")
class MessageRepositoryTest {

    private MessageRepository messageRepository;

    @BeforeEach
    public void beforeEach(){
        System.out.println("before each method invoked");
        messageRepository = new MessageRepository();
    }

    @Test
    @DisplayName("test getAllByFromId() method")
    public void getAllByFromIdTest(){
        User from = getTwoUsersForTest().get(0);
        User to = getTwoUsersForTest().get(1);
        List<Message> messageList = getSomeMessagesByToAndFrom(to, from);
        messageList.forEach(messageRepository::add);
        List<Message> messages = messageRepository.getAllByFromId(from.getId());
        assertAll(
                () -> assertEquals("message 01",messages.get(0).getMessage()),
                () -> assertEquals("message 02",messages.get(1).getMessage()),
                () -> assertEquals("message 03",messages.get(2).getMessage())
        );
    }

    //test cases of this class will run similar to test case of MessageRepositoryTest class
    @Nested
    @DisplayName("messageRepository add and delete test class")
    class MessageAddAndDeleteTest {

        @Test
        @DisplayName("test add() method")
        public void addTest(){
            List<User> userList = getTwoUsersForTest();
            User from = userList.get(0);
            User to = userList.get(1);
            Message message = Message
                    .builder()
                    .message("Hi")
                    .from(from)
                    .to(to)
                    .sendDate(new Date())
                    .build();
            messageRepository.add(message);
            assertEquals(1,message.getId(),"id of the message must be equal 1");
        }

        @Test
        @DisplayName("test deleteById() method")
        public void deleteByIdTest(){
            List<User> userList = getTwoUsersForTest();
            User from = userList.get(0);
            User to = userList.get(1);
            Message message = Message
                    .builder()
                    .message("Hi")
                    .from(from)
                    .to(to)
                    .sendDate(new Date())
                    .build();
            messageRepository.add(message);
            messageRepository.deleteById(message.getId());
            assertEquals(0,messageRepository.size(),"size must be equal 0");
        }
    }

    private static List<User> getTwoUsersForTest(){
        return  Arrays.asList(
                User
                        .builder()
                        .name("Tomas")
                        .surname("Walter")
                        .age(25)
                        .build(),
                User
                        .builder()
                        .name("Kane")
                        .surname("Smith")
                        .age(30)
                        .build());
    }

    private static List<Message> getSomeMessagesByToAndFrom(User to,User from){
        return  Arrays.asList(
                Message
                        .builder()
                        .sendDate(new Date())
                        .message("message 01")
                        .to(to)
                        .from(from)
                        .build(),
                Message
                        .builder()
                        .sendDate(new Date())
                        .message("message 02")
                        .to(to)
                        .from(from)
                        .build(),
                Message
                        .builder()
                        .sendDate(new Date())
                        .message("message 03")
                        .to(to)
                        .from(from)
                        .build()
        );
    }
}