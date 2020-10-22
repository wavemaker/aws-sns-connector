package com.wavemaker.connector.aws.sns.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 10/8/20
 */
public class AWSSNSPublishRequest {

    private String topicArn;
    private String targetArn;
    private String phoneNumber;
    private String message;
    private String subject;
    private String messageStructure;

    private Map<String, AWSSNSMessageAttributeValue> messageAttributes;

    public AWSSNSPublishRequest() {
    }

    public AWSSNSPublishRequest(String topicArn, String message) {
        setTopicArn(topicArn);
        setMessage(message);
    }

    public AWSSNSPublishRequest(String topicArn, String message, String subject) {
        setTopicArn(topicArn);
        setMessage(message);
        setSubject(subject);
    }

    public String getTopicArn() {
        return this.topicArn;
    }

    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }

    public String getTargetArn() {
        return this.targetArn;
    }

    public void setTargetArn(String targetArn) {
        this.targetArn = targetArn;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessageStructure(String messageStructure) {
        this.messageStructure = messageStructure;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageStructure() {
        return this.messageStructure;
    }

    public java.util.Map<String, AWSSNSMessageAttributeValue> getMessageAttributes() {
        if (messageAttributes == null) {
            messageAttributes = new HashMap<String, AWSSNSMessageAttributeValue>();
        }
        return messageAttributes;
    }

    public void setMessageAttributes(java.util.Map<String, AWSSNSMessageAttributeValue> messageAttributes) {
        this.messageAttributes = messageAttributes == null ? null : new HashMap<String, AWSSNSMessageAttributeValue>(messageAttributes);
    }

    @Override
    public String toString() {
        return "AWSSNSPublishRequest{" +
                "topicArn='" + topicArn + '\'' +
                ", targetArn='" + targetArn + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                ", messageStructure='" + messageStructure + '\'' +
                ", messageAttributes=" + messageAttributes +
                '}';
    }
}
