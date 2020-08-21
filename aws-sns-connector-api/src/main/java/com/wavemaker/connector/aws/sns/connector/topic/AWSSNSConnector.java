package com.wavemaker.connector.aws.sns.connector.topic;

import java.util.List;

import com.wavemaker.connector.aws.sns.model.AWSSNSPublishRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSSubscribeRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSSubscribeResponse;
import com.wavemaker.runtime.connector.annotation.WMConnector;


@WMConnector(name = "aws-sns-connector",
        description = "This connector exposes apis to manage topics and subscription")
public interface AWSSNSConnector {

    /**
     * Creates topic in AWS SNS
     *
     * @param name topic name
     * @return topic ARN (amazon resource name)
     */
    public String createTopic(String name);

    /**
     * Deletes topic in AWS SNS
     *
     * @param topicArn topicARN (amazon resource name)
     */
    public void deleteTopic(String topicArn);

    /**
     * List of topics in AWS SNS
     *
     * @return list of SNS topic ARNs (amazon resource name)
     */
    public List<String> listTopicArns();

    /**
     * Verifies topic exist in AWS SNS
     *
     * @param topicArn topic ARN (amazon resource name)
     * @return true is topic exist, else false
     */
    public boolean isTopicArnExist(String topicArn);

    /**
     * List subscriptions to a topic in AWS SNS
     *
     * @param topicArn topic ARN (amazon resource name)
     * @return list of subscriptions
     */
    public List<AWSSNSSubscribeResponse> listSubscriptions(String topicArn);

    /**
     * Subscribe a protocol to an AWS SNS topic.Protocol such as lambda, SQS, http, https, email, application...etc
     *
     * @param subscribeRequest subscription request consist of topicArn, protocol and endpoint Arn.
     * @return Subscription response
     */
    public AWSSNSSubscribeResponse subscribe(AWSSNSSubscribeRequest subscribeRequest);

    /**
     * Get Subscription details of a SNS topic
     *
     * @param topicArn     Amazon resource name of a SNS topic
     * @param subscribeArn Subscription Amazon resource name
     * @return Subscription response
     */
    public AWSSNSSubscribeResponse getSubscription(String topicArn, String subscribeArn);

    /**
     * Removes subscription from a SNS topic
     *
     * @param subscriptionArn Subscription Amazon resource name
     */
    public void unSubscribe(String subscriptionArn);

    /**
     * Publish message to a specific topic in AWS SNS
     *
     * @param topicArn Amazon resource name of a SNS topic
     * @param message  message to be send
     * @return AWS SNS MessageId
     */
    public String publishMessage(String topicArn, String message);

    /**
     * Publish message to a specific topic in AWS SNS
     *
     * @param topicArn Amazon resource name of a SNS topic
     * @param message  message to be send
     * @param subject  subject of the message
     * @return AWS SNS MessageId
     */
    public String publishMessage(String topicArn, String message, String subject);


    /**
     * Send message in specific structure with custom attributes
     * @param publishRequest
     * @return AWS SNS MessageId
     */
    public String publishMessage(AWSSNSPublishRequest publishRequest);

}