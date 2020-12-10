package com.autoTest.javaFXEquivalenceClass.util;


import com.autoTest.javaFXEquivalenceClass.controller.JavaFxMain;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

import static com.autoTest.javaFXEquivalenceClass.base.ConsField.*;


public class PopupUtil {
    /**
     * 数据非法
     */
    public static void dataIsInvalid(JavaFxMain javaFxMain, String str){
        String title="数据非法";
        String headerText="输入的数据\""+str+"\"非法。";
        String contentText="请重新输入合法的数据.";
        WarningHints(javaFxMain,title,headerText,contentText);
    }

    /**
     * 数据为空
     * @param javaFxMain
     * @param str
     */
    public static void dataIsEmpty(JavaFxMain javaFxMain,String str){
        String title="数据为空";
        String headerText="输入的数据\""+str+"\"为空。";
        String contentText="请重新输入合法的不为空的数据.";
        WarningHints(javaFxMain,title,headerText,contentText);
    }

    /**
     * 错误提示框
     * @param javaFxMain
     * @param title
     * @param e
     */
    public static void errorPopup(JavaFxMain javaFxMain,String title,Exception e){
        String headerText="工具运行出现的错误。";
        String contentText="工具运行出现\""+e+"\"的错误。";
        WarningHints(javaFxMain,title,headerText,contentText);
    }

    /**
     * 字符串数据校验错误（数据非法），提示框
     * @param stage
     * @param strMessage
     * @param fieldStr
     */
    public static void stringDataIsInvalid(Stage stage,String strMessage,String fieldStr){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle(POPUP_DATA_INVALID_TITLE);
        alert.setHeaderText("输入的内容:"+fieldStr+", "+strMessage);
        alert.setContentText(POPUP_DATA_INVALID_CONTENTTEXT);
        alert.showAndWait();
    }


   /**
    * 字符串数据校验错误（数据非法），提示框
    * @param stage Window窗口
    * @param fieldName 字段名称
    * @param tips 提示信息
    */
    public static void dataIsInvalid(Stage stage,String fieldName,String tips){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle(fieldName+POPUP_DATA_INVALID_TITLE);
        alert.setHeaderText(tips);
        alert.setContentText(POPUP_DATA_INVALID_CONTENTTEXT);
        alert.showAndWait();
    }





    /**
     * 编辑弹出框，输入数据不为空的验证
     * @param stage Window窗口
     * @param fieldName 字段名称
     * @param tips 提示信息
     */
    public static void dataIsEmptyStage(Stage stage,String fieldName,String tips) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle(fieldName+POPUP_DATA_EMPTY_TITLE);
        alert.setHeaderText(tips);
        alert.setContentText("");
        alert.showAndWait();
    }

    /**
     * 成功提示框
     * @param javaFxMain
     * @param title
     * @param headerText
     * @param contentText
     */
    public static void SuccessHints(JavaFxMain javaFxMain, String title, String headerText, String contentText){

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(javaFxMain.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static Boolean alertConfirDialog(JavaFxMain javaFxMain,String header,String message){
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION,message,new ButtonType("取消", ButtonBar.ButtonData.NO),
                new ButtonType("确定", ButtonBar.ButtonData.YES));
              // 设置窗口的标题
             _alert.setTitle("确认");
             _alert.setHeaderText(header);
            // 设置对话框的 icon 图标，参数是主窗口的 stage
            _alert.initOwner(javaFxMain.getPrimaryStage());
           // showAndWait() 将在对话框消失以前不会执行之后的代码
           Optional<ButtonType> _buttonType = _alert.showAndWait();
          // 根据点击结果返回
         if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            return true;
        }
        else {
            return false;
        }
    }


    public static void WarningHints(JavaFxMain javaFxMain, String title, String headerText, String contentText){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(javaFxMain.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
