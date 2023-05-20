package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friendlist", schema = "Project")
public class FriendlistEntity {
    private int owneruserid;

    @Id
    @Column(name = "Owneruserid")
    public int getOwneruserid() {
        return owneruserid;
    }

    public void setOwneruserid(int owneruserid) {
        this.owneruserid = owneruserid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendlistEntity that = (FriendlistEntity) o;

        if (owneruserid != that.owneruserid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return owneruserid;
    }
}
