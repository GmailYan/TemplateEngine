package main.java.templateService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    @RequestMapping("/greeting")
    public String home() {
        return "Hello World!";
    }
    
    @RequestMapping(path="/template", method=RequestMethod.POST)    
    public String setTemplate() {
        return "Hello World!";
    }
    
    @RequestMapping(path="/mergeTemplate", method=RequestMethod.POST)    
    public String oneTimeTemplateMerge(@RequestParam String template, @RequestParam String context) {
        return "Hello World!";
    }
    
}
