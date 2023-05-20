package model;

import javax.persistence.*;

@Entity
@Table(name = "image", schema = "Project" )
public class ImageEntity {
    private int imageid;
    private String imagename;
    private String imagesource;

    @Id
    @Column(name = "imageid")
    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    @Basic
    @Column(name = "imagename")
    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Basic
    @Column(name = "imagesource")
    public String getImagesource() {
        return imagesource;
    }

    public void setImagesource(String imagesource) {
        this.imagesource = imagesource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageEntity that = (ImageEntity) o;

        if (imageid != that.imageid) return false;
        if (imagename != null ? !imagename.equals(that.imagename) : that.imagename != null) return false;
        if (imagesource != null ? !imagesource.equals(that.imagesource) : that.imagesource != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageid;
        result = 31 * result + (imagename != null ? imagename.hashCode() : 0);
        result = 31 * result + (imagesource != null ? imagesource.hashCode() : 0);
        return result;
    }
}
