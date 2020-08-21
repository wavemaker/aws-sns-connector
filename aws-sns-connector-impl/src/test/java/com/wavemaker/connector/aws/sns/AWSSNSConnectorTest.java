package com.wavemaker.connector.aws.sns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wavemaker.connector.aws.sns.connector.fcm.AWSSNSFCMConnector;
import com.wavemaker.connector.aws.sns.model.AWSSNSFCMRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSFCMResponse;
import com.wavemaker.connector.aws.sns.model.*;
import com.wavemaker.connector.aws.sns.connector.pushnotification.AWSSNSPushNotificationConnector;
import com.wavemaker.connector.aws.sns.connector.topic.AWSSNSConnector;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AWSSNSConnectorTestConfiguration.class)
public class AWSSNSConnectorTest {

    @Autowired
    private AWSSNSConnector awssnsConnector;

    @Autowired
    private AWSSNSPushNotificationConnector pushNotificationConnector;

    @Autowired
    private AWSSNSFCMConnector AWSSNSFCMConnector;

    private String token = "DummyelMrUKfuGHltmsRu3d7uFm:APA91bHFcpRo5JoctraIIJsn6IxKar4uiYwZiuBOt-EaUryfqfOM4ylY3wD0nBOMQle101aeyiMTH2SrcFiLn0Ga-PJYrpugMbH8HngHShCEOdjxI6ea4Rty-qxH0IVVLJq0FIeMnW_6";

    private String message = "{\"default\": \"Sample fallback message\",\"GCM\": \"{ \\\"data\\\" : {\\\"notification\\\" : {\\\"body\\\" : \\\"Total cases:18,350,079   Deaths:694,906 Recovered:11,549,268\\\",\\\"title\\\": \\\"Coronavirus Live\\\",\\\"icon\\\": \\\"https://financesonline.com/uploads/2019/10/WaveMaker-logo1.png\\\"} } }\"}";

    @Test
    public void createApplicationAndTopic() {
        //subscribe and register
        String platformArn = pushNotificationConnector.createFCMPlatformApplication("WMApp2");
        User wmUser1 = new User("101", "WMUser2");
        String endPointArn = pushNotificationConnector.addTokenToPlatformApplication(platformArn, token);
        String topicArn = awssnsConnector.createTopic("Topic_" + wmUser1.getUserId() + "_" + wmUser1.getUserName());
        AWSSNSSubscribeResponse subscribe = awssnsConnector.subscribe(new AWSSNSSubscribeRequest(topicArn, SNSTopicProtocol.APPLICATION, endPointArn));

        //publish message
        AWSSNSPublishRequest awssnsPublishRequest = new AWSSNSPublishRequest();
        awssnsPublishRequest.setMessage(message);
        awssnsPublishRequest.setMessageStructure("json");
        awssnsPublishRequest.setTopicArn(topicArn);
        awssnsConnector.publishMessage(awssnsPublishRequest);

        //unsubscribe & delete
        awssnsConnector.unSubscribe(subscribe.getSubscriptionArn());
        awssnsConnector.deleteTopic(topicArn);
        pushNotificationConnector.deleteTokenEndPointArnFromPlatformApplication(platformArn, endPointArn);
        pushNotificationConnector.deletePlatformApplication(platformArn);
    }

    @Test
    public void demoConnector() {
        User user = new User("1006", "WMDemo2");
        AWSSNSFCMRequest awssnsfcmRequest = new AWSSNSFCMRequest()
                .setBody("Total cases:18,350,079   Deaths:694,906  Recovered:11,549,268")
                .setIcon("https://www.world-heart-federation.org/wp-content/uploads/novel-coronavirus.png")
                .setIcon_click_url("https://www.worldometers.info/coronavirus/")
                .setTitle("Coronavirus Live")
                .setUser(user);

        AWSSNSFCMResponse AWSSNSFCMResponse = AWSSNSFCMConnector.registerToken(token, user);
        AWSSNSFCMConnector.publishMessage(awssnsfcmRequest);

    }

    @Test
    public void publishMessageToTopic() {
        User user = new User("2", "user");
        AWSSNSFCMRequest request = new AWSSNSFCMRequest();
        request.setBody("VIVO IPL 2020 will be played from 19th September")
                .setIcon("https://aniportalimages.s3.amazonaws.com/media/details/IPL_6LShH3c.jpg")
                .setTitle("IPL 2020 Live")
                .setIcon_click_url("hhttps://upload.wikimedia.org/wikipedia/en/a/ab/Indian_Premier_League_Logo.png")
                .setUser(user);
        AWSSNSFCMConnector.publishMessage(request);
    }


}