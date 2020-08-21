package com.wavemaker.connector.aws.sns.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 9/8/20
 */
public class AWSSNSSubscribeResponse implements Serializable, Cloneable {
    /**
     * <p>
     * The subscription's ARN.
     * </p>
     */
    private String subscriptionArn;
    /**
     * <p>
     * The subscription's owner.
     * </p>
     */
    private String owner;
    /**
     * <p>
     * The subscription's protocol.
     * </p>
     */
    private String protocol;
    /**
     * <p>
     * The subscription's endpoint (format depends on the protocol).
     * </p>
     */
    private String endpoint;
    /**
     * <p>
     * The ARN of the subscription's topic.
     * </p>
     */
    private String topicArn;

    /**
     * <p>
     * The subscription's ARN.
     * </p>
     *
     * @return The subscription's ARN.
     */

    public String getSubscriptionArn() {
        return this.subscriptionArn;
    }

    /**
     * <p>
     * The subscription's ARN.
     * </p>
     *
     * @param subscriptionArn The subscription's ARN.
     */

    public void setSubscriptionArn(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
    }

    /**
     * <p>
     * The subscription's ARN.
     * </p>
     *
     * @param subscriptionArn The subscription's ARN.
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public AWSSNSSubscribeResponse withSubscriptionArn(String subscriptionArn) {
        setSubscriptionArn(subscriptionArn);
        return this;
    }

    /**
     * <p>
     * The subscription's owner.
     * </p>
     *
     * @return The subscription's owner.
     */

    public String getOwner() {
        return this.owner;
    }

    /**
     * <p>
     * The subscription's owner.
     * </p>
     *
     * @param owner The subscription's owner.
     */

    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * <p>
     * The subscription's owner.
     * </p>
     *
     * @param owner The subscription's owner.
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public AWSSNSSubscribeResponse withOwner(String owner) {
        setOwner(owner);
        return this;
    }

    /**
     * <p>
     * The subscription's protocol.
     * </p>
     *
     * @return The subscription's protocol.
     */

    public String getProtocol() {
        return this.protocol;
    }

    /**
     * <p>
     * The subscription's protocol.
     * </p>
     *
     * @param protocol The subscription's protocol.
     */

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * <p>
     * The subscription's protocol.
     * </p>
     *
     * @param protocol The subscription's protocol.
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public AWSSNSSubscribeResponse withProtocol(String protocol) {
        setProtocol(protocol);
        return this;
    }

    /**
     * <p>
     * The subscription's endpoint (format depends on the protocol).
     * </p>
     *
     * @return The subscription's endpoint (format depends on the protocol).
     */

    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * <p>
     * The subscription's endpoint (format depends on the protocol).
     * </p>
     *
     * @param endpoint The subscription's endpoint (format depends on the protocol).
     */

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * <p>
     * The subscription's endpoint (format depends on the protocol).
     * </p>
     *
     * @param endpoint The subscription's endpoint (format depends on the protocol).
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public AWSSNSSubscribeResponse withEndpoint(String endpoint) {
        setEndpoint(endpoint);
        return this;
    }

    /**
     * <p>
     * The ARN of the subscription's topic.
     * </p>
     *
     * @return The ARN of the subscription's topic.
     */

    public String getTopicArn() {
        return this.topicArn;
    }

    /**
     * <p>
     * The ARN of the subscription's topic.
     * </p>
     *
     * @param topicArn The ARN of the subscription's topic.
     */

    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }

    /**
     * <p>
     * The ARN of the subscription's topic.
     * </p>
     *
     * @param topicArn The ARN of the subscription's topic.
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public AWSSNSSubscribeResponse withTopicArn(String topicArn) {
        setTopicArn(topicArn);
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and debugging.
     *
     * @return A string representation of this object.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSubscriptionArn() != null)
            sb.append("SubscriptionArn: ").append(getSubscriptionArn()).append(",");
        if (getOwner() != null)
            sb.append("Owner: ").append(getOwner()).append(",");
        if (getProtocol() != null)
            sb.append("Protocol: ").append(getProtocol()).append(",");
        if (getEndpoint() != null)
            sb.append("Endpoint: ").append(getEndpoint()).append(",");
        if (getTopicArn() != null)
            sb.append("TopicArn: ").append(getTopicArn());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof AWSSNSSubscribeResponse == false)
            return false;
        AWSSNSSubscribeResponse other = (AWSSNSSubscribeResponse) obj;
        if (other.getSubscriptionArn() == null ^ this.getSubscriptionArn() == null)
            return false;
        if (other.getSubscriptionArn() != null && other.getSubscriptionArn().equals(this.getSubscriptionArn()) == false)
            return false;
        if (other.getOwner() == null ^ this.getOwner() == null)
            return false;
        if (other.getOwner() != null && other.getOwner().equals(this.getOwner()) == false)
            return false;
        if (other.getProtocol() == null ^ this.getProtocol() == null)
            return false;
        if (other.getProtocol() != null && other.getProtocol().equals(this.getProtocol()) == false)
            return false;
        if (other.getEndpoint() == null ^ this.getEndpoint() == null)
            return false;
        if (other.getEndpoint() != null && other.getEndpoint().equals(this.getEndpoint()) == false)
            return false;
        if (other.getTopicArn() == null ^ this.getTopicArn() == null)
            return false;
        if (other.getTopicArn() != null && other.getTopicArn().equals(this.getTopicArn()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getSubscriptionArn() == null) ? 0 : getSubscriptionArn().hashCode());
        hashCode = prime * hashCode + ((getOwner() == null) ? 0 : getOwner().hashCode());
        hashCode = prime * hashCode + ((getProtocol() == null) ? 0 : getProtocol().hashCode());
        hashCode = prime * hashCode + ((getEndpoint() == null) ? 0 : getEndpoint().hashCode());
        hashCode = prime * hashCode + ((getTopicArn() == null) ? 0 : getTopicArn().hashCode());
        return hashCode;
    }

    @Override
    public AWSSNSSubscribeResponse clone() {
        try {
            return (AWSSNSSubscribeResponse) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

}
