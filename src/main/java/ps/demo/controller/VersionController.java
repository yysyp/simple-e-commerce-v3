package ps.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ps.demo.config.GitInfoConfig;

import java.io.*;

@Slf4j
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private GitInfoConfig git;

    @GetMapping("/gitCommitIdv1")
    public GitInfoConfig getGitCommitIdv1() throws IOException {
        log.info("--git={}", git);
        return git;
    }

    @GetMapping("/gitCommitIdv2")
    public String getGitCommitIdv2() throws IOException {
        //git.json  or  git.properties
        File file = ResourceUtils.getFile("classpath:git");
        if (file.exists()) {
            String s = "";
            InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader br = new BufferedReader(in);
            StringBuffer content = new StringBuffer();
            while ((s = br.readLine()) != null) {
                content = content.append(s);
            }
            return content.toString();
        } else {
            return "";
        }
    }
}