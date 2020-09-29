package com.autoTest.javaFXEquivalenceClass.controller;

import com.alibaba.fastjson.JSONObject;
import com.autoTest.javaFXEquivalenceClass.model.*;

import com.autoTest.javaFXEquivalenceClass.util.XmlInterfaceUtils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.autoTest.javaFXEquivalenceClass.base.ConsField.*;
import static com.autoTest.javaFXEquivalenceClass.util.MinTool.*;
import static com.autoTest.javaFXEquivalenceClass.util.ReflexObjectUtil.getMap;
import static com.autoTest.javaFXEquivalenceClass.util.ReflexObjectUtil.map2Bean;


public  class JavaFxMain extends Application {
    private static final Logger logger= LoggerFactory.getLogger(JavaFxMain.class);
    private Stage primaryStage;
    private BorderPane rootLayout;
    private GeneratedSamples generatedSamples=new GeneratedSamples();

    private ObservableList<Field> fieldsData = FXCollections.observableArrayList();
    private List<StringTypeBean> stringTypeBeans;
    private List<InterTypeBean> interTypeBeans;
    private List<DateTypeBean> dateTypeBeans;
    private List<DecimalTypeBean> decimalTypeBeans;
    private List<EnumerationTypeBean> enumerationTypeBeans;
    /**
     * Constructor
     */
    public JavaFxMain() {
        stringTypeBeans=new ArrayList<>();
        interTypeBeans=new ArrayList<>();
        dateTypeBeans=new ArrayList<>();
        decimalTypeBeans=new ArrayList<>();
        enumerationTypeBeans=new ArrayList<>();
        /**
         * 数据初始化，从配置的xml中读取数据
         */
        dataInitialization();
    }

    /**
     * 数据初始化，根据字段类型获取数据
     */
    private void dataInitialization() {
        try {
            String xmlPath=JavaFxMain.class.getResource("/temp/dataXml.xml").toString();
            System.out.println(xmlPath);
           int indexInt=xmlPath.indexOf("/");
            String xmlFilePath;
            int xmlPathLength=xmlPath.length();
            if(xmlPathLength>indexInt){
                xmlFilePath=xmlPath.substring(indexInt+1);
            }else {
                xmlFilePath="";
            }
            ListAllJavaBeans listAllJavaBeans = (ListAllJavaBeans) XmlInterfaceUtils.dataXmltoEntity(ListAllJavaBeans.class, xmlFilePath);
            if(listAllJavaBeans.getListStringTypeBeans()!=null){
                List<StringTypeBean> listStringTypeBeans=listAllJavaBeans.getListStringTypeBeans();
                for(int m=0;m<listStringTypeBeans.size();m++){
                    getDateFromXMl(Collections.singletonList(listStringTypeBeans.get(m)),FIELD_TYPE_STRING);
                }
            }
            if(listAllJavaBeans.getListInterTypeBeans()!=null){
                List<InterTypeBean> listInterTypeBeans=listAllJavaBeans.getListInterTypeBeans();
                for(int i=0;i<listInterTypeBeans.size();i++){
                    getDateFromXMl(Collections.singletonList(listInterTypeBeans.get(i)),FIELD_TYPE_INTER);
                }
            }
            if(listAllJavaBeans.getListDateTypeBeans()!=null){
                List<DateTypeBean> listDateTypeBeans=listAllJavaBeans.getListDateTypeBeans();
                for(int i=0;i<listDateTypeBeans.size();i++){
                    getDateFromXMl(Collections.singletonList(listDateTypeBeans.get(i)),FIELD_TYPE_DECIMAL);
                }
            }
            if(listAllJavaBeans.getListDecimalTypeBeans()!=null){
                List<DecimalTypeBean> listDecimalTypeBeans=listAllJavaBeans.getListDecimalTypeBeans();
                for(int i=0;i<listDecimalTypeBeans.size();i++){
                    getDateFromXMl(Collections.singletonList(listDecimalTypeBeans.get(i)),FIELD_TYPE_DATE);
                }
            }
            if(listAllJavaBeans.getListEnumerationTypeBeans()!=null){
                List<EnumerationTypeBean> listEnumerationTypeBeans=listAllJavaBeans.getListEnumerationTypeBeans();
                for(int i=0;i<listEnumerationTypeBeans.size();i++){
                    getDateFromXMl(Collections.singletonList(listEnumerationTypeBeans.get(i)),FIELD_TYPE_ENUMERATION);
                }
            }
        }catch (Exception e){
           System.out.println("数据初始化失败："+e);
        }

    }

    /**
     * 数据初始化，从xml读取数据到工具中
     * @param listObject
     * @param javaBeanType
     */
    public void getDateFromXMl(List<Object> listObject, String javaBeanType)  {
        int sizeInt=listObject.size();
        for(int i=0;i<sizeInt;i++){
            Map<String, Object> map;
            map=getMap(listObject.get(i));
            Object uuidStr=map.get("uuid");
            if(!"null".equals(uuidStr)&&!"".equals(uuidStr)){
                String orderNumber=map.get("orderNumber").toString();
                String fieldID=map.get("fieldID").toString();
                String name=map.get("fieldName").toString();
                String fieldType=map.get("fieldType").toString();
                String fieldLength=map.get("fieldLength").toString();
                String choiceFieldEmpty=map.get("choiceFieldEmpty").toString();
                String fieldOtherType=map.get("fieldOtherType").toString();
                String uuid=map.get("uuid").toString();
                String isGroupBytes="";
                if(javaBeanType.equals(FIELD_TYPE_STRING)){
                    stringTypeBeans.add(i, map2Bean(map,StringTypeBean.class));
                    isGroupBytes=map.get("isGroupBytes").toString();
                }
                if(javaBeanType.equals(FIELD_TYPE_INTER)){
                    interTypeBeans.add(i, map2Bean(map,InterTypeBean.class));
                }
                if(javaBeanType.equals(FIELD_TYPE_DECIMAL)){
                    decimalTypeBeans.add(i, map2Bean(map,DecimalTypeBean.class));
                    isGroupBytes=map.get("isGroupBytes").toString();
                }
                if(javaBeanType.equals(FIELD_TYPE_DATE)){
                    dateTypeBeans.add(i, map2Bean(map,DateTypeBean.class));
                }
                if(javaBeanType.equals(FIELD_TYPE_ENUMERATION)){
                    enumerationTypeBeans.add(i, map2Bean(map,EnumerationTypeBean.class));
                }
                fieldsData.add(new Field(
                        orderNumber,
                        fieldID,
                        name,
                        fieldType,
                        fieldLength,
                        choiceFieldEmpty,
                        fieldOtherType,
                        uuid,
                        isGroupBytes
                ));
            }else {
                System.out.println("字段类型为："+javaBeanType+",第"+i+"的字符串类型数据为空！");
            }
        }
    }

    public ObservableList<Field> getFieldsData() {
        return fieldsData;
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("start");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("数据生成工具");

        initRootLayout();
        showFieldBatchOverview();
    }
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewRootLayout));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showFieldBatchOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(vieFieldBatchOverview));
            AnchorPane fieldBatchOverview = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setCenter(fieldBatchOverview);
            FieldOverviewController controller = loader.getController();
            controller.setJavaFxMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param field the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    /**
     * 编辑字段功能，根据字段类型选择弹出框类型
     * @param field
     * @return
     */

    public  Field fieldEditDialog(Field field) {
        Field modifyField=new Field();
        String fieldType=null;
        try{
            fieldType=field.getFieldType();
            if(!StringUtils.isEmpty(fieldType)){
                switch (fieldType){
                    case "字符串型":
                        modifyField=stringEditList(field);
                        break;
                    case "整数型":
                        modifyField=interEditList(field);
                        break;
                    case "小数型":
                        modifyField=decimalEditList(field);
                        break;
                    case "日期型":
                        modifyField=dateEditList(field);
                        break;
                    case "枚举型":
                        modifyField=enumerationEditList(field);
                        break;
                    default:
                }
            }
        }catch (Exception e){
            logger.error(fieldType+"错误："+e);
        }
        return modifyField;
    }



    private Field enumerationEditList(Field field) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewEnumerationTypeFieldEditFxmlPath));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            String orderNumberStr=field.getOrderNumber();
            dialogStage.setTitle("编辑的字段类型为\""+field.getFieldType()+"\"类型，序号为："+orderNumberStr);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the field into the controller.
            EnumerationFieldEditDialogController enumerationFieldEditDialogController = loader.getController();
            enumerationFieldEditDialogController.setDialogStage(dialogStage);
            enumerationFieldEditDialogController.setField(field);
            String  uuidText=field.getUuid();
            int index= getEnumerationIndex(enumerationTypeBeans,uuidText);
            enumerationFieldEditDialogController.setEnumerationTypeBeans(enumerationTypeBeans);
            if(index>=0){
                enumerationFieldEditDialogController.setEnumerationListTypeBeanEdit(index);
            }
            dialogStage.showAndWait();
            return enumerationFieldEditDialogController.returnModifyField();

        }catch (Exception e){
            e.printStackTrace();
            return field;
        }
    }


    private Field decimalEditList(Field field) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewDecimalTypeFieldEditFxmlPath));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            String orderNumberStr=field.getOrderNumber();
            dialogStage.setTitle("编辑的字段类型为\""+field.getFieldType()+"\"类型，序号为："+orderNumberStr);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the field into the controller.
            DecimalFieldEditDialogController decimalFieldEditDialogController = loader.getController();
            decimalFieldEditDialogController.setDialogStage(dialogStage);
            decimalFieldEditDialogController.setField(field);
            String  uuidText=field.getUuid();
            int index= getDecimalIndex(decimalTypeBeans,uuidText);
            decimalFieldEditDialogController.setDecimalTypeBeans(decimalTypeBeans);
            if(index>=0){
                decimalFieldEditDialogController.setDecimalListTypeBeanEdit(index);
            }
            /* stringFieldEditDialogController.setStringTypeBeans(stringTypeBeans);*/
            dialogStage.showAndWait();
            return decimalFieldEditDialogController.returnModifyField();

        }catch (Exception e){
            e.printStackTrace();
            return field;
        }
    }


    private Field dateEditList(Field field) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewDateTypeFieldEditFxmlPath));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            String orderNumberStr=field.getOrderNumber();
            dialogStage.setTitle("编辑的字段类型为\""+field.getFieldType()+"\"类型，序号为："+orderNumberStr);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the field into the controller.
            DateFieldEditDialogController dateFieldEditDialogController = loader.getController();
            dateFieldEditDialogController.setDialogStage(dialogStage);
            dateFieldEditDialogController.setField(field);
            String  uuidText=field.getUuid();
            int index= getDateIndex(dateTypeBeans,uuidText);
            dateFieldEditDialogController.setDateTypeBeans(dateTypeBeans);
            if(index>=0){
                dateFieldEditDialogController.setDateListTypeBeanEdit(index);
            }
            /* stringFieldEditDialogController.setStringTypeBeans(stringTypeBeans);*/
            dialogStage.showAndWait();
            return dateFieldEditDialogController.returnModifyField();

        }catch (Exception e){
            e.printStackTrace();
            return field;
        }
    }


    public  Field interEditList(Field field) {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewInterFieldEditFxmlPath));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            String orderNumberStr=field.getOrderNumber();
            dialogStage.setTitle("编辑的字段类型为\""+field.getFieldType()+"\"类型，序号为："+orderNumberStr);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the field into the controller.
            InterFieldEditDialogController interFieldEditDialogController = loader.getController();
            interFieldEditDialogController.setDialogStage(dialogStage);
            interFieldEditDialogController.setField(field);
            String  uuidText=field.getUuid();
            int index= getInterIndex(interTypeBeans,uuidText);
            interFieldEditDialogController.setInterTypeBeans(interTypeBeans);
            if(index>=0){
                interFieldEditDialogController.setInterListTypeBeanEdit(index);
            }
            dialogStage.showAndWait();
            return interFieldEditDialogController.returnModifyField();

        }catch (Exception e){
            e.printStackTrace();
            return field;
        }
    }


    public  Field stringEditList(Field field){

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewStringFieldEditFxmlPath));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            String orderNumberStr=field.getOrderNumber();
            dialogStage.setTitle("编辑的字段类型为\""+field.getFieldType()+"\"类型，序号为："+orderNumberStr);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the field into the controller.
            StringFieldEditDialogController stringFieldEditDialogController = loader.getController();
            stringFieldEditDialogController.setDialogStage(dialogStage);
            stringFieldEditDialogController.setField(field);
            // Show the dialog and wait until the user closes it
            String  uuidText=field.getUuid();
            int index= getStringIndex(stringTypeBeans,uuidText);
            stringFieldEditDialogController.setStringTypeBeans(stringTypeBeans);
            if(index>=0){
                stringFieldEditDialogController.setStringListTypeBeanEdit(index);
            }
            dialogStage.showAndWait();
            return stringFieldEditDialogController.returnModifyField();

        } catch (IOException e) {
            e.printStackTrace();
            return  field;
        }
    }

    public boolean generatedSamples(){
        /**按照字段，用于存放正常的数据样本**/
        /*List<List<String>> successList=new ArrayList<>();*/
        List<Map<String,List<Map<String,String>>>> normalListDate=new ArrayList<>();
        /**按照字段，用于存放异常的数据样本**/
        /*List<List<String>> failList=new ArrayList<>();*/
        List<Map<String,List<Map<String,String>>>> abnormalListDate=new ArrayList<>();
        JSONObject successJson=new JSONObject();
        if(stringTypeBeans.size()>0){
            logger.info("开始根据\"生成字符串型数据样本\"，生成字符串样本数据。");
            String fieldType="字符串型";
           /* generatedSamples.computationalEquivalence(stringTypeBeans,fieldType,successJson,successList,failList);*/
           generatedSamples.computationalSampleData(stringTypeBeans,fieldType,successJson,normalListDate,abnormalListDate);
        }
        if(interTypeBeans.size()>0){
            logger.info("开始根据\"生成整数型数据样本\"，生成整数样本数据。");
            String fieldType="整数型";
        }
        if(decimalTypeBeans.size()>0){
            logger.info("开始根据\"生成小数型数据样本\"，生成小数样本数据。");
            String fieldType="小数型";
        }
        if(dateTypeBeans.size()>0){
            logger.info("开始根据\"生成日期型数据样本\"，生成日期样本数据。");
            String fieldType="日期型";
        }
        if(enumerationTypeBeans.size()>0){
            logger.info("开始根据\"生成枚举型数据样本\"，生成枚举样本数据。");
            String fieldType="枚举型";
        }

       /* List<List<String>> lists=generatedSamples.makeLists(successList,failList,successJson);*/
       /* generatedSamples.writeExcel(lists);*/
        return true;
    }

    public boolean deleteFieldListBeans(String fildType,String uuid){
        int index;
        boolean deleteBoolean=false;
        try{
            switch (fildType){
                case "字符串型":
                    index=getStringIndex(stringTypeBeans,uuid);
                    if(index>=0){
                        stringTypeBeans.remove(index);
                        deleteBoolean=true;
                    }else if(index == -1){
                        deleteBoolean=true;
                    }
                    break;
                case "整数型":
                    index=getInterIndex(interTypeBeans,uuid);
                    if(index>=0){
                        stringTypeBeans.remove(index);
                        deleteBoolean=true;
                    }else if(index == -1){
                        deleteBoolean=true;
                    }
                    break;
                case "小数型":
                    index=getDecimalIndex(decimalTypeBeans,uuid);
                    if(index>=0){
                        decimalTypeBeans.remove(index);
                        deleteBoolean=true;
                    }else if(index == -1){
                        deleteBoolean=true;
                    }
                    break;
                case "日期型":
                    index=getDateIndex(dateTypeBeans,uuid);
                    if(index>=0){
                        dateTypeBeans.remove(index);
                        deleteBoolean=true;
                    }else if(index == -1){
                        deleteBoolean=true;
                    }
                    break;
                case "枚举型":
                    index=getEnumerationIndex(enumerationTypeBeans,uuid);
                    if(index>=0){
                        enumerationTypeBeans.remove(index);
                        deleteBoolean=true;
                    }else if(index == -1){
                        deleteBoolean=true;
                    }
                    break;
            }
        }catch (Exception e){
            logger.error("删除类型为"+fildType+",uuid为"+uuid+"失败！");
        }
        return deleteBoolean;
    }


    /**
     * Returns the main stage.
     * @return
     */
   public Stage getPrimaryStage() {
        return primaryStage;
    }

  /*  public static void main(String[] args) {
        launch(args);
    }*/

}