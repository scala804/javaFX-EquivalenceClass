package com.autoTest.javaFXEquivalenceClass.model;


public class StringTypeBean {

    private String uuid;
    private  String orderNumber;
    private  String fieldID;
    private  String  fieldName;
    private  String  fieldType;
    private  String  fieldLength;
    private  String  fieldOtherType;
    private  String  minStringLenth;
    private  String beyondLowerBoundaryExpectedResults;
    private  String bigStringLenth;
    private  String beyondUpperBoundaryExpectedResults;

    private  String groupNull;
    private  String groupBytes;

    private  String withNullExpectedResult;
    private  String notAllowedString;
    private  String withDisallowedStringExpectedResult;

    private String choiceFieldEmpty;
    private String isGroupBytes;

    private String fieldNormalData;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getfieldType() {
        return fieldType;
    }



    public String getIsGroupBytes() {
        return isGroupBytes;
    }

    public void setIsGroupBytes(String isGroupBytes) {
        this.isGroupBytes = isGroupBytes;
    }

    public String getFieldNormalData() {
        return fieldNormalData;
    }

    public void setFieldNormalData(String fieldNormalData) {
        this.fieldNormalData = fieldNormalData;
    }



    public StringTypeBean(String orderNumber, String fieldID, String fieldName, String fieldType, String fieldLength, String choiceFieldEmpty, String fieldOtherType, String fieldOtherType1, String minStringLenth, String beyondLowerBoundaryExpectedResults, String bigStringLenth, String beyondUpperBoundaryExpectedResults, String groupNull, String groupBytes, String withNullExpectedResult, String notAllowedString, String withDisallowedStringExpectedResult,String fieldNormalData) {
        this.orderNumber = orderNumber;
        this.fieldID = fieldID;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldLength = fieldLength;
        this.choiceFieldEmpty = choiceFieldEmpty;
        this.fieldOtherType = fieldOtherType;
        this.minStringLenth = minStringLenth;
        this.beyondLowerBoundaryExpectedResults = beyondLowerBoundaryExpectedResults;
        this.bigStringLenth = bigStringLenth;
        this.beyondUpperBoundaryExpectedResults = beyondUpperBoundaryExpectedResults;
        this.groupNull = groupNull;
        this.groupBytes = groupBytes;
        this.withNullExpectedResult = withNullExpectedResult;
        this.notAllowedString = notAllowedString;
        this.withDisallowedStringExpectedResult = withDisallowedStringExpectedResult;
        this.fieldNormalData = fieldNormalData;
    }

    public StringTypeBean() {

    }


    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }


    public void setFieldOtherType(String fieldOtherType) {
        this.fieldOtherType = fieldOtherType;
    }

    public void setMinStringLenth(String minStringLenth) {
        this.minStringLenth = minStringLenth;
    }

    public void setBeyondLowerBoundaryExpectedResults(String beyondLowerBoundaryExpectedResults) {
        this.beyondLowerBoundaryExpectedResults = beyondLowerBoundaryExpectedResults;
    }

    public void setBigStringLenth(String bigStringLenth) {
        this.bigStringLenth = bigStringLenth;
    }

    public void setBeyondUpperBoundaryExpectedResults(String beyondUpperBoundaryExpectedResults) {
        this.beyondUpperBoundaryExpectedResults = beyondUpperBoundaryExpectedResults;
    }

    public void setGroupNull(String groupNull) {
        this.groupNull = groupNull;
    }

    public void setGroupBytes(String groupBytes) {
        this.groupBytes = groupBytes;
    }

    public void setWithNullExpectedResult(String withNullExpectedResult) {
        this.withNullExpectedResult = withNullExpectedResult;
    }

    public void setNotAllowedString(String notAllowedString) {
        this.notAllowedString = notAllowedString;
    }

    public void setWithDisallowedStringExpectedResult(String withDisallowedStringExpectedResult) {
        this.withDisallowedStringExpectedResult = withDisallowedStringExpectedResult;
    }

    public String getOrderNumber() {
        return orderNumber;
    }



    public String getFieldID() {
        return fieldID;
    }

    public String getFieldName() {
        return fieldName;
    }


    public String getFieldLength() {
        return fieldLength;
    }


    public String getFieldOtherType() {
        return fieldOtherType;
    }

    public String getMinStringLenth() {
        return this.minStringLenth;
    }

    public String getBeyondLowerBoundaryExpectedResults() {
        return beyondLowerBoundaryExpectedResults;
    }

    public String getBigStringLenth() {
        return bigStringLenth;
    }

    public String getBeyondUpperBoundaryExpectedResults() {
        return beyondUpperBoundaryExpectedResults;
    }

    public String getGroupNull() {
        return groupNull;
    }

    public String getGroupBytes() {
        return groupBytes;
    }

    public String getWithNullExpectedResult() {
        return withNullExpectedResult;
    }

    public String getNotAllowedString() {
        return notAllowedString;
    }

    public String getWithDisallowedStringExpectedResult() {
        return withDisallowedStringExpectedResult;
    }

    public String getChoiceFieldEmpty() {
        return choiceFieldEmpty;
    }

    public void setChoiceFieldEmpty(String choiceFieldEmpty) {
        this.choiceFieldEmpty = choiceFieldEmpty;
    }

    public void setfieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}

