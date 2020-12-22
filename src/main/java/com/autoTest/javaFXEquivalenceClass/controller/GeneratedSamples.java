package com.autoTest.javaFXEquivalenceClass.controller;


import com.alibaba.fastjson.JSONObject;

import com.autoTest.javaFXEquivalenceClass.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static com.autoTest.javaFXEquivalenceClass.base.ConsField.*;
import static com.autoTest.javaFXEquivalenceClass.util.EasyExcelUtil.writeExcelByString;
import static com.autoTest.javaFXEquivalenceClass.util.RandomUtil.*;
import static com.autoTest.javaFXEquivalenceClass.util.RandomUtil.dateEqually;


@Controller
public class GeneratedSamples {
    private static final Logger logger = LoggerFactory.getLogger(GeneratedSamples.class);
    int intParamsNumber;

    @Autowired
    public GeneratedSamples() {

    }

    public boolean writeExcel(List listArrayList,String excelPath,String excelFileTableName) {
        boolean booleanSamples;
        logger.info("Excel保存路径：" + excelPath);
        booleanSamples=writeExcelByString(excelPath, excelFileTableName, listArrayList);
        return booleanSamples;
    }

    public List<List<String>> makeLists(List<Map<String, List<Map<String, String>>>> successList, List<Map<String, List<Map<String, String>>>> failList, JSONObject successJson) {
        intParamsNumber=0;
        List<List<String>> lists = new ArrayList<>();
        String paramType=FILED_NAME;
        List<String> listParam=getList(successJson,paramType,"");
        lists.add(0,listParam);
        String valueType=FILED_PARAMS;
        String success="S";
        String fail="F";
        List<String> listParamValue=getList(successJson,valueType,success);
        if(listParamValue.size()>0){
            listParamValue.add("操作成功");
        }
        lists.add(1,listParamValue);

        getListList(lists,successList,successJson,success);
        getListList(lists,failList,successJson,fail);
        return lists;
    }


    public List<List<String>> getListList(List<List<String>> lists,List<Map<String, List<Map<String, String>>>> litMapList, JSONObject successJson,String sf){
        String valueType=FILED_PARAMS;
        for(int i=0;i<litMapList.size();i++){
            Map<String, List<Map<String, String>>> mapList=litMapList.get(i);
            Set<String> keys = mapList.keySet();
            for(String key:keys){
                if(mapList.get(key)!=null){
                    List<Map<String, String>> listMap=mapList.get(key);
                    for(int j=0;j<listMap.size();j++){
                        Map<String,String> map=listMap.get(j);
                        Set<String> mapKeys = map.keySet();
                        for(String mapKey:mapKeys){
                            String value=map.get(mapKey);
                            JSONObject successObject = JSONObject.parseObject(successJson.toJSONString());
                            successObject.put(key,mapKey);
                            successObject.put("exceptResult",value);
                            List<String> listParamKey =getList(successObject,valueType,sf);
                            lists.add(listParamKey);
                            System.out.println("测试数据："+listParamKey);
                        }
                    }
                }
            }
        }
        return lists;
    }

    public List<String> getList( JSONObject successJson,String type,String sf){
        List<String> listParam=new ArrayList<>();
        if(FILED_NAME.equals(type)){
            listParam.add("样本序号");
            java.util.Iterator it = successJson.entrySet().iterator();
            while(it.hasNext()){
                java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                listParam.add(entry.getKey().toString());
            }
            listParam.add("期望结果");
            listParam.add("备注");
        }
        if(FILED_PARAMS.equals(type)){
            java.util.Iterator it = successJson.entrySet().iterator();
            intParamsNumber++;
            listParam.add(sf+"-"+"样本"+intParamsNumber);
            while(it.hasNext()){
                java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                listParam.add(entry.getValue().toString());
            }
        }
        return listParam;
    }

    public void stringTypeDateComputation(List<StringTypeBean> stringTypeBeans, JSONObject successJson, List<Map<String, List<Map<String, String>>>> normalListDate, List<Map<String, List<Map<String, String>>>> abnormalListDate) {

        for (int i = 0; i < stringTypeBeans.size(); i++) {
            if (stringTypeBeans.get(i).getFieldName() != null) {
                String fieldNameKey = stringTypeBeans.get(i).getFieldName();
                Map<String, List<Map<String, String>>> fieldNameNormalMap = new HashMap<>();
                Map<String, List<Map<String, String>>> fieldNameAbnormalMap = new HashMap<>();
                try {
                    String bigStringLength = stringTypeBeans.get(i).getBigStringLenth();
                    String minStringLength = stringTypeBeans.get(i).getMinStringLenth();
                    String isGroupBytes = stringTypeBeans.get(i).getIsGroupBytes();
                    String notAllowedString = stringTypeBeans.get(i).getNotAllowedString();
                    String choiceFieldEmptyKey = stringTypeBeans.get(i).getChoiceFieldEmpty();
                    String beyondLowerBoundaryExpectedResults = SYSTEM_DEFAULT_FAIL_VALUE;
                    if (!"".equals(stringTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults())) {
                        beyondLowerBoundaryExpectedResults = stringTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults();
                    }
                    String beyondUpperBoundaryExpectedResults = SYSTEM_DEFAULT_FAIL_VALUE;
                    if (!"".equals(stringTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults())) {
                        beyondUpperBoundaryExpectedResults = stringTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults();
                    }
                    String withNullExpectedResult = SYSTEM_DEFAULT_FAIL_VALUE;
                    if (!"".equals(stringTypeBeans.get(i).getWithNullExpectedResult())) {
                        withNullExpectedResult = stringTypeBeans.get(i).getWithNullExpectedResult();
                    }
                    String withDisallowedStringExpectedResult = SYSTEM_DEFAULT_FAIL_VALUE;
                    if (!"".equals(stringTypeBeans.get(i).getWithDisallowedStringExpectedResult())) {
                        withDisallowedStringExpectedResult = stringTypeBeans.get(i).getWithDisallowedStringExpectedResult();
                    }
                    Map rusultMap = new HashMap();
                    rusultMap.put("beyondLowerBoundaryExpectedResults", beyondLowerBoundaryExpectedResults);
                    rusultMap.put("beyondUpperBoundaryExpectedResults", beyondUpperBoundaryExpectedResults);
                    rusultMap.put("withNullExpectedResult", withNullExpectedResult);
                    rusultMap.put("withDisallowedStringExpectedResult", withDisallowedStringExpectedResult);
                    String fieldNormalData = "";
                    if (!"".equals(stringTypeBeans.get(i).getFieldNormalData()) ) {
                        fieldNormalData = stringTypeBeans.get(i).getFieldNormalData();
                    }

                    int bigIntLength = Integer.valueOf(bigStringLength);
                    int minIntLength = Integer.valueOf(minStringLength);
                    int middleStringLength;
                    //业务规则 1:任意上边界和下边界之间的字符串
                    if (!"".equals(fieldNormalData)) {
                        successJson.put(fieldNameKey, fieldNormalData);
                    } else {
                        if (bigIntLength > minIntLength + 1) {
                            middleStringLength = minIntLength + (bigIntLength - minIntLength) / 2;
                            String middleStrngValue = generateString(middleStringLength);
                            successJson.put(fieldNameKey, middleStrngValue);
                        }
                    }

                    Map<String, String> abnormalMap = new HashMap<>();
                    Map<String, String> normalMap = new HashMap<>();

                    //字符串生成成功的业务规则2、3、4
                    //数据样本验证有效上边界（最大长度的有效字符串）,先判断单双字节
                    List<Map<String, String>> normalListMap = new ArrayList<>();
                    List<Map<String, String>> abnormalListMap = new ArrayList<>();
                   //上边界正常值
                   if(!"".equals(isGroupBytes)){
                       choiceFieldNoOrYes(bigIntLength, normalMap,0,isGroupBytes);
                       choiceFieldNoOrYes(bigIntLength, abnormalMap,1,isGroupBytes);
                       choiceFieldNoOrYes(minIntLength, normalMap,0,isGroupBytes);
                       choiceFieldNoOrYes(minIntLength, abnormalMap,-1,isGroupBytes);
                   }

                    String keyValue="字符串字段设置为空";
                    if(CHOICE_FIELD_NO.equals(choiceFieldEmptyKey)){
                        abnormalMap.put("",keyValue);
                    }
                    if(CHOICE_FIELD_YES.equals(choiceFieldEmptyKey)){
                        abnormalMap.put("",keyValue);
                    }
                    //特殊字符串
                    String standardCharactersKey = getStandardCharacters(bigIntLength, notAllowedString);
                    /* String standardCharactersValue="验证特殊字符串";*/
                    /*String standardCharactersValue=SYSTEM_DEFAULT_SUCCESS_VALUE;*/
                    if(CHOICE_FIELD_NO.equals(choiceFieldEmptyKey)&&!"".equals(standardCharactersKey)){
                        normalMap.put(standardCharactersKey,SYSTEM_DEFAULT_SUCCESS_VALUE);
                        normalListMap.add(normalMap);
                    }
                    if(!"".equals(notAllowedString)){
                        abnormalMap.put(notAllowedString, withDisallowedStringExpectedResult);
                    }
                    abnormalListMap.add(abnormalMap);
                    //操作失败
                    fieldNameAbnormalMap.put(fieldNameKey, abnormalListMap);
                    abnormalListDate.add(fieldNameAbnormalMap);

                    fieldNameNormalMap.put(fieldNameKey, normalListMap);
                    normalListDate.add(fieldNameNormalMap);

                } catch (Exception e) {
                    logger.error(fieldNameKey + "根据等价类原则，数据生成错误：" + e);
                }
            }
        }
    }



    private void choiceFieldNoOrYes(int length,Map<String,String> map,int addInt,String isGroupBytes){
        String keyValue="";
        //加括号的文字描述“（最大长度）个中文字符
        keyValue=length+addInt+"个中文字符";
        map.put(getFixedLengthChinese(length+1),keyValue);
        //加括号的文字描述“（最大长度）个英文字符”
        keyValue=length+addInt+"个英文字符";
        map.put(getRandomOfLetter(length+addInt),keyValue);
        //加括号的文字描述“（最大长度）个整数数字”
        keyValue=length+addInt+"个整数数字";
        map.put(getRandomOfNumber(length+addInt),keyValue);
        if(length>6){
            Integer chinaNumber=Integer.valueOf(getBetweenMinAndMax(1,length+addInt));
            Integer numberInt=length+addInt-Integer.valueOf(chinaNumber);
            keyValue=length+addInt+"个中文+英文+数字组合的字符串";
            if(CHOICE_FIELD_YES.equals(isGroupBytes)){
                map.put(getFixedLengthChinese(chinaNumber)+generateString(numberInt),keyValue);
            }
        }

    }

    //特殊字符处理
    public static String getStandardCharacters(int bigIntLength, String notAllowedString) {
        String standardCharacters = STANDARD_CHARACTERS;
        if (!"".equals(notAllowedString)) {
            for (int i = 0; i < notAllowedString.length(); i++) {
                String tempString = String.valueOf(notAllowedString.charAt(i));
                if (standardCharacters.contains(tempString)) {
                  String   temp=standardCharacters.replace(tempString, "");
                    standardCharacters=temp;
                }
            }
        }
        if (standardCharacters.length() > bigIntLength) {
            standardCharacters = standardCharacters.substring(0, bigIntLength);
        }
        return standardCharacters;
    }


    public void interTypeDateComputation(List<InterTypeBean> interTypeBeans, JSONObject successJson, List<Map<String, List<Map<String, String>>>> normalListDate, List<Map<String, List<Map<String, String>>>> abnormalListDate) {
        for (int i = 0; i < interTypeBeans.size(); i++) {
            if (interTypeBeans.get(i).getFieldName() != null) {
                String fieldNameKey = interTypeBeans.get(i).getFieldName();
                String choiceFieldEmptyKey = interTypeBeans.get(i).getChoiceFieldEmpty();
                Map<String, List<Map<String, String>>> fieldNameNormalMap = new HashMap<>();
                Map<String, List<Map<String, String>>> fieldNameAbnormalMap = new HashMap<>();
                List<Map<String, String>> normalListMap = new ArrayList<>();
                List<Map<String, String>> abnormalListMap = new ArrayList<>();
                String interBeyondLowerBoundaryKey = interTypeBeans.get(i).getInterBeyondLowerBoundaryValue();
                String interBeyondUpperBoundaryKey = interTypeBeans.get(i).getInterBeyondUpperBoundaryValue();
                int interBeyondLowerBoundary = Integer.valueOf(interBeyondLowerBoundaryKey);
                int interBeyondUpperBoundary = Integer.valueOf(interBeyondUpperBoundaryKey);

                String beyondLowerBoundaryExpectedResults = SYSTEM_DEFAULT_FAIL_VALUE;
                if (!"".equals(interTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults())) {
                    beyondLowerBoundaryExpectedResults = interTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults();
                }

                String beyondUpperBoundaryExpectedResults = SYSTEM_DEFAULT_FAIL_VALUE;
                if (!"".equals(interTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults())) {
                    beyondUpperBoundaryExpectedResults = interTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults();
                }

                String otherNotInterExpectedResults = SYSTEM_DEFAULT_FAIL_VALUE;
                if (!"".equals(interTypeBeans.get(i).getOtherNotInterExpectedResults())) {
                    otherNotInterExpectedResults = interTypeBeans.get(i).getOtherNotInterExpectedResults();
                }

                String withNullExpectedResult = SYSTEM_DEFAULT_FAIL_VALUE;
                if (!"".equals(interTypeBeans.get(i).getWithNullExpectedResult())) {
                    withNullExpectedResult = interTypeBeans.get(i).getWithNullExpectedResult();
                }

                String fieldNormalData = "";
                if (!"".equals(interTypeBeans.get(i).getFieldNormalData())) {
                    fieldNormalData = interTypeBeans.get(i).getFieldNormalData();
                }
                if (!"".equals(fieldNormalData)) {
                    successJson.put(fieldNameKey, fieldNormalData);
                } else {
                    int middleStrngValue = 0;
                    if (interBeyondLowerBoundary > 0 && interBeyondUpperBoundary > 1) {
                        middleStrngValue = getIntervalSectionIntSimple(interBeyondUpperBoundary - 1, interBeyondLowerBoundary);
                    } else {
                        if (interBeyondLowerBoundary < 0 && interBeyondUpperBoundary > 0) {
                            middleStrngValue = 0;
                        }
                    }
                    successJson.put(fieldNameKey, middleStrngValue + "");
                }
                Map<String, String> normalMap = new HashMap<>();
                Map<String, String> abnormalMap = new HashMap<>();
                String interBeyondUpperBoundaryValue = "取“整数型字段编辑页面”输入的上边界值";
                normalMap.put(interBeyondUpperBoundaryKey, SYSTEM_DEFAULT_SUCCESS_VALUE);
                /*String  interBeyondUpperBoundaryAbnormaValue="数据样本验证无效上边界整数型值时，生成1个失败的数据样本,取“上边界值+1”";*/
                abnormalMap.put(String.valueOf(interBeyondUpperBoundary + 1), beyondUpperBoundaryExpectedResults);

                /*String  interBeyondLowerBoundaryValue="取“整数型字段边界页面”输入的下边界值";*/
                normalMap.put(interBeyondLowerBoundaryKey, SYSTEM_DEFAULT_SUCCESS_VALUE);
                /*String  interBeyondLowerBoundaryAbnormalValue="取“整数型字段边界页面”输入的下边界值";*/
                abnormalMap.put(String.valueOf(interBeyondLowerBoundary - 1), beyondLowerBoundaryExpectedResults);
                if (CHOICE_FIELD_YES.equals(choiceFieldEmptyKey)) {
                    /*String  choiceFieldEmptyValue="允许为空，把该字段设置为空作为成功数据样本";*/
                    normalMap.put("", SYSTEM_DEFAULT_SUCCESS_VALUE);
                }
                if (CHOICE_FIELD_NO.equals(choiceFieldEmptyKey)) {
                    abnormalMap.put("", withNullExpectedResult);
                }

                //使用非整数格式的字符串作为第1个失败的数据样本，非整数格式的字符串选取规则为随机选取
                abnormalMap.put(String.valueOf(generationDouble()), otherNotInterExpectedResults);
                abnormalMap.put("A", otherNotInterExpectedResults);
                abnormalMap.put(String.valueOf(generatingFloat()), otherNotInterExpectedResults);
                normalListMap.add(normalMap);
                abnormalListMap.add(abnormalMap);
                fieldNameNormalMap.put(fieldNameKey, normalListMap);
                normalListDate.add(fieldNameNormalMap);
                fieldNameAbnormalMap.put(fieldNameKey, abnormalListMap);
                abnormalListDate.add(fieldNameAbnormalMap);
            }
        }
    }

    public void decimalTypeDateComputation(List<DecimalTypeBean> decimalTypeBeans, JSONObject successJson, List<Map<String, List<Map<String, String>>>> normalListDate, List<Map<String, List<Map<String, String>>>> abnormalListDate) {
        for (int i = 0; i < decimalTypeBeans.size(); i++) {
            if (decimalTypeBeans.get(i).getFieldName() != null) {
                Map<String, List<Map<String, String>>> keyNormalMap = new HashMap<>();
                Map<String, List<Map<String, String>>> keyAbnormalMap = new HashMap<>();
                String fieldNameKey = decimalTypeBeans.get(i).getFieldName();
                String precisionKey = decimalTypeBeans.get(i).getPrecision();
                String decimalBeyondUpperBoundaryKey = decimalTypeBeans.get(i).getDecimalBeyondUpperBoundaryValue();
                String decimalBeyondLowerBoundaryKey = decimalTypeBeans.get(i).getDecimalBeyondLowerBoundaryValue();
                String ChoiceFieldEmptyKey = decimalTypeBeans.get(i).getChoiceFieldEmpty();
                int precisionKeyInt = Integer.valueOf(precisionKey);
                Float Max = Float.valueOf(decimalBeyondUpperBoundaryKey);
                Float Min = Float.valueOf(decimalBeyondLowerBoundaryKey);

                String beyondLowerBoundaryExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
                if(!"".equals(decimalTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults())){
                    beyondLowerBoundaryExpectedResults=decimalTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults();
                }
                String isNullExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
                if(!"".equals(decimalTypeBeans.get(i).getIsNullExpectedResults())){
                    isNullExpectedResults=decimalTypeBeans.get(i).getIsNullExpectedResults();
                }
                String beyondUpperBoundaryExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
                if(!"".equals(decimalTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults())){
                    beyondUpperBoundaryExpectedResults=decimalTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults();
                }
                String decimalIsFormatExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
                if(!"".equals(decimalTypeBeans.get(i).getDecimalIsFormatExpectedResults())){
                    decimalIsFormatExpectedResults=decimalTypeBeans.get(i).getDecimalIsFormatExpectedResults();
                }
                String fieldNormalData;
                if (!"".equals(decimalTypeBeans.get(i).getFieldNormalData())) {
                    fieldNormalData = decimalTypeBeans.get(i).getFieldNormalData();
                    successJson.put(fieldNameKey, fieldNormalData);
                }
                else {
                    fieldNormalData = generationDecimal(precisionKeyInt, Max, Min);
                    successJson.put(fieldNameKey, fieldNormalData);
                }


                Map<String, String> normalMap = new HashMap<>();
                Map<String, String> abnormalMap = new HashMap<>();
                normalMap.put(decimalBeyondUpperBoundaryKey, SYSTEM_DEFAULT_SUCCESS_VALUE);
                normalMap.put(decimalBeyondLowerBoundaryKey, SYSTEM_DEFAULT_SUCCESS_VALUE);
                if (CHOICE_FIELD_YES.equals(ChoiceFieldEmptyKey)) {
                    normalMap.put("", SYSTEM_DEFAULT_SUCCESS_VALUE);
                }
                if (CHOICE_FIELD_NO.equals(ChoiceFieldEmptyKey)) {
                    abnormalMap.put("", isNullExpectedResults);
                }
                List<Map<String, String>> litNormalMap = new ArrayList<>();
                List<Map<String, String>> litabnormalMap = new ArrayList<>();
                litNormalMap.add(normalMap);
                keyNormalMap.put(fieldNameKey, litNormalMap);
                normalListDate.add(keyNormalMap);
                double temptest = Math.pow(10,precisionKeyInt);

                BigDecimal maxB1 = new BigDecimal(Float.toString(Max));
                BigDecimal minB1 = new BigDecimal(Float.toString(Min));
                BigDecimal b1 = new BigDecimal(Double.toString((1/temptest)));
                Float maxSum= maxB1.add(b1).floatValue();
                Float minSum= minB1.subtract(b1).floatValue();
                String zeroPatten="."+generateZeroString(precisionKeyInt);
                DecimalFormat decimalFormat=new DecimalFormat(zeroPatten);//构造方法的字符格式这里如果小数不足2位,会以0补足.
                String p=decimalFormat.format(maxSum);//format 返回的是字符串/构造方法的字符格式这里如果小数不足2位,会以0补足.
                String b=decimalFormat.format(minSum);//format 返回的是字符串
                abnormalMap.put(p, beyondUpperBoundaryExpectedResults);
                abnormalMap.put(b, beyondLowerBoundaryExpectedResults);

                abnormalMap.put(generateString(Integer.valueOf(precisionKey)), decimalIsFormatExpectedResults);
                /*abnormalMap.put("A", decimalIsFormatExpectedResults);*/
                String tempPrecisionKeyInt=generationDecimal(precisionKeyInt+1, Max, Min);
                abnormalMap.put(tempPrecisionKeyInt, decimalIsFormatExpectedResults);
                litabnormalMap.add(abnormalMap);
                keyAbnormalMap.put(fieldNameKey, litabnormalMap);
                abnormalListDate.add(keyAbnormalMap);
            }
        }
    }


    public void dateTypeDateComputation(List<DateTypeBean> dateTypeBeans, JSONObject successJson, List<Map<String, List<Map<String, String>>>> normalListDate, List<Map<String, List<Map<String, String>>>> abnormalListDate) {
        for (int i = 0; i < dateTypeBeans.size(); i++) {
            Map<String, List<Map<String, String>>> keyNormalMap = new HashMap<>();
            Map<String, List<Map<String, String>>> keyAbnormalMap = new HashMap<>();
            String fieldNameKey = dateTypeBeans.get(i).getFieldName();
            String dateBeyondUpperBoundaryValue = dateTypeBeans.get(i).getDateBeyondUpperBoundaryValue();
            String dateBeyondLowerBoundaryValue = dateTypeBeans.get(i).getDateBeyondLowerBoundaryValue();
            String timeBeyondUpperBoundaryValue = dateTypeBeans.get(i).getTimeBeyondUpperBoundaryValue();
            String timeBeyondLowerBoundaryValue = dateTypeBeans.get(i).getTimeBeyondLowerBoundaryValue();

            String beyondLowerBoundaryExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
            if(!"".equals(dateTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults())){
                beyondLowerBoundaryExpectedResults=dateTypeBeans.get(i).getBeyondLowerBoundaryExpectedResults();
            }
            String beyondUpperBoundaryExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
            if(!"".equals(dateTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults())){
                beyondUpperBoundaryExpectedResults=dateTypeBeans.get(i).getBeyondUpperBoundaryExpectedResults();
            }
            String dateIsFormatExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
            if(!"".equals(dateTypeBeans.get(i).getDateIsFormatExpectedResults())){
                dateIsFormatExpectedResults=dateTypeBeans.get(i).getDateIsFormatExpectedResults();
            }
            String dateIsNullExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
            if(!"".equals(dateTypeBeans.get(i).getDateIsNullExpectedResults())){
                dateIsNullExpectedResults=dateTypeBeans.get(i).getDateIsNullExpectedResults();
            }

            String date = getSpecifiedDayAfter(dateBeyondLowerBoundaryValue);
            if (!dateEqually(date, dateBeyondLowerBoundaryValue, "date")) {
                date = "";
            }
            String time = getSpecifiedDayTime(timeBeyondLowerBoundaryValue, 1);
            if (!dateEqually(time, timeBeyondLowerBoundaryValue, "time")) {
                time = "";
            }
            String ChoiceFieldEmpty = dateTypeBeans.get(i).getChoiceFieldEmpty();
            if ("".equals(date)) {
                if (!"".equals(time)) {
                    successJson.put(fieldNameKey, dateBeyondLowerBoundaryValue + "T" + time);
                } else {
                    successJson.put(fieldNameKey, dateBeyondLowerBoundaryValue + "T" + timeBeyondLowerBoundaryValue);
                }
            } else {
                successJson.put(fieldNameKey, date + "T" + time);
            }
            List<Map<String, String>> litNormalMap = new ArrayList<>();
            List<Map<String, String>> litabnormalMap = new ArrayList<>();
            Map<String, String> mormalMap = new HashMap<>();
            Map<String, String> abnormalMap = new HashMap<>();
            mormalMap.put(dateBeyondUpperBoundaryValue + "T" + timeBeyondUpperBoundaryValue, SYSTEM_DEFAULT_SUCCESS_VALUE);
            mormalMap.put(dateBeyondLowerBoundaryValue + "T" + timeBeyondLowerBoundaryValue, SYSTEM_DEFAULT_SUCCESS_VALUE);
            if (CHOICE_FIELD_YES.equals(ChoiceFieldEmpty)) {
                mormalMap.put("", SYSTEM_DEFAULT_SUCCESS_VALUE);
            }
            if (CHOICE_FIELD_NO.equals(ChoiceFieldEmpty)) {
                abnormalMap.put("", dateIsNullExpectedResults);
            }
            String dateBeyondUpperBoundaryValueKey = getSpecifiedDay(dateBeyondUpperBoundaryValue, 1);
            String timeBeyondUpperBoundaryValueKey = getSpecifiedDayTime(timeBeyondUpperBoundaryValue, 1);
            abnormalMap.put(dateBeyondUpperBoundaryValueKey + "T" + timeBeyondUpperBoundaryValueKey, beyondUpperBoundaryExpectedResults);
            String dateBeyondLowerBoundaryValueKey = getSpecifiedDay(dateBeyondLowerBoundaryValue, -1);
            String timeBeyondLowerBoundaryValueKey = getSpecifiedDayTime(timeBeyondLowerBoundaryValue, -1);
            abnormalMap.put(dateBeyondLowerBoundaryValueKey + "T" + timeBeyondLowerBoundaryValueKey, beyondLowerBoundaryExpectedResults);

            abnormalMap.put("2020-10A-32T25:21A:10", dateIsFormatExpectedResults);
            abnormalMap.put("2020-10-32" + "T" + "25:21:10", dateIsFormatExpectedResults);
            litNormalMap.add(mormalMap);
            litabnormalMap.add(abnormalMap);
            keyNormalMap.put(fieldNameKey, litNormalMap);
            keyAbnormalMap.put(fieldNameKey, litabnormalMap);
            normalListDate.add(keyNormalMap);
            abnormalListDate.add(keyAbnormalMap);
        }
    }

    public void enumerationTypeDateComputation(List<EnumerationTypeBean> enumerationTypeBeans, JSONObject successJson, List<Map<String, List<Map<String, String>>>> normalListDate, List<Map<String, List<Map<String, String>>>> abnormalListDate) {
        for (int i = 0; i < enumerationTypeBeans.size(); i++) {
            Map<String, List<Map<String, String>>> keyNormalMap = new HashMap<>();
            Map<String, List<Map<String, String>>> keyAbnormalMap = new HashMap<>();
            String fieldNameKey = enumerationTypeBeans.get(i).getFieldName();
            String enumerationValue = enumerationTypeBeans.get(i).getEnumerationValue();
            String ChoiceFieldEmpty = enumerationTypeBeans.get(i).getChoiceFieldEmpty();
            String successExpectedResults=SYSTEM_DEFAULT_SUCCESS_VALUE;
            if(!"".equals(enumerationTypeBeans.get(i).getSuccessExpectedResults())){
                successExpectedResults=enumerationTypeBeans.get(i).getSuccessExpectedResults();
            }
            String isNullExpectedResults=SYSTEM_DEFAULT_FAIL_VALUE;
            if(!"".equals(enumerationTypeBeans.get(i).getIsNullExpectedResults())){
                isNullExpectedResults=enumerationTypeBeans.get(i).getIsNullExpectedResults();
            }

            String[] arrEnumerationValue = enumerationValue.split("\n|\r");
            int arryKey = getIntervalSectionIntSimple(arrEnumerationValue.length - 1, 0);
            String arrEnumerationValueKey = arrEnumerationValue[arryKey];
            successJson.put(fieldNameKey, arrEnumerationValueKey);
            List<Map<String, String>> litNormalMap = new ArrayList<>();
            List<Map<String, String>> litabnormalMap = new ArrayList<>();
            Map<String, String> mormalMap = new HashMap<>();
            Map<String, String> abnormalMap = new HashMap<>();
            String firstArrEnumerationValue = arrEnumerationValue[0];
            String lastArrEnumerationValue = arrEnumerationValue[arrEnumerationValue.length - 1];
            mormalMap.put(firstArrEnumerationValue, successExpectedResults);
            mormalMap.put(lastArrEnumerationValue, successExpectedResults);
            if (CHOICE_FIELD_YES.equals(ChoiceFieldEmpty)) {
                mormalMap.put("", successExpectedResults);
            }
            if (CHOICE_FIELD_NO.equals(ChoiceFieldEmpty)) {
                abnormalMap.put("", isNullExpectedResults);
            }
            litNormalMap.add(mormalMap);
            litabnormalMap.add(abnormalMap);
            keyNormalMap.put(fieldNameKey, litNormalMap);
            keyAbnormalMap.put(fieldNameKey, litabnormalMap);
            normalListDate.add(keyNormalMap);
            abnormalListDate.add(keyAbnormalMap);
        }
    }

    public static void main(String[] args) {
        int bigIntLength = 8;
        int minIntLength = 4;
        int StringLength;
        StringLength = minIntLength + (bigIntLength - minIntLength) / 2;
        System.out.println(StringLength);

        String tempString = getStandardCharacters(5, "'%_,.*~`!@#$");
        System.out.println(tempString);
    }


}
