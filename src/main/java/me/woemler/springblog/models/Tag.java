
package me.woemler.springblog.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="TAG")
public class Tag implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="TAG_ID")
    private Integer tagId;
    
    @NotNull
    @NotEmpty
    @Size(min=1, max=20)
    @Column(name="TAG_NAME")
    private String tagName;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy="tags")
    private Set<BlogPost> blogPosts;

    public Tag() { }

    public Tag(String tagName, Set<BlogPost> blogPosts) {
        this.tagName = tagName;
        this.blogPosts = blogPosts;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tag) {
        this.tagName = tag;
    }

    public Set<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(Set<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }

    @Override public String toString() {
        return "Tag{" +
            "tagId=" + tagId +
            ", tagName='" + tagName + '\'' +
            '}';
    }
}
