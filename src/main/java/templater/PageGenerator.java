package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


public class PageGenerator {

    private static PageGenerator pageGenerator;
    private final Configuration configuration;

    public static PageGenerator instance() {

        if (pageGenerator == null) pageGenerator = new PageGenerator();

        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            //configuration.setTemplateLoader(new FileTemplateLoader(new File("src/main/webapp")));
            configuration.setDirectoryForTemplateLoading(new File("C:/JAVA/etaxi/src/main/webapp"));
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator() {

        configuration = new Configuration();
        //configuration.setTemplateLoader(new ClassTemplateLoader(this.getClass(), ""));

    }
}
