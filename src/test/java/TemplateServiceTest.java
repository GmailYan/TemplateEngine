package test.java;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.templateService.Context;
import main.java.templateService.Template;
import main.java.templateService.apacheVelocity.TemplateService;

public class TemplateServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testMaterialiseTemplate() {
		TemplateService service = new TemplateService();
		Template template = new Template();
		Context context = new Context();
		String result = service.materialiseTemplate(template, context);
		assertEquals("", result);
	}

	@Test
	public void testMaterialiseTemplateNormal() {
		TemplateService service = new TemplateService();
		
		Template template = new Template();
		template.setTemplateString("Hello $name. welcome");
		Context context = new Context();
		context.put("name", "Ben");
		
		String result = service.materialiseTemplate(template, context);
		assertEquals("Hello Ben. welcome", result);
	}
	
	@Test
	public void testMaterialiseTemplate_VariableNotFound() {
		TemplateService service = new TemplateService();
		
		Template template = new Template();
		template.setTemplateString("Hello $name . $email");
		Context context = new Context();
		context.put("name", "Ben");
		
		String result = service.materialiseTemplate(template, context);
		assertEquals("Hello Ben . $email", result);
	}
	
	@Test
	public void testMaterialiseTemplate_VariableEnding() {
		TemplateService service = new TemplateService();
		
		Template template = new Template();
		template.setTemplateString("Hello $name2.");
		Context context = new Context();
		context.put("name", "Ben");
		
		String result = service.materialiseTemplate(template, context);
		assertEquals("Hello $name2.", result);
	}
	
	@Test
	public void testBulkMaterialiseTemplate_Normal() {
		TemplateService service = new TemplateService();
		
		Template template = new Template();
		template.setTemplateString("Hello $name.");
		Context context = new Context();
		context.put("name", "Ben");
		Context context2 = new Context();
		context2.put("name", "Yan");
		List<Context> contexts = Arrays.asList(context, context2);
		
		List<String> results = service.bulkMaterialiseTemplate(template, contexts);
		assertEquals("Hello Ben.", results.get(0));
		assertEquals("Hello Yan.", results.get(1));
	}

}
