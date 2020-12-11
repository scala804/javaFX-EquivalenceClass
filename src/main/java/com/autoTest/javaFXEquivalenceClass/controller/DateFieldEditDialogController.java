package com.autoTest.javaFXEquivalenceClass.controller;

import com.autoTest.javaFXEquivalenceClass.model.DateTypeBean;
import com.autoTest.javaFXEquivalenceClass.model.Field;
import com.autoTest.javaFXEquivalenceClass.util.MinTool;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


import static com.autoTest.javaFXEquivalenceClass.base.ConsField.*;
import static com.autoTest.javaFXEquivalenceClass.util.MinTool.*;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsEmptyStage;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsInvalid;
import static java.time.LocalDate.now;

/**
 * @author yangbihua
 */
public class DateFieldEditDialogController {

    private static final Logger logger = LoggerFactory.getLogger(DateFieldEditDialogController.class);
    @FXML
    private TextField uuid;
    @FXML
    private TextField fieldOtherType;
    @FXML
    private TextField fieldType;
    @FXML
    private TextField fieldLength;
    @FXML
    private TextField orderNumber;
    @FXML
    private ChoiceBox choiceFieldEmpty;
    @FXML
    private  TextField fieldID;
    @FXML
    private  TextField  fieldName;
    @FXML
    private TextField fieldNormalData;
    @FXML
    private DatePicker dateBeyondLowerBoundaryValue;
    @FXML
    private TextField timeBeyondLowerBoundaryValue;
    @FXML
    private TextArea beyondLowerBoundaryExpectedResults;
    @FXML
    private  TextArea dateIsNullExpectedResults;
    @FXML
    private DatePicker dateBeyondUpperBoundaryValue;
    @FXML
    private TextField timeBeyondUpperBoundaryValue;
    @FXML
    private TextArea beyondUpperBoundaryExpectedResults;
    @FXML
    private  TextArea dateIsFormatExpectedResults;

    @FXML
    private ImageView ImageViewUpperTime;
    @FXML
    private ImageView ImageViewLowerTime;

    private Stage dialogStage;
    private Field field;
    private DateTypeBean dateTypeBean = new DateTypeBean();
    private List<DateTypeBean> dateTypeBeans;
    private boolean okClicked = false;
    DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter hhmmdd = DateTimeFormatter.ofPattern("HH:mm:ss");
    @FXML
    private void initialize() {
        choiceFieldEmpty.getItems().addAll("否", "是");
        fieldName.setPromptText(TEXT_INPUT_NOT_NULL);
        fieldID.setPromptText(TEXT_INPUT_NOT_REQUIRED);
        fieldNormalData.setPromptText(TEXT_INPUT_NOT_REQUIRED_SYSTEM_CREATE);

        beyondLowerBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        beyondUpperBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);

        dateIsNullExpectedResults.setPromptText(SYSTEM_DEFAULT_CHOICEFIELDEMPTY_withNullExpectedResult);
        dateIsFormatExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        dateBeyondLowerBoundaryValue.setValue(now());
        dateBeyondUpperBoundaryValue.setValue(now());
        Date d = new Date();
        String timeStringTemp=String.valueOf(d);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = sdf.format(d);
        timeBeyondUpperBoundaryValue.setText(dateNowStr);
        timeBeyondLowerBoundaryValue.setText(dateNowStr);

        ImageIcon imageIcon = new ImageIcon(DateFieldEditDialogController.class.getResource("/icons/calendar.png"));

        String tempImagePath=imageIcon.toString();
        Image image = new Image(String.valueOf(tempImagePath));
       ImageViewUpperTime.setImage(image);
        ImageViewLowerTime.setImage(image);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setField(Field field) {
        this.field = field;
        uuid.setText(field.getUuid());
        choiceFieldEmpty.setValue(field.getChoiceFieldEmpty());
        choiceFieldEmpty.setValue(field.getChoiceFieldEmpty());
        fieldOtherType.setText(field.getFieldOtherType());
        fieldType.setText(field.getFieldType());
        /*fieldLength.setText(field.getFieldLength());*/
        fieldLength.setText("["+field.getMinLength()+","+field.getMaxLength()+"]");
        orderNumber.setText(field.getOrderNumber());
    }
    public void setDateTypeBeans(List<DateTypeBean> dateTypeBeans) {
        this.dateTypeBeans = dateTypeBeans;
    }

    public void setDateListTypeBeanEdit(int index) {
        String fieldId;
        DateTypeBean dateTypeBean = dateTypeBeans.get(index);
        fieldId = dateTypeBean.getFieldID();
        try {
            setDateBeanEdit(dateTypeBean);
        } catch (Exception e) {
            logger.error("ID为" + fieldId + "编辑错误！");
        }
    }

    /**
     * 把StringTypeBean里面的数据放入到编辑框中,用于再页面上显示
     *
     * @param dateTypeBean
     * @return
     */
    public void setDateBeanEdit(DateTypeBean dateTypeBean) {
        try {
            uuid.setText(dateTypeBean.getUuid());
            orderNumber.setText(dateTypeBean.getOrderNumber());
            fieldName.setText(dateTypeBean.getFieldName());
            fieldID.setText(dateTypeBean.getFieldID());
            fieldType.setText(dateTypeBean.getfieldType());
            fieldNormalData.setText(dateTypeBean.getFieldNormalData());
            fieldOtherType.setText(dateTypeBean.getFieldOtherType());
            dateBeyondLowerBoundaryValue.setValue(LocalDate.parse(dateTypeBean.getDateBeyondLowerBoundaryValue(),yyyymmdd));
            timeBeyondLowerBoundaryValue.setText(dateTypeBean.getTimeBeyondLowerBoundaryValue());
            dateIsNullExpectedResults.setText(dateTypeBean.getDateIsNullExpectedResults());
            dateBeyondUpperBoundaryValue.setValue(LocalDate.parse(dateTypeBean.getDateBeyondUpperBoundaryValue(),yyyymmdd));
            timeBeyondUpperBoundaryValue.setText(dateTypeBean.getTimeBeyondUpperBoundaryValue());
            dateIsFormatExpectedResults.setText(dateTypeBean.getDateIsFormatExpectedResults());
            beyondLowerBoundaryExpectedResults.setText(dateTypeBean.getBeyondLowerBoundaryExpectedResults());
            /*fieldLength.setText(dateTypeBean.getFieldLength());*/
            fieldLength.setText("["+LocalDate.parse(dateTypeBean.getDateBeyondLowerBoundaryValue(),yyyymmdd)+" "+dateTypeBean.getTimeBeyondLowerBoundaryValue()+","+LocalDate.parse(dateTypeBean.getDateBeyondUpperBoundaryValue(),yyyymmdd)+" "+dateTypeBean.getTimeBeyondUpperBoundaryValue()+"]");
            choiceFieldEmpty.setValue(dateTypeBean.getChoiceFieldEmpty());
            beyondUpperBoundaryExpectedResults.setText(dateTypeBean.getBeyondUpperBoundaryExpectedResults());
        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
    }

    public Boolean isOkClicked() {
        return okClicked;
    }

    public Field returnModifyField(){
        Field field=new Field();
        if(isOkClicked()){
            field.setName(fieldName.getText());
            field.setFieldType(fieldType.getText());
            field.setChoiceFieldEmpty(choiceFieldEmpty.getValue().toString());
            field.setUuid(uuid.getText());
            field.setFieldOtherType(fieldOtherType.getText());
            String dateLength="["+dateBeyondLowerBoundaryValue.getEditor().getText()+" "+timeBeyondLowerBoundaryValue.getText()+","+dateBeyondUpperBoundaryValue.getEditor().getText()+" "+timeBeyondUpperBoundaryValue.getText()+"]";
            field.setFieldLength(dateLength);
            /*field.setFieldType(fieldType.getText());*/
            /*field.setFieldType("["+dateBeyondLowerBoundaryValue.get)*/;
            field.setFieldID(fieldID.getText());
            field.setOrderNumber(orderNumber.getText());
            field.setIsGroupBytes("");
        }
        return field;
    }

    /**
     * 把编辑框中的数据放入到dateTypeBean里面，获取页面上的数据
     *
     * @param
     * @return
     */
    public DateTypeBean setDateBean() {
        try {
            dateTypeBean.setUuid(uuid.getText());
            dateTypeBean.setOrderNumber(orderNumber.getText());
            dateTypeBean.setFieldID(fieldID.getText());
            dateTypeBean.setFieldName(fieldName.getText());
            dateTypeBean.setfieldType(fieldType.getText());
            dateTypeBean.setFieldNormalData(fieldNormalData.getText());
            dateTypeBean.setFieldOtherType(fieldOtherType.getText());
            dateTypeBean.setDateBeyondLowerBoundaryValue(yyyymmdd.format(dateBeyondLowerBoundaryValue.getValue()));
            dateTypeBean.setTimeBeyondLowerBoundaryValue(timeBeyondLowerBoundaryValue.getText());
            dateTypeBean.setDateIsNullExpectedResults(dateIsNullExpectedResults.getText());
            dateTypeBean.setDateBeyondUpperBoundaryValue(yyyymmdd.format(dateBeyondUpperBoundaryValue.getValue()));
            dateTypeBean.setTimeBeyondUpperBoundaryValue(timeBeyondUpperBoundaryValue.getText());
            dateTypeBean.setDateIsFormatExpectedResults(dateIsFormatExpectedResults.getText());
            dateTypeBean.setBeyondLowerBoundaryExpectedResults(beyondLowerBoundaryExpectedResults.getText());
            dateTypeBean.setBeyondUpperBoundaryExpectedResults(beyondUpperBoundaryExpectedResults.getText());
            dateTypeBean.setFieldLength("["+yyyymmdd.format(dateBeyondLowerBoundaryValue.getValue())+" "+timeBeyondLowerBoundaryValue.getText()+","+yyyymmdd.format(dateBeyondUpperBoundaryValue.getValue())+" "+timeBeyondUpperBoundaryValue.getText()+"]");
            /*dateTypeBean.setFieldLength(fieldLength.getText());*/
            dateTypeBean.setChoiceFieldEmpty(choiceFieldEmpty.getValue().toString());
        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
        return dateTypeBean;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        DateTypeBean dateTypeBean;
        if (!isInputDataFormatValid()) {
            okClicked=false;
        } else {
            String uuidText = uuid.getText();
            int index = MinTool.getDateIndex(dateTypeBeans, uuidText);
            dateTypeBean = setDateBean();
            if (index >= 0) {
                dateTypeBeans.remove(index);
                dateTypeBeans.add(index, dateTypeBean);
            } else {
                dateTypeBeans.add(dateTypeBean);
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }



    private boolean isInputDataFormatValid() {
        Boolean returnBoolean = true;
        if (StringUtils.isEmpty(fieldName.getText())) {
            String strMessage = FILED_NAME_NOT_EMPTY;
            dataIsEmptyStage(dialogStage, "字段名称", strMessage);
            return false;
        }
        /**数据项要求填写验证**/
        if (!StringUtils.isEmpty(fieldID.getText()) && returnBoolean == true) {
            String str = fieldID.getText();
            boolean result = str.matches("[a-zA-Z]+");
            if (!result) {
                String strMessage = "数据项如果填写，必须为英文";
                dataIsInvalid(dialogStage, "数据项", strMessage);
                return false;
            }
        }
        /**
         * 日期下边界值
         */
        if(!StringUtils.isEmpty(dateBeyondLowerBoundaryValue.getValue())){
            String dateStr=yyyymmdd.format(dateBeyondLowerBoundaryValue.getValue());
            if(!isLegalDate(dateStr)){
                String strMessage = POPUP_DATE_IS_INVALID;
                dataIsEmptyStage(dialogStage, "日期下边界值", strMessage);
                return false;
            }
        } else {
            String strMessage = POPUP_DATE_IS_EMPTY;
            dataIsInvalid(dialogStage, "日期下边界值", strMessage);
            return false;
        }
        /**
         * 日期上边界值
         */
        if(!StringUtils.isEmpty(dateBeyondUpperBoundaryValue.getValue())){
            String dateUpperStr=yyyymmdd.format(dateBeyondUpperBoundaryValue.getValue());
            if(!isLegalDate(dateUpperStr)){
                String strMessage = POPUP_DATE_IS_INVALID;
                dataIsInvalid(dialogStage, "日期上边界值", strMessage);
                return false;
            }else {
                String dateLowerStr=yyyymmdd.format(dateBeyondLowerBoundaryValue.getValue());
                if(!dateCompareResult(dateUpperStr,dateLowerStr)){
                    String strMessage = POPUP_DATE_LOW_BOUNDARY_AFTER_UPPER_BOUNDARY;
                    dataIsInvalid(dialogStage, "日期上边界值", strMessage);
                    return false;
                };
            }
        } else {
            String strMessage = POPUP_DATE_IS_EMPTY;
            dataIsInvalid(dialogStage, "日期上边界值", strMessage);
            return false;
        }

        /**
         * 时间下边界值
         */
       if(!StringUtils.isEmpty(timeBeyondLowerBoundaryValue.getText())){
            String timeStr=timeBeyondLowerBoundaryValue.getText();
            if(!isLegalTime(timeStr)){
                String strMessage = POPUP_TIME_IS_INVALID;
                dataIsEmptyStage(dialogStage, "时间下边界值", strMessage);
                return false;
            }
        } else {
            String strMessage = POPUP_TIME_IS_EMPTY;
            dataIsInvalid(dialogStage, "时间下边界值", strMessage);
            return false;
        }

        /**
         * 时间上边界值
         */
        if(!StringUtils.isEmpty(timeBeyondUpperBoundaryValue.getText())){
            String dateUpperStr=timeBeyondUpperBoundaryValue.getText();
            if(!isLegalTime(dateUpperStr)){
                String strMessage = POPUP_TIME_IS_INVALID;
                dataIsInvalid(dialogStage, "时间上边界值", strMessage);
                return false;
            }else {
                String dateLowerStr=timeBeyondLowerBoundaryValue.getText();
                if(!timeCompareResult(dateUpperStr,dateLowerStr)){
                    String strMessage = POPUP_TIME_LOW_BOUNDARY_AFTER_UPPER_BOUNDARY;
                    dataIsInvalid(dialogStage, "时间上边界值", strMessage);
                    return false;
                };
            }
        } else {
            String strMessage = POPUP_DATE_IS_EMPTY;
            dataIsInvalid(dialogStage, "时间上边界值", strMessage);
            return false;
        }

        /**
         * 超出下边界的预期结果
         */
        if (!StringUtils.isEmpty(beyondLowerBoundaryExpectedResults.getText()) && returnBoolean == true) {
            String str=beyondLowerBoundaryExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "超出下边界的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 日期为空的预期结果
         */
        if (!StringUtils.isEmpty(dateIsNullExpectedResults.getText()) && returnBoolean == true) {
            String str=dateIsNullExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "日期为空的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 超出上边界的预期结果
         */
        if (!StringUtils.isEmpty(beyondUpperBoundaryExpectedResults.getText()) && returnBoolean == true) {
            String str=beyondUpperBoundaryExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "超出上边界的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 非日期格式的预期结果
         */
        if (!StringUtils.isEmpty(dateIsFormatExpectedResults.getText()) && returnBoolean == true) {
            String str=dateIsFormatExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "非日期格式的预期结果", strMessage);
                return false;
            }
        }

        return returnBoolean;
    }

}
