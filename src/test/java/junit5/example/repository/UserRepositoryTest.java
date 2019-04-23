package junit5.example.repository;

import junit5.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

//display name annotation print to console name which is written in the annotation
@DisplayName("UserRepository test class")
class UserRepositoryTest {

    private UserRepository userRepository;

    //@Before annotation changed to @BeforeEach in Junit5 but functionality looks the same
    //it will invoked for each testCase in this class
    @BeforeEach
    public void beforeEach() {
        userRepository = new UserRepository();
    }

    /*
    through @ParameterizedTest and @MethodSource("getSingleUser") annotations we can receive User
    in this addTest() method by Parameter (User user) and that user will be getting  from
    getSingleUser() method
    */
    @ParameterizedTest
    //values of @MethodSource annotation must be static and it must return Iterable Object
    @MethodSource({"getSingleUser"})
    @DisplayName("test add() method")
    public void addTest(User user) {
        userRepository.add(user);
        assertEquals(user.getId(), 1, "id of the user must be equal 1");
    }

    @Test
    @DisplayName("test getById() method")
    public void getIdTest() {
        User user = getSingleUser().get(0);
        userRepository.add(user);
        Optional<User> optionalUser = userRepository.getById(user.getId());

        //assume is new Features of Junit5 it will be throwing Exception when this case will return false
        assumeTrue(optionalUser.isPresent());

        //when this case has already true we can continue our test logic
        assertSame(user, optionalUser.get(), "these two references must be looking the same Object ");
    }

    @Test
    @DisplayName("test getAll() method")
    public void getAllTest() {
        getSomeUsers().forEach(userRepository::add);
        List<User> userList = userRepository.getAll();
        //through this method we can check all values from list on by one
        assertAll(
                () -> assertEquals(1, userList.get(0).getId(), "first id of the user must be equal 1"),
                () -> assertEquals(2, userList.get(1).getId(), "second id of the user must be equal 2"),
                () -> assertEquals(3, userList.get(2).getId(), "third id of the user must be equal 3"),
                () -> assertEquals(4, userList.get(3).getId(), "fourth id of the user must be equal 4")
                );
    }

    static List<User> getSingleUser() {
        return Collections.singletonList(User
                .builder()
                .name("Tomas")
                .surname("Walter")
                .age(25)
                .build());
    }

    static List<User> getSomeUsers() {
        return Arrays.asList(
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
                        .build(),
                User
                        .builder()
                        .name("Leo")
                        .surname("Morison")
                        .age(20)
                        .build(),
                User
                        .builder()
                        .name("Yana")
                        .surname("Violov")
                        .age(18)
                        .build());
    }
}