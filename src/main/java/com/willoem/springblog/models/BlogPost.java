
package com.willoem.springblog.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="BLOG_POST")
public class BlogPost implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="POST_ID")
    private Integer postId;
    
    @NotNull
    @NotEmpty
    @Size(min=1, max=200)
    @Column(name="TITLE")
    private String title;
    
    @NotNull
    @NotEmpty
    @Size(min=1, max=100)
    @Column(name="SLUG", unique=true)
    private String slug;
    
    @NotNull
    @NotEmpty
    @Column(name="MARKUP")
    @Lob
    private String markup;
    
    @Column(name="POST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar postDate; 
    
    @Column(name="STATUS")
    @Size(min=1, max=10)
    private String status;
    
    @Column(name="ENABLE_COMMENTS")
    private boolean enableComments;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="BLOG_POST_TAGS", 
            joinColumns={@JoinColumn(name="POST_ID")},
            inverseJoinColumns={@JoinColumn(name="TAG_ID")})
    private Set<Tag> tags = new HashSet<Tag>();

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public Calendar getPostDate() {
        return postDate;
    }

    public void setPostDate(Calendar postDate) {
        this.postDate = postDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEnableComments() {
        return enableComments;
    }

    public void setEnableComments(boolean enableComments) {
        this.enableComments = enableComments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    
    public String printPostDate(){
        DateFormat df = new SimpleDateFormat("MMM d yyyy hh:mm aaa");
        return df.format(this.postDate.getTime());
    }
    
    public List<String> getTagList(){
        Hibernate.initialize(this.getTags());
        Set<Tag> tags = this.getTags();
        List<String> tagList = new ArrayList();
        for (Tag tag: tags){
            tagList.add(tag.getTagName());
        }
        return tagList;
    }
}
