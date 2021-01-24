package com.github.masato29isle.mail.service;

import com.github.masato29isle.mail.constants.MailTemplate;
import com.google.inject.Inject;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

/**
 * 指定したテンプレートのメールを送信する
 */
public class TemplateMailSendService implements MailSendService {

    /**
     * テンプレートエンジン
     */
    private final TemplateEngine templateEngine;
    /**
     * メーラー
     */
    private final Mailer mailer;

    @Inject
    public TemplateMailSendService(TemplateEngine templateEngine, Mailer mailer) {
        this.templateEngine = templateEngine;
        this.mailer = mailer;
    }

    @Override
    public void sendMail(MailTemplate mailTemplate) {
        final Context ctx = new Context(Locale.getDefault());

        Email email = EmailBuilder.startingBlank()
                .to(mailTemplate.getRecipientList())
                .withSubject(mailTemplate.getSubject())
                .withPlainText(templateEngine.process(mailTemplate.getTemplateFileName(), ctx))
                .buildEmail();

        mailer.sendMail(email, false);
    }
}
