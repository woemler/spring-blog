
package me.woemler.springblog.repositories;

import com.google.gson.Gson;
import me.woemler.springblog.models.Tag;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author woemler 
 */

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("select distinct t.tagName from Tag t where t.tagName like %?1%")
    List<String> findDistincTagNameByFragment(String fragment);
    Tag findByTagName(String tagName);
}
