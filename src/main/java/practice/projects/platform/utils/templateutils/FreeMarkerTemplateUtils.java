package practice.projects.platform.utils.templateutils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Singleton
public class FreeMarkerTemplateUtils {

    public String processTemplateIntoString(Template template, Map<String, Object> model)
            throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        return stringWriter.toString();
    }
}
