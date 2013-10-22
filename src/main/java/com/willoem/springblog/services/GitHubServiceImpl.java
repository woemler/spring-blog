
package com.willoem.springblog.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GitHubServiceImpl implements GitHubService {
    
    @Override
    public List<Map<String,String>> getReposByUser(String user) 
            throws MalformedURLException, Exception
    {
        List<Map<String,String>> repos = new ArrayList();
        String url = "https://api.github.com/users/" + user + "/repos";
        JSONArray json = new JSONArray(readUrl(url));
        if (json.length() > 0){
            for (int i=0; i<json.length(); i++){
                Map<String,String> repo = new HashMap();
                JSONObject jsonObj = json.getJSONObject(i);
                repo.put("title", (String) jsonObj.get("name"));
                repo.put("description", (String) jsonObj.get("description"));
                repo.put("url", (String) jsonObj.get("html_url"));
                repos.add(repo);
            }
        }
        
        return repos;
    }
    
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }
    
}
