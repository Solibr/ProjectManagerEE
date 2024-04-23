package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class Task {

    private long id;

    private String name;

    private Long projectId;

    private TaskStatus status;

    private ZonedDateTime createTime;

    private ZonedDateTime statusUpdateTime;

    private String text;

}
