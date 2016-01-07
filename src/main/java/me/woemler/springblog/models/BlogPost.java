
package me.woemler.springblog.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Document(collection = "blog_posts")
public class BlogPost {

    @Id private String postId;
    @NotNull private String title;
    @NotNull @Indexed(unique = true) private String slug;
    @NotNull private String markup;
    private Date postDate = new Date();
    private String status = BlogPost.STATUS_INACTIVE;
    private boolean enableComments = false;
    private Set<String> tags = new HashSet<>();

    public BlogPost() { }

    @PersistenceConstructor
    public BlogPost(String postId, String title, String slug, String markup, Date postDate,
        String status, boolean enableComments, Set<String> tags) {
        this.postId = postId;
        this.title = title;
        this.slug = slug;
        this.markup = markup;
        this.postDate = postDate;
        this.status = status;
        this.enableComments = enableComments;
        this.tags = tags;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String printPostDate(){
        DateFormat df = new SimpleDateFormat("MMM d yyyy hh:mm aaa");
        return df.format(this.postDate.getTime());
    }
    
    public List<String> getTagList(){
        return new ArrayList<>(tags);
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
            "postId='" + postId + '\'' +
            ", title='" + title + '\'' +
            ", slug='" + slug + '\'' +
            ", markup='" + markup + '\'' +
            ", postDate=" + postDate +
            ", status='" + status + '\'' +
            ", enableComments=" + enableComments +
            ", tags=" + tags +
            '}';
    }
}
