package com.wavemaker.connector.aws.sns.connector.fcm;

import com.wavemaker.connector.aws.sns.model.AWSSNSFCMRequest;
import com.wavemaker.connector.aws.sns.model.AWSSNSFCMResponse;
import com.wavemaker.connector.aws.sns.model.User;
import com.wavemaker.runtime.connector.annotation.WMConnector;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 10/8/20
 */
@WMConnector(name = "aws-sns-connector",
        description = "This connector is used for demo aws sns push notifications")
public interface AWSSNSFCMConnector {

    public AWSSNSFCMResponse registerToken(String token, User user);

    public void publishMessage(AWSSNSFCMRequest request);

}
