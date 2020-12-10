package com.autoTest.javaFXEquivalenceClass.util;


import com.autoTest.javaFXEquivalenceClass.model.DateTypeBean;
import com.autoTest.javaFXEquivalenceClass.model.ListAllJavaBeans;
import com.autoTest.javaFXEquivalenceClass.model.StringTypeBean;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * CREATE BY OUKELE
 * CREATE TIME：2020/3/17 - 9:15
 */

/**
 *
 * 1、实现 对象 转 xml
 * 2、实现 xml 转对象
 */
public class XmlInterfaceUtils {

    private XmlInterfaceUtils() {

    }

    /**
     * 对象 转 xml
     *
     * @param object
     * @return 返回一个生成xml的位置
     */
    public static String convertToXml(Object object,String filePath) {
        if (object == null) {
            return null;
        }
        Element root = null;
        try {
            Class<?> aClass = object.getClass();
            // 根节点
            root = new Element(aClass.getSimpleName());
            for (Field declaredField : aClass.getDeclaredFields()) {
                declaredField.setAccessible(true);
                // 这个属性为 List 时
                if (declaredField.getGenericType() instanceof ParameterizedType) {
                    Element child1 = new Element(declaredField.getName());
                    List<?> objList = (List<?>) declaredField.get(object);
                    if (objList != null) {
                        ListObjecToXml(objList, child1);
                        root.addContent(child1);
                    }
                } else {
                    String fieldName = declaredField.getName();
                    Element child = new Element(fieldName);
                    child.setText(String.valueOf(declaredField.get(object)));
                    root.addContent(child);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("对象转换成XML出现异常：" + e.getMessage());
        }
        try {
            // 将 根节点 加入 文档中
            Document doc = new Document(root);
            // 创建xml输出流操作类
            XMLOutputter xmlOutput = new XMLOutputter();
            // 格式化xml内容
            xmlOutput.setFormat(Format.getPrettyFormat());
            File directory = new File("src/main/resources");
            String courseFile = directory.getCanonicalPath();

            // 把xml输出到指定位置
            File f = new File(courseFile + File.separator + "temp");
            if (!f.exists()) {
                f.mkdirs();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHssmm");
            System.out.println(f.getAbsolutePath());
            File file = new File(filePath);
           /* File file = new File(f.getAbsolutePath() + File.separator + object.getClass().getSimpleName() + format.format(new Date()) + ".xml");*/
            xmlOutput.output(doc, new FileOutputStream(file));
            System.out.println("生成完毕！ " + file.getName() + "文件生成位置为：" + file.getAbsolutePath());
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成xml文件出现异常：" + e.getMessage());
        }
        return null;
    }

    // 处理 List<实体> objData
    private static void ListObjecToXml(List<?> objData, Element element) throws Exception {
        if (objData != null) {
            for (int i = 0; i < objData.size(); i++) {
                Field[] declaredFields = objData.get(i).getClass().getDeclaredFields();
                Object obj = objData.get(i);
                Element root = new Element(obj.getClass().getSimpleName());
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    Element child = new Element(declaredField.getName());
                    if (declaredField.getGenericType() instanceof ParameterizedType) {
                        List<?> objList = (List<?>) declaredField.get(obj);
                        if (objList != null) {
                            ListObjecToXml(objList, child);
                            root.addContent(child);
                        }
                    } else {
                        child.setText(String.valueOf(declaredField.get(obj)));
                        root.addContent(child);
                    }

                }
                element.addContent(root);
            }
        }
    }

    /**
     * xml 转 对象
     *
     * @param clazz
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public static Object dataXmltoEntity(Class<?> clazz, String xmlStr) {
        if (clazz == null) {
            System.out.println("未设置对象的类型");
            return null;
        }
        File file = new File(xmlStr);
        if (!file.exists()) {
            System.out.println("解析失败，找不到文件");
            return null;
        }
        //创建Jdom2的解析器对象
        SAXBuilder builder = new SAXBuilder();
        Document document;
        Element root = null;
        Object obj = null;
        try {
            document = builder.build(file.getAbsoluteFile());
            root = document.getRootElement();
            if (!root.getName().equals(clazz.getSimpleName())) {
                System.out.println("xml内容无法转成 " + clazz + "对象，请检查！");
                return null;
            }
            // new出 当前最大的对象
            obj = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("无法进行xml解析，请检查！");
        }
        try {
            List<Element> children = root.getChildren();
            for (Element child : children) {
                // 第二层的xml数据
                List<Element> children1 = child.getChildren();
                if (children1.isEmpty()) { // 处理第一层的xml数据
                    Field field = clazz.getDeclaredField(child.getName());
                    field.setAccessible(true);
                    if (field.getGenericType().getTypeName().equals("int")) {
                        field.set(obj, Integer.parseInt(child.getValue()));
                    } else {
                        field.set(obj, child.getValue());
                    }
                } else { // 处理第二层的 xml 数据
                    mm(clazz, obj, child, children1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析xml数据转换成实体出现异常：" + e.getMessage());
        }
        return obj;
    }

    private static void mm(Class<?> clazz, Object obj, Element child, List<Element> children1) throws Exception {
        // 取到当前的 list 属性
        Field field = clazz.getDeclaredField(child.getName());
        field.setAccessible(true);
        Class<?> genericClazz = null;
        if (field.getType() == List.class) {
            // 如果是List类型，得到其Generic的类型
            Type genericType = field.getGenericType();
            if (genericType != null) {
                // 如果是泛型参数的类型
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) genericType;
                    //得到泛型里的class类型对象
                    genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                }
            }
        }
        if (genericClazz != null) {
            List list = new ArrayList();
            // list 中 包含的对象
            for (Element element : children1) {
                // 取出当前类的属性与值
                List<Element> children2 = element.getChildren();
                // new 出List中包含的对象
                Object o = genericClazz.getConstructor().newInstance();
                // 当前对象进行赋值
                for (Element element1 : children2) {
                    if (element1.getChildren().isEmpty()) {
                        Field field1 = genericClazz.getDeclaredField(element1.getName());
                        field1.setAccessible(true);
                        field1.set(o, element1.getValue());
                    } else {
                        // 递归处理
                        mm(genericClazz, o, element1, element1.getChildren());
                    }
                }
                list.add(o);
            }
            field.set(obj, list);
        }
    }

    public static void main(String[] args)  {
        ListAllJavaBeans listAllJavaBeans = new ListAllJavaBeans();
        List<DateTypeBean> listDateTypeBean =new ArrayList<>();
        List<StringTypeBean> listStringTypeBean =new ArrayList<>();
        DateTypeBean dateTypeBean=new DateTypeBean();
        StringTypeBean stringTypeBean=new StringTypeBean();
        dateTypeBean.setFieldID("222222");
        listDateTypeBean.add(0,dateTypeBean);
        listDateTypeBean.add(1,dateTypeBean);
        stringTypeBean.setOrderNumber("785678568565757");
        stringTypeBean.setUuid("992952354235");
        stringTypeBean.setfieldType("字符串型");
        stringTypeBean.setIsGroupBytes("是");
        stringTypeBean.setFieldName("测试数据字符串");
        stringTypeBean.setFieldLength("20");
        stringTypeBean.setFieldOtherType("是");
        stringTypeBean.setFieldID("test");
        stringTypeBean.setChoiceFieldEmpty("是");
        StringTypeBean stringTypeBean1=new StringTypeBean();
        stringTypeBean1.setOrderNumber("567575675757");
        stringTypeBean1.setUuid("992952354235");
        stringTypeBean1.setfieldType("字符串型");
        stringTypeBean1.setIsGroupBytes("是");
        stringTypeBean1.setFieldName("测试数据字符串");
        stringTypeBean1.setFieldLength("20");
        stringTypeBean1.setFieldOtherType("是");
        stringTypeBean1.setFieldID("test");
        stringTypeBean1.setChoiceFieldEmpty("是");
        listStringTypeBean.add(0,stringTypeBean);
        listStringTypeBean.add(1,stringTypeBean1);
        listAllJavaBeans.setListDateTypeBeans(listDateTypeBean);
        listAllJavaBeans.setListStringTypeBeans(listStringTypeBean);
        XmlInterfaceUtils.convertToXml(listAllJavaBeans,"dataXml");
        String pathString="D:\\gitHub\\javaFX-EquivalenceClass\\javaFX-EquivalenceClass\\src\\main\\resources\\temp\\dataXml001.xml";
        ListAllJavaBeans o = (ListAllJavaBeans) XmlInterfaceUtils.dataXmltoEntity(ListAllJavaBeans.class, pathString);
        if(o.getListDateTypeBeans()!=null){
            for(int i=0;i<o.getListDateTypeBeans().size();i++){
                DateTypeBean tempDateTypeBean=o.getListDateTypeBeans().get(i);
                System.out.println(tempDateTypeBean);
            }
        }
        System.out.println(o.getListDecimalTypeBeans());
        System.out.println(o.getListEnumerationTypeBeans());
        System.out.println(o.getListInterTypeBeans());
        System.out.println(o.getListStringTypeBeans());
    }
}