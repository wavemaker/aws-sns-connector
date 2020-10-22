package com.wavemaker.connector.aws.sns.connector.fcm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.wavemaker.connector.aws.sns.connector.pushnotification.AWSSNSPushNotificationConnector;
import com.wavemaker.connector.aws.sns.connector.topic.AWSSNSConnector;
import com.wavemaker.connector.aws.sns.model.*;
import com.wavemaker.connector.aws.sns.props.AWSProps;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 10/8/20
 */
@Service
@Primary
public class AWSSNSFCMConnectorImpl implements AWSSNSFCMConnector {

    private static final Logger logger = LoggerFactory.getLogger(AWSSNSFCMConnectorImpl.class);

    @Autowired
    AWSSNSPushNotificationConnector pushNotificationConnector;
    @Autowired
    AWSSNSConnector awssnsConnector;
    @Autowired
    AWSProps awsProps;

    private String message = "{\"default\": \"default\",\"GCM\": \"{ \\\"data\\\" : {\\\"notification\\\" : {\\\"body\\\" : \\\"$body\\\",\\\"title\\\": \\\"$title\\\",\\\"icon\\\": \\\"$icon\\\",\\\"click_action\\\": \\\"$click_action\\\"} } }\"}";

    @Override
    public AWSSNSFCMResponse registerToken(String token, User user) {
        String platformArn = pushNotificationConnector.createFCMPlatformApplication(awsProps.getAws_sns_fcm_application_name());
        String endPointArn = pushNotificationConnector.addTokenToPlatformApplication(platformArn, token);
        String topicArn = awssnsConnector.createTopic("Topic_" + user.getUserId() + "_" + getFirstName(user.getUserName()).toLowerCase());

        logger.info("Registering token to topic " + topicArn);

        AWSSNSSubscribeResponse subscribe = awssnsConnector.subscribe(new AWSSNSSubscribeRequest(topicArn, SNSTopicProtocol.APPLICATION, endPointArn));
        return new AWSSNSFCMResponse()
                .setPlatformArn(platformArn)
                .setEndPointArn(endPointArn)
                .setTopicArn(topicArn)
                .setSubscriptionArn(subscribe.getSubscriptionArn());
    }

    @Override
    public void publishMessage(AWSSNSFCMRequest request) {
        String firstName = getFirstName(request.getUser().getUserName());
        String userId = request.getUser().getUserId();
        String topicArn = "arn:aws:sns:"+ awsProps.getClientRegion().getName()+ ":"+awsProps.getAws_account_id()+":Topic_" + userId + "_" + firstName.toLowerCase();
        String replaceMessage = message.replace("$body", request.getBody())
                .replace("$title", request.getTitle())
                .replace("$icon", request.getIcon())
                .replace("$click_action", request.getIcon_click_url());

        logger.info("Sending message " + replaceMessage + " to topic " + topicArn);


        AWSSNSPublishRequest awssnsPublishRequest = new AWSSNSPublishRequest();
        awssnsPublishRequest.setMessage(replaceMessage);
        awssnsPublishRequest.setMessageStructure("json");
        awssnsPublishRequest.setTopicArn(topicArn);
        logger.info("AWSSNSPublishRequest details " + awssnsPublishRequest);
        awssnsConnector.publishMessage(awssnsPublishRequest);
    }

    private String getFirstName(String username) {
        if (username.contains(".")) {
            username = username.substring(0, username.indexOf("."));
        }
        if (username.contains(" ")) {
            username = username.substring(0, username.indexOf(" "));
        }
        return username;
    }
}
