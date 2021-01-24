package com.github.masato29isle.mail.service;

import com.github.masato29isle.mail.constants.MailTemplate;

/**
 * メール送信サービス
 */
public interface MailSendService {

    /**
     * メールを送信する
     *
     * @param mailTemplate 送信対象メールテンプレート
     */
    void sendMail(MailTemplate mailTemplate);
}
