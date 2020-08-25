## Connector  Introduction

Connector is a Java based backend extension for WaveMaker applications. Connectors are built as Java modules & exposes java based SDK to interact with the connector implementation.
Each connector is built for a specific purpose and can be integrated with one of the external services. Connectors are imported & used in the WaveMaker application. Each connector runs on its own container thereby providing the ability to have it’s own version of the third party dependencies.

## Features of Connectors

1. Connector is a java based extension which can be integrated with external services and reused in many Wavemaker applications.
1. Each connector can work as an SDK for an external system.
1. Connectors can be imported once in a WaveMaker application and used many times in the applications by creating multiple instances.
1. Connectors are executed in its own container in the WaveMaker application, as a result there are no dependency version conflict issues between connectors.

## About AWS SNS Connector

## Introduction
Amazon Simple Notification Service (Amazon SNS) is a web service that coordinates and manages the delivery or sending of messages to subscribing endpoints or clients. In Amazon SNS, there are two types of clients—publishers and subscribers—also referred to as producers and consumers. Publishers communicate asynchronously with subscribers by producing and sending a message to a topic. Subscribers (that is, web servers, email addresses, Amazon SQS queues, AWS Lambda functions) consume or receive the messages.

In few AWS region, Amazon SNS allows sending notification messages to mobile and web endpoints, whether it is direct or with subscriptions to a topic, you first need to register the app with AWS. To register your mobile app with AWS, enter a name to represent your app, choose the platform that will be supported such as Firebase cloud messaging, and provide your credentials for the notification service platform. After the app is registered with AWS, the next step is to create an endpoint for the app and mobile device. The endpoint is then used by Amazon SNS for sending notification messages to the app and device.


This connector provides WM application to interact with AWS SNS using simple and easy apis. Basically this connector provide two functionalities

## Basic SNS operations
Basic sns operations such as WaveMaker application do following SNS actions
1. Create, List, Delete, Existence of SNS topic
1. Add, delete, get, update Subscriptions to a topic. Subscriptions are such as Lambda, SQS, SMS,email, https, http & platform application.
1. Sending messages to a specific topics


## SNS- FCM Push Notifications
1. It also provides apis to create, delete, list FCM platform application in SNS.
1. Apis to register token to FCM platform application, subsequently token ARN can be registered as a subscriber to SNS topic.
1. Api to send FCM notification to topics, which indeed send notification to FCM clients.

## Prerequisite
1. Firebase application and firebase Web/Android/IOS app configuration 
1. Firebase Cloud messaging server key
1. Push Notifications Prefab in WaveMaker application
1. AWS account with key and secret
1. Java 1.8 or above
1. Maven 3.1.0 or above
1. Any java editor such as Eclipse, Intelij..etc
1. Internet connection

Step 1,2 & 3 are needed only if you are implementing FCM push notification.

## Build
You can build this connector using following command

```
mvn clean install -DskipTests
```

## Basic SNS apis

Using following connector, user can manage SNS topics, subscriptions and publish messages to topic.

```
@Autowired
private AWSSNSConnector awssnsConnector;

String topicArn = awssnsConnector.createTopic("Topic");
awssnsConnector.subscribe(new AWSSNSSubscribeRequest(topicArn, SNSTopicProtocol.EMAIL, endPointArn));
awssnsConnector.publishMessage(topicArn,"Hi hello world");
```

## SNS- FCM Push Notifications configuration
1. Go to https://console.firebase.google.com/
1. Login with your google account
1. Click on Add project and provide your project name.This will create a firebase workspace to you.
![alt text](https://github.com/wavemaker/aws-sns-connector/blob/master/readmeImages/FirebaseCreateAPP.jpeg?raw=true)

1. Go to project overview and project settings
![alt text](https://github.com/wavemaker/aws-sns-connector/blob/master/readmeImages/FirebaseProjectSettings.jpeg?raw=true)

1. Go to add App, you can select either Web, Android, IOS app type and provide the name of the app.
app name should not be same as your web application or mobile application.On after app creation, it will provide app configuration values.Copy these values.
![alt text](https://github.com/wavemaker/aws-sns-connector/blob/master/readmeImages/FirebaseWebAppSettings.jpeg?raw=true)

1. In addition, go to Cloud Messaging copy server key value.
![alt text](https://github.com/wavemaker/aws-sns-connector/blob/master/readmeImages/FirebaseServerKey.jpeg?raw=true)

1. Import connector ZIP artifact in wavemaker applications and go to profile properties
Provide values for following properties

    ```
    connector.aws-sns-connector.default.aws.accessKey=<<aws key>>
    connector.aws-sns-connector.default.aws.accessSecret=<<aws secret key>>
    connector.aws-sns-connector.default.aws.clientRegion=<<aws region, note only few regions supports platform application such as US_EAST_1 >>
    connector.aws.account.id= << aws user account id such as 402700149789>>
    connector.aws-sns-connector.default.aws.sns.fcm.platform.application.name=<<aws sns platform application name >>
    connector.aws-sns-connector.default.fcm.application.server.key=<<firebase secret key you have copied in previous steps >>
    ```

1. In WaveMaker application, drag pushNotification prefab into your page.
Go to prefab configuration provides firebase config values you have saved in previous steps.such as
![alt text](https://github.com/wavemaker/aws-sns-connector/blob/master/readmeImages/TokenVariable.jpeg?raw=true)


### Register token and publish message
1. Prefab will generate token using firebase configuration.This token has to register to SNS firebase platform application.
1. Create a java service in WaveMaker application
   ```
   @Autowired
   private AWSSNSFCMConnector awssnsfcmConnector;

   public void registerToken(String token){
     User user = new User(securityService.getLoggedInUser().getUserId(),securityService.getLoggedInUser().getUsername());
     AWSSNSFCMResponse awssnsfcmresponse = awssnsfcmConnector.registerToken(token, user);
   }
   ```

1. Create a service variable for above register token and map prefab token to token, such as 
![alt text](https://github.com/wavemaker/aws-sns-connector/blob/master/readmeImages/TokenVariable.jpeg?raw=true)

1. Publish notification message in your WaveMaker application by using following api
    
    ```
    @Autowired
    private AWSSNSFCMConnector awssnsfcmConnector;

    User user = new User(securityService.getLoggedInUser().getUserId(),securityService.getLoggedInUser().getUsername());
    AWSSNSFCMRequest request = new AWSSNSFCMRequest();
    request.setBody(""Body of the notification"")
                .setIcon("https://static.macupdate.com/products/28356/m/wavemaker-logo.webp?v=1568302524")
                .setTitle("Title of the notification")
                .setIcon_click_url("https://www.wavemaker.com/learn/documentation-reference")
                .setUser(user);
    awssnsfcmConnector.publishMessage(request);
    ```
1. On after publishing notification, you should see notification in your browser as
![alt text](https://github.com/wavemaker/aws-sns-connector/blob/master/readmeImages/NotificationSample.jpeg?raw=true)


