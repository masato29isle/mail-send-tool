package com.github.masato29isle.mail.constants;

import org.simplejavamail.api.email.Recipient;

import javax.mail.Message;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * メールテンプレート
 */
public enum MailTemplate {
    /**
     * テスト
     */
    TEST(
            "test-template"
            , "【テスト】テストメール送信"
            , Arrays.asList(
            new Recipient("TEST", "test@example.com", Message.RecipientType.TO)
            ,new Recipient("TEST2", "test2@example.com", Message.RecipientType.TO)
            )
    );

    /**
     * テンプレートファイル名
     */
    private final String templateName;
    /**
     * メール件名
     */
    private final String subject;
    /**
     * 対象メール宛先情報
     */
    private final List<Recipient> recipientList;

    MailTemplate(String templateName, String subject, List<Recipient> recipientList) {
        this.templateName = templateName;
        this.subject = subject;
        this.recipientList = recipientList;
    }

    /**
     * テンプレートファイル名を取得する
     *
     * @return テンプレートファイル名
     */
    public String getTemplateFileName() {
        return templateName + ".txt";
    }

    /**
     * メールタイトルを取得刷る
     *
     * @return メールタイトル
     */
    public String getSubject() {
        return subject;
    }

    /**
     * メール送信対象者リストを取得する
     *
     * @return メール送信対象者
     */
    public List<Recipient> getRecipientList() {
        return Collections.unmodifiableList(recipientList);
    }

    /**
     * テンプレート名からメールテンプレートを取得する
     *
     * @param templateName テンプレート名
     * @return テンプレートメール
     */
    public static MailTemplate getByTemplateName(String templateName) {
        return Arrays.stream(MailTemplate.values())
                .filter(category -> category.templateName.equals(templateName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("引数の値が不正です"));
    }
}
