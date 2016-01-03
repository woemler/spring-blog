package me.woemler.springblog.test;

import me.woemler.springblog.models.BlogPost;
import me.woemler.springblog.models.Tag;
import me.woemler.springblog.repositories.BlogService;
import me.woemler.springblog.repositories.TagService;
import me.woemler.springblog.test.config.TestApplicationConfig;
import me.woemler.springblog.test.config.TestDataSourceConfig;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author woemler
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApplicationConfig.class, TestDataSourceConfig.class})
@FixMethodOrder
public class RepositoryTests {
	
	@SuppressWarnings("SpringJavaAutowiringInspection") @Autowired BlogService blogService;
	@SuppressWarnings("SpringJavaAutowiringInspection") @Autowired TagService tagService;
	private static boolean isConfigured = false;
	
	@Before
	public void setup(){
		if (!isConfigured) {
			blogService.deleteAll();
			tagService.deleteAll();
			Tag tag1 = new Tag("test", null);
			Tag tag2 = new Tag("news", null);
			tagService.save(tag1);
			tagService.save(tag2);
			BlogPost post1 =
					new BlogPost("Test Post1", "test-post1", "This is a test blog post with active comments.",
							new Date(), BlogPost.STATUS_ACTIVE, true, new HashSet<>(Arrays.asList(tag2)));
			BlogPost post2 =
					new BlogPost("Test Post2", "test-post2", "This is a test blog post that is inactive.",
							new Date(), BlogPost.STATUS_INACTIVE, true, new HashSet<>(Arrays.asList(tag1)));
			BlogPost post3 = new BlogPost("Test Post3", "test-post3",
					"This is a test blog post with inactive comments.",
					new Date(), BlogPost.STATUS_ACTIVE, false, new HashSet<>(Arrays.asList(tag1, tag2)));
			blogService.save(post1);
			blogService.save(post2);
			blogService.save(post3);
		}
		isConfigured = true;
	}
	
	@Test
	public void findAll(){
		
		List<BlogPost> posts = blogService.findAll();
		Assert.notNull(posts);
		Assert.notEmpty(posts);
		Assert.isTrue(posts.size() == 3);
		BlogPost post = posts.get(0);
		Assert.isTrue(post.getPostId() != null);
		System.out.println(post.toString());
		
		List<Tag> tags = tagService.findAll();
		Assert.notNull(tags);
		Assert.notEmpty(tags);
		Assert.isTrue(tags.size() == 2);
		Tag tag = tags.get(0);
		Assert.notNull(tag.getTagId());
		System.out.println(tag.toString());
		
	}
	
	@Test
	public void findBlogPostBySlug(){
		BlogPost post = blogService.findBySlug("test-post3");
		Assert.notNull(post);
		Assert.isTrue(post.getSlug().equals("test-post3"));
		post.getTags().size();
		Set<Tag> tags = post.getTags();
		Assert.notNull(tags);
		Assert.notEmpty(tags);
		Assert.isTrue(tags.size() == 2);
	}
	
	@Transactional
	@Test
	public void findTagByName(){
		Tag tag = tagService.findByTagName("test");
		Assert.notNull(tag);
		Assert.notNull(tag.getTagId());
		Set<BlogPost> posts = tag.getBlogPosts();
		Assert.notNull(posts);
		Assert.notEmpty(posts);
		Assert.isTrue(posts.size() == 2);
	}
	
}
