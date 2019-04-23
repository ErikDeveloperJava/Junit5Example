package junit5.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private int id;

    private String message;

    private Date sendDate;

    private User from;

    private User to;
}