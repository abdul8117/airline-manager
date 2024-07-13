package com.abdul.airlinemanager.email;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import jakarta.mail.internet.MimeMessage;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            String subject,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode
    ) throws MessagingException {
        // send email
        String templateName;

        if (emailTemplate == null)
            templateName = "confirm-email";
        else
            templateName = emailTemplate.getName();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context ctx = new Context();
        ctx.setVariables(properties);

        helper.setFrom("contact@airline-manager.io");
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEngine.process(templateName, ctx);

        helper.setText(template, true);

        javaMailSender.send(message);
    }
}
