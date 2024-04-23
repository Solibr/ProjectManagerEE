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
        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }

    public static Project getOneProject() {
        Project p = new Project();
        p.setName("One project EEE");
        return p;
    }
}
