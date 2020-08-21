package com.wavemaker.connector.aws.sns.connector.pushnotification;

import java.util.List;

import com.wavemaker.runtime.connector.annotation.WMConnector;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 7/8/20
 */
@WMConnector(name = "aws-sns-connector",
        description = "This connector exposes api to create SNS push notification application and manage end points")
public interface AWSSNSPushNotificationConnector {

    /**
     * Creates Firebase Cloud message platform SNS application in AWS SNS.
     *
     * @param name name of Firebase Cloud message platform SNS application
     * @return ARN(Amazon resource name) of the Firebase Cloud message platform SNS application
     */
    public String createFCMPlatformApplication(String name);

    /**
     * Deletes SNS Cloud message platform application from AWS SNS
     *
     * @param platformApplicationArn ARN(Amazon resource name) platform SNS application
     */
    public void deletePlatformApplication(String platformApplicationArn);

    /**
     * List of platform applications in SNS
     *
     * @return List of platformApplicationArn
     */
    public List<String> listPlatformApplication();

    /**
     * Verify platform applications in SNS
     *
     * @param platformApplicationArn ARN(Amazon resource name) platform SNS application
     * @return true if platform application exist in AWS SNS, else false
     */
    public Boolean isExistPlatformApplication(String platformApplicationArn);

    /**
     * Add token endpoint to SNS platform application
     *
     * @param platformApplicationArn ARN(Amazon resource name) platform SNS application
     * @param token                  Client token
     * @return token end point Arn
     */
    public String addTokenToPlatformApplication(String platformApplicationArn, String token);

    /**
     * Delete token endpoint from SNS platform application
     *
     * @param platformApplicationArn ARN(Amazon resource name) platform SNS application
     * @param endPointArn            Token end point Arn
     */
    public void deleteTokenEndPointArnFromPlatformApplication(String platformApplicationArn, String endPointArn);

    /**
     * List token endpoints with in SNS platform application
     *
     * @param platformApplicationArn ARN(Amazon resource name) platform SNS application
     * @return list of token endpoints Arn
     */
    public List<String> listTokenEndPointArnsFromPlatformApplication(String platformApplicationArn);

    /**
     * Verify token endpoint exist with in SNS platform application
     *
     * @param platformApplicationArn ARN(Amazon resource name) platform SNS application
     * @param endPointArn            token endpoint Arn
     * @return true if token endpoint arn exist, else false
     */
    public boolean isTokenEndPointArnsExist(String platformApplicationArn, String endPointArn);
}
