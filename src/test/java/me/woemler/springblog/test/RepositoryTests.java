package me.woemler.springblog.test;

import me.woemler.springblog.models.BlogPost;
import me.woemler.springblog.repositories.BlogRepository;
import me.woemler.springblog.test.config.TestApplicationConfig;
import me.woemler.springblog.test.config.TestDataSourceConfig;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author woemler
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApplicationConfig.class, TestDataSourceConfig.class})
@FixMethodOrder
public class RepositoryTests {
	
	@SuppressWarnings("SpringJavaAutowiringInspection") @Autowired BlogRepository blogRepository;
	
	@Before
	public void setup(){
		blogRepository.deleteAll();
		BlogPost post1 =
				new BlogPost(null, "Test Post1", "test-post1", "This is a test blog post with active comments.",
						new Date(), BlogPost.STATUS_ACTIVE, true, new HashSet<>(Arrays.asList("news")));
		BlogPost post2 =
				new BlogPost(null, "Test Post2", "test-post2", "This is a test blog post that is inactive.",
						new Date(), BlogPost.STATUS_INACTIVE, true, new HashSet<>(Arrays.asList("test")));
		BlogPost post3 = new BlogPost(null, "Test Post3", "test-post3",
				"This is a test blog post with inactive comments.",
				new Date(), BlogPost.STATUS_ACTIVE, false, new HashSet<>(Arrays.asList("test", "news")));
		blogRepository.save(post1);
		blogRepository.save(post2);
		blogRepository.save(post3);
	}
	
	@Test
	public void findAll(){
		
		List<BlogPost> posts = blogRepository.findAll();
		Assert.notNull(posts);
		Assert.notEmpty(posts);
		Assert.isTrue(posts.size() == 3);
		BlogPost post = posts.get(0);
		Assert.isTrue(post.getPostId() != null);
		System.out.println(post.toString());
		
		List<String> tags = blogRepository.findAllTags();
		Assert.notNull(tags);
		Assert.notEmpty(tags);
		Assert.isTrue(tags.size() == 2);
		
	}
	
	@Test
	public void findBlogPostBySlug(){
		BlogPost post = blogRepository.findBySlug("test-post3");
		Assert.notNull(post);
		Assert.isTrue(post.getSlug().equals("test-post3"));
		post.getTags().size();
		Set<String> tags = post.getTags();
		Assert.notNull(tags);
		Assert.notEmpty(tags);
		Assert.isTrue(tags.size() == 2);
	}
	
	@Test
	public void findAllTags(){
		List<String> tags = blogRepository.findAllTags();
		Assert.notNull(tags);
		Assert.notEmpty(tags);
		Assert.isTrue(tags.size() == 2);
		Assert.isTrue(tags.get(0).equals("test") || tags.get(0).equals("news"));
	}
	
	@Test
	public void findTagByFragment(){
		List<String> tags = blogRepository.findTagsByFragment("te");
		System.out.println(tags.toString());
		Assert.notNull(tags);
		Assert.notEmpty(tags);
		Assert.isTrue(tags.size() == 1);
		Assert.isTrue(tags.get(0).equals("test"));
	}
	
	@Test
	public void createPost(){
		BlogPost post = new BlogPost(null, "JUnit is great!", "junit-is-great", "Don't you think so?", 
				new Date(), BlogPost.STATUS_ACTIVE, false, new HashSet<>(Arrays.asList("junit")));
		blogRepository.save(post);
		Assert.notNull(post.getPostId());
		Assert.isTrue(blogRepository.count() == 4);
		Assert.isTrue(blogRepository.findAllTags().size() == 3);
	}
	
	@Test
	public void updatePost(){
		createPost();
		BlogPost post = blogRepository.findBySlug("junit-is-great");
		Assert.notNull(post);
		post.setSlug("junit-post");
		blogRepository.save(post);
		post = blogRepository.findBySlug("junit-is-great");
		Assert.isNull(post);
		post = blogRepository.findBySlug("junit-post");
		Assert.notNull(post);
	}
	
	@Test
	public void deletePost(){
		createPost();
		BlogPost post = blogRepository.findBySlug("junit-is-great");
		blogRepository.delete(post.getPostId());
		post = blogRepository.findBySlug("junit-is-great");
		Assert.isNull(post);
		Assert.isTrue(blogRepository.count() == 3);
		Assert.isTrue(blogRepository.findAllTags().size() == 2);
	}
	
}
