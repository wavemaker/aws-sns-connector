package com.wavemaker.connector.aws.sns.model;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 10/8/20
 */
public class AWSSNSFCMResponse {

    private String platformArn;

    private String endPointArn;

    private String topicArn;

    private String subscriptionArn;;

    public String getPlatformArn() {
        return platformArn;
    }

    public AWSSNSFCMResponse setPlatformArn(String platformArn) {
        this.platformArn = platformArn;
        return this;
    }

    public String getEndPointArn() {
        return endPointArn;
    }

    public AWSSNSFCMResponse setEndPointArn(String endPointArn) {
        this.endPointArn = endPointArn;
        return this;
    }

    public String getTopicArn() {
        return topicArn;
    }

    public AWSSNSFCMResponse setTopicArn(String topicArn) {
        this.topicArn = topicArn;
        return this;
    }

    public String getSubscriptionArn() {
        return subscriptionArn;
    }

    public AWSSNSFCMResponse setSubscriptionArn(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
        return this;
    }
}
