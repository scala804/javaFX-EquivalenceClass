package com.autoTest.javaFXEquivalenceClass.controller;

import com.alibaba.fastjson.JSONObject;

import com.autoTest.javaFXEquivalenceClass.model.StringTypeBean;
import com.autoTest.javaFXEquivalenceClass.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.autoTest.javaFXEquivalenceClass.util.EasyExcelUtil.writeExcelByString;


@Controller
public class GeneratedSamples {
    private static final Logger logger= LoggerFactory.getLogger(GeneratedSamples.class);
    @Autowired
    public GeneratedSamples() {

    }

    public List<List<String>> computationalEquivalence(List listBeans,String fieldType,JSONObject successJson,List successList,List failList){
        List<List<String>> listArrayList=new ArrayList<>();
        try{
            switch (fieldType){
                case "字符串型":
                    stringTypeComputation(listBeans,successJson,successList,failList);
                    break;
                case "整数型":
                    break;
                case "小数型":
                    break;
                case "日期型":
                    break;
                case "枚举型":
                    break;
            }
        }catch (Exception e){
          logger.error(fieldType+"类型的等价类计算错误："+e);
        }
        return listArrayList;
    }

    /**字符串比较*/
    private void stringTypeComputation(List listBeans,JSONObject successJson,List successList,List failList) {
        for(int i=0;i<listBeans.size();i++){
            List<String> listS=new ArrayList<>();
            List<String> listF=new ArrayList<>();
            JSONObject json=(JSONObject) JSONObject.toJSON(listBeans.get(i));
            /**编辑字段名称*/
            String fieldName=json.get("fieldName").toString();
            /**编辑字段序号*/
            String orderNumber=json.get("orderNumber").toString();
            /**最小字符串长度*/
            int minStringLenth=Integer.valueOf(json.get("minStringLenth").toString());
            /**超出下边界的预期结果*/
            String beyondLowerBoundaryExpectedResults=json.get("beyondLowerBoundaryExpectedResults").toString();
            /**最大字符串长度*/
            int bigStringLenth=Integer.valueOf(json.get("bigStringLenth").toString());
            /**超出上边界的预期结果*/
            String beyondUpperBoundaryExpectedResults=json.get("beyondUpperBoundaryExpectedResults").toString();
            /**是否为空*/
            String groupNull=json.get("groupNull").toString();
            /*为空的预期结果*/
            String withNullExpectedResult=json.get("withNullExpectedResult").toString();;

            /**是否区分单双字节*/
            String groupBytes=json.get("groupBytes").toString();
            /**不允许包含的字符*/
            String notAllowedString=json.get("notAllowedString").toString();
            /**包含不允许字符的预期结果*/
            String withDisallowedStringExpectedResult="";
            if(notAllowedString!=null&&notAllowedString.length()>0){
                withDisallowedStringExpectedResult=json.get("withDisallowedStringExpectedResult").toString();
            }

            /**成功数据*/
            int s=0;
            listS.add(s,fieldName);
            listS.add(s+1,orderNumber);
            /**下界边界值**/
            String minStringLenthStrSuccess= RandomUtil.generateString(minStringLenth);
            /**上界边界值**/
            String bigStringLenthStrSuccess=RandomUtil.generateString(bigStringLenth);
            /**中间值**/
            String intervalSectionStrSuccess=RandomUtil.getIntervalSectionStrSimple(bigStringLenth,minStringLenth);

            listS.add(minStringLenthStrSuccess);
            listS.add(bigStringLenthStrSuccess);
            listS.add(intervalSectionStrSuccess);

            if(groupNull=="是"){
                String GroupNull=RandomUtil.generateString(0);
                GroupNull=GroupNull+"|"+withNullExpectedResult;
                listS.add(GroupNull);
            }
            successList.add(listS);
            successJson.put(fieldName,intervalSectionStrSuccess);

            /**失败数据*/
            int f=0;
            listF.add(f,fieldName);
            listF.add(f+1,orderNumber);

            /**超出下界*/

            if(minStringLenth>1){
               String minStringLenthStrFail=RandomUtil.generateString(minStringLenth-1);
                minStringLenthStrFail=minStringLenthStrFail+"|"+beyondLowerBoundaryExpectedResults;
                listF.add(minStringLenthStrFail);
            }else if(minStringLenth==1&&groupNull=="否"){
                String minStringLenthStrFail=RandomUtil.generateString(minStringLenth-1);
                minStringLenthStrFail=minStringLenthStrFail+"|"+beyondLowerBoundaryExpectedResults;
                listF.add(minStringLenthStrFail);
            }


            /**超出上界*/
            String bigStringLenthStrFail="";
            if(bigStringLenth>=1){
                bigStringLenthStrFail=RandomUtil.generateString(bigStringLenth+1);
                bigStringLenthStrFail=bigStringLenthStrFail+"|"+beyondUpperBoundaryExpectedResults;
                listF.add(bigStringLenthStrFail);
            }

            /**不包含字符**/
            if(notAllowedString!=null||notAllowedString.length()>0){
                String notAllowedStringFail=notAllowedString+"|"+withDisallowedStringExpectedResult;
                listF.add(notAllowedStringFail);
            }
            failList.add(listF);
        }
    }

    public boolean writeExcel(List listArrayList){
        boolean booleanSamples = false;
        List<String> list=new ArrayList<>();
        String path=System.getProperty("user.dir");
        path=path+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"excel"+File.separator+"success.xlsx";
        logger.info(String.valueOf("path____________"+path));
        writeExcelByString(path, "等价类生成数据",listArrayList);
        return booleanSamples;
    }

    public List<List<String>> makeLists(List<List<String>> successList,List<List<String>> failList,JSONObject successJson){
        List<String> fieldNamelistStr=new ArrayList<>();
        List<String> baselistStr=new ArrayList<>();
        List<List<String>> lists=new ArrayList<>();
        fieldNamelistStr.add(0,"标识");
        baselistStr.add(0,"S");

        for(int i=0;i<successList.size();i++){
            String fieldName=successList.get(i).get(0);
            fieldNamelistStr.add(i+1,fieldName);
            String s=successJson.getString(fieldName);
            baselistStr.add(i+1,s);
        }
        int sort=0;
        fieldNamelistStr.add("备注");
        lists.add(sort,fieldNamelistStr);

        int endIndex=baselistStr.size();
        for(int i=0;i<successList.size();i++){
            List<String> tempSuccess=successList.get(i);
            for(int m=2;m<tempSuccess.size();m++){
                sort=sort+1;
                List<String> baselistStrSTemp=new ArrayList<>();
                baselistStrSTemp.addAll(baselistStr);
                String temp=tempSuccess.get(m);
                if(m==5){
                    int lastIndex=temp.lastIndexOf("|");
                    String preTemp=temp.substring(0,lastIndex);
                    String lastTemp=temp.substring(lastIndex+1);
                    baselistStrSTemp.add(0,"S");
                    baselistStrSTemp.remove(i+1);
                    baselistStrSTemp.add(i+1,preTemp);
                    baselistStrSTemp.remove(i+2);
                    baselistStrSTemp.add(endIndex,lastTemp);

                }else {
                    baselistStrSTemp.remove(0);
                    baselistStrSTemp.add(0,"S");
                    baselistStrSTemp.remove(i+1);
                    baselistStrSTemp.add(i+1,temp);
               }
                logger.info("success_"+baselistStrSTemp);
                lists.add(sort,baselistStrSTemp);
            }
        }

        for(int i=0;i<failList.size();i++){
            List<String> tempFailList=failList.get(i);
            for(int m=2;m<tempFailList.size();m++){
                sort=sort+1;
                List<String> baselistStrFTemp=new ArrayList<>();
                baselistStrFTemp.addAll(baselistStr);
                String temp=tempFailList.get(m);
                int lastIndex=temp.lastIndexOf("|");
                String preTemp=temp.substring(0,lastIndex);
                String lastTemp=temp.substring(lastIndex+1);
                baselistStrFTemp.remove(0);
                baselistStrFTemp.add(0,"F");
                baselistStrFTemp.remove(i+1);
                baselistStrFTemp.add(i+1,preTemp);
                baselistStrFTemp.add(endIndex,lastTemp);
                lists.add(sort,baselistStrFTemp);
                logger.info("fail_"+baselistStrFTemp);
            }
        }
        return lists;
    }

    public void computationalSampleData(List stringTypeBeans, String fieldType, JSONObject successJson, List<Map<String, List<Map<String, String>>>> normalListDate, List<Map<String, List<Map<String, String>>>> abnormalListDate) {
        try{
            switch (fieldType){
                case "字符串型":
                    stringTypeDateComputation(stringTypeBeans,successJson,normalListDate,abnormalListDate);
                    break;
                case "整数型":
                    break;
                case "小数型":
                    break;
                case "日期型":
                    break;
                case "枚举型":
                    break;
                default:
                    logger.error("没有改类型"+fieldType);
            }
        }catch (Exception e){
            logger.error(fieldType+"类型的等价类计算错误："+e);
        }
    }

    private void stringTypeDateComputation(List<StringTypeBean> stringTypeBeans, JSONObject successJson, List<Map<String, List<Map<String, String>>>> normalListDate, List<Map<String, List<Map<String, String>>>> abnormalListDate) {
         for(int i=0;i<stringTypeBeans.size();i++){
             if(stringTypeBeans.get(i).getFieldName()!=null){
                try {
                    String bigStringLength= stringTypeBeans.get(i).getBigStringLenth();
                    String minStringLength=stringTypeBeans.get(i).getMinStringLenth();
                    String isGroupBytes=stringTypeBeans.get(i).getIsGroupBytes();
                    int bigIntLength=Integer.valueOf(bigStringLength);
                    int minIntLength=Integer.valueOf(minStringLength);
                    if(bigIntLength>minIntLength+1){

                    }
                }catch (Exception e){

                }
              /**操作成功，对应业务规则1,2,3,4**/
              /**规则1，数据样本验证正常有效的字符串（中间值）**/
              Map<String,List<Map<String,String>>> normalMap=new HashMap<>();

              
             }
         }
    }
}
