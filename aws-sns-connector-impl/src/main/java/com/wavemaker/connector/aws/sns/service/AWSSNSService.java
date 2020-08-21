package com.wavemaker.connector.aws.sns.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.wavemaker.connector.aws.sns.model.AWSSNSMessageAttributeValue;
import com.wavemaker.connector.aws.sns.model.AWSSNSPublishRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSSubscribeRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSSubscribeResponse;
import com.wavemaker.connector.aws.sns.props.AWSProps;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 7/8/20
 */
@Service
public class AWSSNSService {

    @Autowired
    AWSProps awsProps;

    AmazonSNS amazonSNS = null;

    public AmazonSNS getSNSClient() {
        if (amazonSNS == null) {
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsProps.getAccessKey(), awsProps.getAccessSecret());
            amazonSNS = AmazonSNSClientBuilder.standard()
                    .withRegion(awsProps.getClientRegion())
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
        }
        return amazonSNS;
    }

    public AWSSNSSubscribeResponse toAWSSNSTopicSubscription(Subscription subscription) {
        AWSSNSSubscribeResponse awssnsSubscribeResponse = new AWSSNSSubscribeResponse();
        awssnsSubscribeResponse.setEndpoint(subscription.getEndpoint());
        awssnsSubscribeResponse.setOwner(subscription.getOwner());
        awssnsSubscribeResponse.setProtocol(subscription.getProtocol());
        awssnsSubscribeResponse.setSubscriptionArn(subscription.getSubscriptionArn());
        awssnsSubscribeResponse.setTopicArn(subscription.getTopicArn());

        return awssnsSubscribeResponse;

    }

    public SubscribeRequest toSubscribeRequest(AWSSNSSubscribeRequest subscribeRequest) {
        return new SubscribeRequest(subscribeRequest.getTopicArn(), subscribeRequest.getProtocol().getValue(), subscribeRequest.getEndpoint());
    }


    public PublishRequest toPublishRequest(AWSSNSPublishRequest publishRequest) {
        PublishRequest actPublishRequest = new PublishRequest();
        actPublishRequest.setMessage(publishRequest.getMessage());
        actPublishRequest.setMessageStructure(publishRequest.getMessageStructure());
        actPublishRequest.setPhoneNumber(publishRequest.getPhoneNumber());
        actPublishRequest.setSubject(publishRequest.getSubject());
        actPublishRequest.setTargetArn(publishRequest.getTargetArn());
        actPublishRequest.setTopicArn(publishRequest.getTopicArn());
        actPublishRequest.setMessageAttributes(toMessageAttributes(publishRequest.getMessageAttributes()));
        return actPublishRequest;
    }

    private Map<String, MessageAttributeValue> toMessageAttributes(Map<String, AWSSNSMessageAttributeValue> messageAttributes) {
        com.amazonaws.internal.SdkInternalMap<String, MessageAttributeValue> actMessageAttributes = new com.amazonaws.internal.SdkInternalMap<String, MessageAttributeValue>();
        for (String key : messageAttributes.keySet()) {
            AWSSNSMessageAttributeValue awssnsMessageAttributeValue = messageAttributes.get(key);
            MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
            messageAttributeValue.setBinaryValue(awssnsMessageAttributeValue.getBinaryValue());
            messageAttributeValue.setDataType(awssnsMessageAttributeValue.getDataType());
            messageAttributeValue.setStringValue(awssnsMessageAttributeValue.getStringValue());
            actMessageAttributes.put(key, messageAttributeValue);

        }
        return actMessageAttributes;
    }
}
