package main.java.templateService.apacheVelocity;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import main.java.templateService.Context;
import main.java.templateService.Template;

public class TemplateService {
	
	VelocityEngine ve;
	
	public TemplateService(){
        ve = new VelocityEngine();
        ve.init();
	}
	
	public String materialiseTemplate(Template template, Context context){
		String templateFile = template.getTemplateString();		
		if(StringUtils.isBlank(templateFile)){
			return "";
		}
		
		VelocityContext velocityContext = convertToVelocityContext(context);
		StringWriter output = new StringWriter();
		ve.evaluate(velocityContext, output, "Template", templateFile);
		return output.toString();
	}
	
	public List<String> bulkMaterialiseTemplate(Template template, List<Context> contexts){
		return contexts.stream().
				map(c -> materialiseTemplate(template, c)).
				collect(Collectors.toList());
	}

	private VelocityContext convertToVelocityContext(Context context){
		VelocityContext vc = new VelocityContext();
		context.forEach((k,v)-> vc.put(k,v));				
		return vc;
	}
	
}
