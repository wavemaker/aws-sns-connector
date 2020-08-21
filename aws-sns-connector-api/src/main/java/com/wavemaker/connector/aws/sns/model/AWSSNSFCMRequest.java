package com.wavemaker.connector.aws.sns.model;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 11/8/20
 */
public class AWSSNSFCMRequest {
    private User user;
    private String title;
    private String body;
    private String icon;
    private String icon_click_url;

    public User getUser() {
        return user;
    }

    public AWSSNSFCMRequest setUser(User user) {
        this.user = user;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AWSSNSFCMRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public AWSSNSFCMRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public AWSSNSFCMRequest setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getIcon_click_url() {
        return icon_click_url;
    }

    public AWSSNSFCMRequest setIcon_click_url(String icon_click_url) {
        this.icon_click_url = icon_click_url;
        return this;
    }
}
