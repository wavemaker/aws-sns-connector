package com.wavemaker.connector.aws.sns.model;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 10/8/20
 */
public class AWSSNSMessageAttributeValue {

    private String dataType;
    private String stringValue;
    private java.nio.ByteBuffer binaryValue;

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public java.nio.ByteBuffer getBinaryValue() {
        return this.binaryValue;
    }

    public void setBinaryValue(java.nio.ByteBuffer binaryValue) {
        this.binaryValue = binaryValue;
    }


}
