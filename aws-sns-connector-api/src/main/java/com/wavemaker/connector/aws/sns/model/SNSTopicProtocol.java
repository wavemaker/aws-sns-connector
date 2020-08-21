package com.wavemaker.connector.aws.sns.model;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 9/8/20
 */
public enum SNSTopicProtocol {

    LAMBDA("lambda"),APPLICATION("application"),SQS("sqs"),SMS("sms"),EMAILJSON("email-json"),EMAIL("email")
    ,HTTPS("https"),HTTP("http");

    public final String value;

    private SNSTopicProtocol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
