package com.autoTest.javaFXEquivalenceClass.model;


import static com.autoTest.javaFXEquivalenceClass.base.ConsField.UUID_DEFAULT_VALUE;
import static java.lang.System.currentTimeMillis;

public class Field {

    private  String orderNumber;
    private  String fieldID;
    private  String  name;
    private  String  fieldType;
    private String  fieldLength;
    private   String  choiceFieldEmpty;
    private   String  fieldOtherType;
    private   String uuid;
    private   String isGroupBytes;

    public Field(String orderNumber,  String fieldID,  String name,  String fieldType,  String fieldLength,  String choiceFieldEmpty,  String fieldOtherType,  String uuid, String isGroupBytes) {
        this.orderNumber = orderNumber;
        this.fieldID = fieldID;
        this.name = name;
        this.fieldType = fieldType;
        this.fieldLength = fieldLength;
        this.choiceFieldEmpty = choiceFieldEmpty;
        this.fieldOtherType = fieldOtherType;
        this.uuid = uuid;
        this.isGroupBytes = isGroupBytes;

    }
    /**
     * Default constructor.
     */
    public Field() {
       this.orderNumber = new String(""+currentTimeMillis());
        this.fieldID = "100";
        this.name = "默认";
        this.fieldType = "String";
        this.fieldLength = "20";
        this.choiceFieldEmpty ="否";
        this.fieldOtherType = "否";
        this.uuid = UUID_DEFAULT_VALUE;
        this.isGroupBytes ="否";
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getFieldID() {
        return fieldID;
    }

    public String getName() {
        return name;
    }



    public String getFieldLength() {
        return fieldLength;
    }

    public String getFieldOtherType() {
        return fieldOtherType;
    }

    public String getUuid() {
        return uuid;
    }

    public String getIsGroupBytes() {
        return isGroupBytes;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }

    public void setFieldOtherType(String fieldOtherType) {
        this.fieldOtherType = fieldOtherType;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setIsGroupBytes(String isGroupBytes) {
        this.isGroupBytes = isGroupBytes;
    }

    public String getChoiceFieldEmpty() {
        return choiceFieldEmpty;
    }

    public void setChoiceFieldEmpty(String choiceFieldEmpty) {
        this.choiceFieldEmpty = choiceFieldEmpty;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
