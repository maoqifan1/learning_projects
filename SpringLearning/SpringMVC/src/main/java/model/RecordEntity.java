package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "record", schema = "Project")
public class RecordEntity {
    private int id;
    private String username;
    private String content;
    private Timestamp chatdate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "chatdate")
    public Timestamp getChatdate() {
        return chatdate;
    }

    public void setChatdate(Timestamp chatdate) {
        this.chatdate = chatdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordEntity that = (RecordEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (chatdate != null ? !chatdate.equals(that.chatdate) : that.chatdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (chatdate != null ? chatdate.hashCode() : 0);
        return result;
    }
}
