package me.woemler.springblog.config;

import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import me.woemler.springblog.models.BlogPost;
import me.woemler.springblog.repositories.BlogRepository;

/**
 * @author woemler
 */
@Component
public class BlogPostInitializer implements CommandLineRunner {

    @Autowired private Environment env;
    @Autowired private BlogRepository blogRepository;

    private static final Logger logger = LoggerFactory.getLogger(BlogPostInitializer.class);

    @Override
    public void run(String... strings) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("dev")){
            String content = "Welcome to your new Spring Boot and MongoDB powered blog!  If you are seeing this post, your application is currently in development mode.  In this mode, the application uses an embedded MongoDB instance that will be created and destroyed with the application context.  Any new posts you create will not persist beyond the life of the application.  You can login the admin page of the blog application [here](/login).  You can find the username and password for the default admin user in the application log:\n" +
                    "\n" +
                    "    Created admin user with username admin and password dd191771-c9d6-4ddf-aa55-f3a7daabf32d\n" +
                    "\n" +
                    "\n" +
                    "To switch to production mode, you can change the active profile in the `application.properties` file:\n" +
                    "\n" +
                    "    spring.profiles.active=prd\n" +
                    "\n" +
                    "Or, you can set the profile from the command line when your start the application:\n" +
                    "\n" +
                    "    java -jar blog.jar --spring.profiles.active=prd\n" +
                    "\n" +
                    "Be sure to update the properties in the `application.properties` and `application-prd.properties` files to suit your needs.";

            BlogPost post = new BlogPost();
            post.setEnableComments(false);
            post.setPostDate(new Date());
            post.setStatus(BlogPost.STATUS_ACTIVE);
            post.setSlug("welcome-to-your-new-blog");
            post.setTags(new HashSet<>(Arrays.asList("welcome")));
            post.setContent(content);
            post.setTitle("Welcome to your new blog!");
            blogRepository.insert(post);
            logger.info("Created default blog post.");
        } else {
            logger.info("Application is not in development mode, no default posts created.");
        }
    }
}
