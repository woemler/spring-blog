
package me.woemler.springblog.models;

import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private Date postDate; 
    
    @Column(name="STATUS")
    @Size(min=1, max=10)
    private String status;
    
    @Column(name="ENABLE_COMMENTS")
    private boolean enableComments;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="BLOG_POST_TAGS", 
            joinColumns={@JoinColumn(name="POST_ID")},
            inverseJoinColumns={@JoinColumn(name="TAG_ID")})
    private Set<Tag> tags;


    public BlogPost() { }

    public BlogPost(String title, String slug, String markup, Date postDate,
        String status, boolean enableComments, Set<Tag> tags) {
        this.title = title;
        this.slug = slug;
        this.markup = markup;
        this.postDate = postDate;
        this.status = status;
        this.enableComments = enableComments;
        this.tags = tags;
    }

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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
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
    
    //Return the first paragraph of a blog post
    public String getPostPreview(){
        String[] paragraphs = this.getMarkup().split("</p>");
        String paragraph = paragraphs[0] + "</p>";
        if (paragraphs.length>1){
            paragraph = paragraph + "<p><em>More...</em></p>";
        }
        return paragraph;
    }
    
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_INACTIVE = "inactive";

    @Override public String toString() {
        return "BlogPost{" +
            "postId=" + postId +
            ", title='" + title + '\'' +
            ", slug='" + slug + '\'' +
            ", markup='" + markup + '\'' +
            ", postDate=" + postDate +
            ", status='" + status + '\'' +
            ", enableComments=" + enableComments +
            '}';
    }
}
