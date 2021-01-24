package com.github.masato29isle.mail;

import com.github.masato29isle.mail.constants.MailTemplate;
import com.github.masato29isle.mail.module.MailToolModule;
import com.github.masato29isle.mail.service.MailSendService;
import com.google.inject.Guice;

/**
 * メール送信実行クラス
 */
public class MailSendMain {

    public static void main(String[] args) {
        // メールを送信する
        Guice.createInjector(new MailToolModule())
                .getInstance(MailSendService.class)
                .sendMail(MailTemplate.TEST);
    }
}
