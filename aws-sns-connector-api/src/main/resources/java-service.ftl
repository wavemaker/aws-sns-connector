/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
 package ${packageName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import com.wavemaker.connector.aws.sns.connector.fcm.AWSSNSFCMConnector;
import com.wavemaker.connector.aws.sns.model.User;
import com.wavemaker.connector.aws.sns.model.AWSSNSFCMResponse;
import com.wavemaker.connector.aws.sns.model.AWSSNSFCMRequest;
//import ${packageName}.model.*;

 /**
  * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
  * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
  *
  * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
  * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
  *
  * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
  * Complex Types/Objects will become part of the Request body in the generated API.
  *
  * NOTE: We do not recommend using method overloading on client exposed methods.
  */
@ExposeToClient
public class ${className} {

    private static final Logger logger = LoggerFactory.getLogger(${className}.class);

	  @Autowired
    private AWSSNSFCMConnector awssnsConnector;

    public void registerToken(String token, String userId, String userName){
         User user = new User(userId, userName);
         AWSSNSFCMResponse awssnsResponse = awssnsConnector.registerToken(token, user);
    }

	public void publishMessage(String body,String icon,String title,String userId, String userName) {
         User user = new User(userId, userName);
         AWSSNSFCMRequest request = new AWSSNSFCMRequest();
         request.setBody(body)
                 .setIcon(icon)
                 .setTitle(title)
                 .setIcon_click_url("https://www.wavemaker.com/learn/documentation-reference")
                 .setUser(user);
         awssnsConnector.publishMessage(request);
    }
}
