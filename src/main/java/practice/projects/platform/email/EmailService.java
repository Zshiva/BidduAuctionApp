package practice.projects.platform.email;

import practice.projects.platform.utils.templateutils.FreeMarkerTemplateUtils;
import practice.projects.usecase.email.SendEmailUseCaseRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Singleton
public class EmailService {
    private final FreeMarkerTemplateUtils freeMarkerTemplateUtils;
    @Inject
    public EmailService(FreeMarkerTemplateUtils freeMarkerTemplateUtils) {
        this.freeMarkerTemplateUtils = freeMarkerTemplateUtils;
    }
    public void sendEmail(SendEmailUseCaseRequest request) throws IOException, MessagingException, TemplateException, SQLException {
            Properties properties = getProperties();
            Session session = createSession(properties);
            MimeMessage message = createMessage(session, request);
            sendMessage(message);
    }
    private Properties getProperties() throws IOException {
        var env = Dotenv.load();
        String smtpPort = env.get("SMTP_PORT");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        return properties;
    }
    private Session createSession(Properties properties) throws MessagingException, SQLException {
        String username = "zshiva35@gmail.com";
        String password = "izwg ntjc eabw oygv";
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    private MimeMessage createMessage(Session session, SendEmailUseCaseRequest request) throws MessagingException, IOException, TemplateException {
        MimeMessage message = new MimeMessage(session);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(request.to()));
        message.setFrom(new InternetAddress("info@bidduauction.com", "Support@bidduauction.com"));
        message.setSubject(request.subject());
        String body = generateEmailBody(request);
        message.setContent(body, "text/html");
        return message;
    }
    private void sendMessage(MimeMessage message) throws MessagingException {
        Transport.send(message);
    }

    private String generateEmailBody(SendEmailUseCaseRequest request) throws IOException, TemplateException {
        Configuration templateConfig = new Configuration(Configuration.VERSION_2_3_20);
        templateConfig.setClassForTemplateLoading(this.getClass(), "/emailTemplates");
        templateConfig.setDefaultEncoding("UTF-8");
        templateConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template = templateConfig.getTemplate("emailTemplate.ftl");
        Map<String, Object> model = new HashMap<>();
        model.put("userEmail", request.to());
        model.put("subject", request.subject());
        model.put("userPassword", request.password());
        return this.freeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}



