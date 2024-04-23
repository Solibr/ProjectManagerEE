package repository;

import entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    public static List<Project> getAllProjects() {
        List<Project> list = new ArrayList<Project>();
        Project p1 = new Project();
        Project p2 = new Project();
        Project p3 = new Project();
        p1.setName("Some");
        p2.setName("Another");
        p3.setName("Other");
        p1.setId(1L);
        p2.setId(2L);
        p3.setId(3L);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }

    public static Project getProjectById(Long id) {
        if (id == 2) {
            Project pp = new Project();
            pp.setName("Another oo");
            pp.setId(2L);
            pp.setParentId(1L);
            return pp;
        }

        Project p = new Project();
        p.setName("random project EEE");
        p.setId(id);
        return p;
    }
}
