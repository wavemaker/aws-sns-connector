package com.wavemaker.connector.aws.sns.model;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 9/8/20
 */
public class AWSSNSSubscribeRequest {

    private String topicArn;

    private SNSTopicProtocol protocol;

    private String endpoint;

    public AWSSNSSubscribeRequest(String topicArn, SNSTopicProtocol protocol, String endpoint) {
        this.topicArn = topicArn;
        this.protocol = protocol;
        this.endpoint = endpoint;
    }

    public String getTopicArn() {
        return topicArn;
    }

    public SNSTopicProtocol getProtocol() {
        return protocol;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
