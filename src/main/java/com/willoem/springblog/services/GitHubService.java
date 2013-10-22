
package com.willoem.springblog.services;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface GitHubService {
    
    List<Map<String,String>> getReposByUser(String user) 
            throws MalformedURLException, Exception;
    
}
