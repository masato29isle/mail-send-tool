package com.github.masato29isle.mail;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.masato29isle.mail.constants.MailTemplate;
import com.github.masato29isle.mail.module.MailToolModule;
import com.github.masato29isle.mail.service.MailSendService;
import com.google.inject.Guice;

/**
 * メール送信実行(AWS-Lambda関数定義)クラス
 */
public class MailSendHandler implements RequestHandler<MailSendHandler.RequestData, MailSendHandler.ResponseData> {

    @Override
    public ResponseData handleRequest(RequestData requestData, Context context) {
        try {
            // メールを送信する
            Guice.createInjector(new MailToolModule())
                    .getInstance(MailSendService.class)
                    .sendMail(MailTemplate.getByTemplateName(requestData.targetTemplateName));
        } catch(Exception e) {
            return new ResponseData(Result.ERROR, e.getMessage());
        }
        return new ResponseData(Result.SUCCESS, "Mail sending completed!!");
    }

    /**
     * リクエストデータ
     */
    static class RequestData {
        public String targetTemplateName;
    }

    /**
     * レスポンスデータ
     */
    static class ResponseData {
        ResponseData(Result result, String detail) {
            this.result = result;
            this.detail = detail;
        }
        public final Result result;
        public final String detail;
    }

    /**
     * 実行結果
     */
    private enum Result {
        SUCCESS,
        ERROR
    }
}
