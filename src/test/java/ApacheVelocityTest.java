package test.java;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApacheVelocityTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testHelloWorld2() throws Exception {
		
        String template = "Hello $name!  Welcome to Velocity!";
                
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("name", "World");
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();

        Velocity.evaluate(context, writer, "testTemplate", template);
                     
        String expected = new String("Hello World!  Welcome to Velocity!");
        assertEquals(expected, writer.toString());
	}
	
	@Test
	public void testHelloWorld() throws Exception {
        /*  first, get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        /*  next, get the Template  */        
        //Template t = ve.getTemplate( "helloworld.vm" );
        Template t = makeTemplateFromString(
        		"helloworld.vm", 
        		"Hello $name!  Welcome to Velocity!"
        );
        
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("name", "World");
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );        
        /* show the World */
                     
        String expected = new String("Hello World!  Welcome to Velocity!");
        assertEquals(expected, writer.toString());
	}

	private Template makeTemplateFromString(String templateName, String templateContent) throws ParseException{
		// Option 1 is Velocity.evaluate() but this incur parsing overhead

		// https://stackoverflow.com/questions/1432468/how-to-use-string-as-velocity-template
		// Option 2 here construct simpleNode
		RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
		StringReader reader = new StringReader(templateContent);
		SimpleNode node = runtimeServices.parse(reader, templateName);
		Template template = new Template();
		template.setRuntimeServices(runtimeServices);
		template.setData(node);
		template.initDocument();
		return template;
		// Option 3 add property "string.resource.loader.class" to engine, 
		// to override it to use our own StringResourceLoader
		
	}
	
}
