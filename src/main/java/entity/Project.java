package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Project {

    private Long id;

    private String name;

    private Long parentId;


}
