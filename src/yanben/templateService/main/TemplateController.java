package yanben.templateService.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    @RequestMapping("/greeting")
    public String home() {
        return "Hello World!";
    }
    
}
