package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, name = "task_id")
    private long id;

    private String title;
    private String description;

    @Column(name = "is_done")
    private boolean isDone = false;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "nickname")
    private User user;

    private String senderNickname = "own task";

    public Task() {
    }

    public Task(String title, String description, LocalDate date, User user) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public Task(String title, String description, LocalDate date, User user, String senderNickname) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.user = user;
        this.senderNickname = senderNickname;
    }

    public Task(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isDone=" + isDone +
                ", date=" + date +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getId().equals(task.getId()) &&
                isDone() == task.isDone() &&
                getTitle().equals(task.getTitle()) &&
                getDescription().equals(task.getDescription()) &&
                getDate().equals(task.getDate()) &&
                getUser().equals(task.getUser()) &&
                getSenderNickname().equals(task.getSenderNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), isDone(), getDate(), getUser(), getSenderNickname());
    }
}
