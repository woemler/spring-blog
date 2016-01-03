
package me.woemler.springblog.repositories;

import me.woemler.springblog.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author woemler 
 */

public interface BlogRepository extends JpaRepository<BlogPost, Integer> {
  BlogPost findBySlug(String slug);
}
