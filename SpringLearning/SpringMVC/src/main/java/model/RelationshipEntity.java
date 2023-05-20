package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relationship", schema = "Project")
public class RelationshipEntity {
    private String relationshipName;

    @Id
    @Column(name = "relationship_name")
    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationshipEntity that = (RelationshipEntity) o;

        if (relationshipName != null ? !relationshipName.equals(that.relationshipName) : that.relationshipName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return relationshipName != null ? relationshipName.hashCode() : 0;
    }
}
