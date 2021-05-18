package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @Column(unique = true, nullable = false, columnDefinition="VARCHAR(64)")
    private String nickname;

    private String name;
    private String surname;
    private String password;

    @Column(name = "is_boss")
    private boolean isBoss;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("task_id")
    private List<Task> tasks = new ArrayList<Task>();

    public User() {
    }

    public User(String name, String surname, String nickname, String password) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.isBoss = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", isBoss=" + isBoss +
                ", taskSize" + getTasks().size() +
                '}';
    }
}
