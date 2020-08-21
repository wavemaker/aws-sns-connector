package com.wavemaker.connector.aws.sns.connector.topic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.model.*;
import com.wavemaker.connector.aws.sns.model.AWSSNSPublishRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSSubscribeRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSSubscribeResponse;
import com.wavemaker.connector.aws.sns.service.AWSSNSService;

@Service
@Primary
public class AWSSNSConnectorImpl implements AWSSNSConnector {

    private static final Logger logger = LoggerFactory.getLogger(AWSSNSConnectorImpl.class);

    @Autowired
    AWSSNSService awssnsService;

    @Override
    public String createTopic(String name) {
        logger.info("Creating SNS topic with name {0}", name);
        CreateTopicResult topic = awssnsService.getSNSClient().createTopic(name);
        return topic.getTopicArn();
    }

    @Override
    public void deleteTopic(String topicArn) {
        logger.info("Deleteing SNS topic with topic arn {0}", topicArn);
        awssnsService.getSNSClient().deleteTopic(topicArn);
    }

    @Override
    public List<String> listTopicArns() {
        awssnsService.getSNSClient().listSubscriptions();
        ListTopicsResult listTopicsResult = awssnsService.getSNSClient().listTopics();
        List<String> topicsArn = new ArrayList<>();
        for (Topic topic : listTopicsResult.getTopics()) {
            String topicArn = topic.getTopicArn();
            topicsArn.add(topicArn);
        }
        return topicsArn;
    }

    @Override
    public boolean isTopicArnExist(String topicArn) {
        List<String> topics = listTopicArns();
        for (String topic : topics) {
            if (topic.equals(topicArn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AWSSNSSubscribeResponse> listSubscriptions(String topicArn) {
        List<Subscription> subscriptions = awssnsService.getSNSClient().listSubscriptionsByTopic(topicArn).getSubscriptions();
        List<AWSSNSSubscribeResponse> awssnsSubscribeResponses = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            awssnsSubscribeResponses.add(awssnsService.toAWSSNSTopicSubscription(subscription));
        }
        return awssnsSubscribeResponses;
    }

    @Override
    public AWSSNSSubscribeResponse subscribe(AWSSNSSubscribeRequest subscribeRequest) {
        logger.info("Adding subscription arn {0} to topic arn {1}", subscribeRequest.getProtocol().getValue(), subscribeRequest.getTopicArn());
        SubscribeResult subscribe = awssnsService.getSNSClient().subscribe(awssnsService.toSubscribeRequest(subscribeRequest));
        //awssnsService.getSNSClient().confirmSubscription(subscribeRequest.getTopicArn(), subscribe.getSubscriptionArn());
        return getSubscription(subscribeRequest.getTopicArn(), subscribe.getSubscriptionArn());
    }

    @Override
    public AWSSNSSubscribeResponse getSubscription(String topicArn, String subscribeArn) {
        List<Subscription> subscriptions = awssnsService.getSNSClient().listSubscriptionsByTopic(topicArn).getSubscriptions();
        for (Subscription subscription : subscriptions) {
            if (subscription.getSubscriptionArn().equals(subscribeArn)) {
                return awssnsService.toAWSSNSTopicSubscription(subscription);
            }
        }
        throw new AmazonSNSException("Given SNS Subscription Arn does not exist in the AWS account");
    }

    @Override
    public void unSubscribe(String subscriptionArn) {
        logger.info("Removing subscription with subscription arn {0}", subscriptionArn);
        awssnsService.getSNSClient().unsubscribe(subscriptionArn);
    }

    @Override
    public String publishMessage(String topicArn, String message) {
        logger.info("Sending message to topic arn {0}", topicArn);
        PublishResult publishResult = awssnsService.getSNSClient().publish(topicArn, message);
        return publishResult.getMessageId();
    }

    @Override
    public String publishMessage(String topicArn, String message, String subject) {
        logger.info("Sending message to topic arn {0}", topicArn);
        PublishResult publishResult = awssnsService.getSNSClient().publish(topicArn, message, subject);
        return publishResult.getMessageId();
    }

    @Override
    public String publishMessage(AWSSNSPublishRequest publishRequest) {
        logger.info("Sending message to topic arn {0}", publishRequest.getTopicArn());
        PublishRequest request = awssnsService.toPublishRequest(publishRequest);
        PublishResult publishResult = awssnsService.getSNSClient().publish(request);
        return publishResult.getMessageId();
    }

}