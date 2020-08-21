package com.wavemaker.connector.aws.sns.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 7/8/20
 */
@Service
public class AWSProps {

    @Value("${aws.clientRegion}")
    private Regions clientRegion;

    @Value("${fcm.application.server.key}")
    private String fcm_server_key;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.accessSecret}")
    private String accessSecret;

    @Value("${aws.sns.fcm.platform.application.name}")
    private String aws_sns_fcm_application_name;

    @Value("${aws.account.id}")
    private String aws_account_id;

    public Regions getClientRegion() {
        return clientRegion;
    }

    public String getFcm_server_key() {
        return fcm_server_key;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public String getAws_sns_fcm_application_name() {
        return aws_sns_fcm_application_name;
    }

    public String getAws_account_id() {
        return aws_account_id;
    }
}
