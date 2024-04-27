package repository;

import entity.Project;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProjectRepository {

    @Resource(name = "jdbc/testDB")
    private DataSource ds;

    @PostConstruct
    public void init() {
        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("create table if not exists projects (id serial, name varchar, parentid bigint default null);");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void save(Project project) {

        try (Connection connection = ds.getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement("insert into projects(name, parentid) values(?, ?);")) {

            insertPreparedStatement.setString(1, project.getName());
            if (project.getParentId() != null) {
                insertPreparedStatement.setLong(2, project.getParentId());
            } else {
                insertPreparedStatement.setNull(2, Types.BIGINT);
            }
            insertPreparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Project project) {
        try (Connection connection = ds.getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement("update projects set name = ? where id = ?;")) {

            insertPreparedStatement.setString(1, project.getName());
            insertPreparedStatement.setLong(2, project.getId());
            insertPreparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Project> findAll() {

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id, name, parentid from projects;")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = createProjectFromResultSetRow(resultSet);
                projects.add(project);
            }
            return projects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Project> findByParentIdEqualsNull() {

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id, name, parentId from projects where parentid is null;")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = createProjectFromResultSetRow(resultSet);
                projects.add(project);
            }
            return projects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Project> findByParentId(Long parentId) {

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id, name, parentId from projects where parentid = ?;")) {

            if (parentId == null) {
                preparedStatement.setNull(1, Types.BIGINT);
            } else {
                preparedStatement.setLong(1, parentId);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = createProjectFromResultSetRow(resultSet);
                projects.add(project);
            }
            return projects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Project> findById(Long id) {

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id, name, parentid from projects where id = ?")) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Project project = createProjectFromResultSetRow(resultSet);
                return Optional.of(project);
            }
            else return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private Project createProjectFromResultSetRow(ResultSet resultSet) throws SQLException {
        Project project = new Project(); // вот это можно и в маппер
        project.setId(resultSet.getLong(1));
        project.setName(resultSet.getString(2));
        project.setParentId(resultSet.getLong(3));
        return project;
    }


    public void deleteById(long id) {

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from projects where id = ?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
