package com.wavemaker.connector.aws.sns.connector.pushnotification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.model.*;
import com.wavemaker.connector.aws.sns.props.AWSProps;
import com.wavemaker.connector.aws.sns.service.AWSSNSService;

import com.wavemaker.connector.aws.sns.connector.pushnotification.AWSSNSPushNotificationConnector;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 7/8/20
 */
@Service
@Primary
public class AWSSNSPushNotificationConnectorImpl implements AWSSNSPushNotificationConnector {

    @Autowired
    AWSProps awsProps;

    @Autowired
    AWSSNSService awssnsService;

    @Override
    public String createFCMPlatformApplication(String name) {
        CreatePlatformApplicationRequest createPlatformApplicationRequest = new CreatePlatformApplicationRequest();
        createPlatformApplicationRequest.setName(name);
        createPlatformApplicationRequest.setPlatform("GCM");
        Map<String, String> attributes = new HashMap<>();
        attributes.put("PlatformCredential", awsProps.getFcm_server_key());
        createPlatformApplicationRequest.setAttributes(attributes);
        CreatePlatformApplicationResult platformApplication = awssnsService.getSNSClient().createPlatformApplication(createPlatformApplicationRequest);
        return platformApplication.getPlatformApplicationArn();
    }


    @Override
    public void deletePlatformApplication(String platformApplicationArn) {
        if (isExistPlatformApplication(platformApplicationArn)) {
            DeletePlatformApplicationRequest deletePlatformApplicationRequest = new DeletePlatformApplicationRequest();
            awssnsService.getSNSClient().deletePlatformApplication(deletePlatformApplicationRequest.withPlatformApplicationArn(platformApplicationArn));
            return;
        }
        throw new AmazonSNSException("Platform application not found with the given arn " + platformApplicationArn);
    }

    @Override
    public List<String> listPlatformApplication() {
        ListPlatformApplicationsResult listPlatformApplicationsResult = awssnsService.getSNSClient().listPlatformApplications();
        List<PlatformApplication> platformApplications = listPlatformApplicationsResult.getPlatformApplications();
        List<String> platformArn = new ArrayList<>();
        for (PlatformApplication application : platformApplications) {
            platformArn.add(application.getPlatformApplicationArn());
        }
        return platformArn;
    }

    @Override
    public Boolean isExistPlatformApplication(String platformApplicationArn) {
        List<String> arns = listPlatformApplication();
        for (String arn : arns) {
            if (arn.equals(platformApplicationArn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String addTokenToPlatformApplication(String platformApplicationArn, String token) {
        ListPlatformApplicationsResult listPlatformApplicationsResult = awssnsService.getSNSClient().listPlatformApplications();
        List<PlatformApplication> platformApplications = listPlatformApplicationsResult.getPlatformApplications();
        for (PlatformApplication application : platformApplications) {
            if (application.getPlatformApplicationArn().equals(platformApplicationArn)) {
                CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
                request.setToken(token);
                request.setPlatformApplicationArn(application.getPlatformApplicationArn());
                CreatePlatformEndpointResult platformEndpoint = awssnsService.getSNSClient().createPlatformEndpoint(request);
                return platformEndpoint.getEndpointArn();
            }
        }
        throw new AmazonSNSException("Platform application not found with the given arn " + platformApplicationArn);
    }

    @Override
    public void deleteTokenEndPointArnFromPlatformApplication(String platformApplicationArn, String endPointArn) {
        if (isTokenEndPointArnsExist(platformApplicationArn, endPointArn)) {
            DeleteEndpointRequest deleteEndpointRequest = new DeleteEndpointRequest();
            deleteEndpointRequest.setEndpointArn(endPointArn);
            awssnsService.getSNSClient().deleteEndpoint(deleteEndpointRequest);
            return;
        }
        throw new AmazonSNSException("End point " + endPointArn + " does not exist in the platform application " + platformApplicationArn);
    }

    @Override
    public List<String> listTokenEndPointArnsFromPlatformApplication(String platformApplicationArn) {
        if (isExistPlatformApplication(platformApplicationArn)) {
            ListEndpointsByPlatformApplicationRequest applicationRequest = new ListEndpointsByPlatformApplicationRequest();
            applicationRequest.setPlatformApplicationArn(platformApplicationArn);
            ListEndpointsByPlatformApplicationResult listEndpointsByPlatformApplicationResult = awssnsService.getSNSClient().listEndpointsByPlatformApplication(applicationRequest);
            List<String> endpoints = new ArrayList<>();
            for (Endpoint endpoint : listEndpointsByPlatformApplicationResult.getEndpoints()) {
                endpoints.add(endpoint.getEndpointArn());
            }
            return endpoints;
        }
        throw new AmazonSNSException("Platform application not found with the given arn " + platformApplicationArn);
    }

    @Override
    public boolean isTokenEndPointArnsExist(String platformApplicationArn, String endPointArn) {
        List<String> tokenEndPoints = listTokenEndPointArnsFromPlatformApplication(platformApplicationArn);
        for (String endPoint : tokenEndPoints) {
            if (endPoint.equals(endPointArn)) {
                return true;
            }
        }
        return false;
    }
}
