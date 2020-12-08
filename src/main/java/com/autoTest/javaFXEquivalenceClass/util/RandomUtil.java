package com.autoTest.javaFXEquivalenceClass.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RandomUtil {
    private static Random random = null;
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串
     *
     * @param length
     *            字符串长度
     * @return 纯0字符串
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     *
     * @param num
     *            数字
     * @param fixdlenth
     *            字符串长度
     * @return 定长的字符串
     */
    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
                    + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 每次生成的len位数都不相同
     *
     * @param param
     * @return 定长的数字
     */
    public static int getNotSimple(int[] param, int len) {
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }
    // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
    public static int getIntervalSectionIntSimple(int MAX,int MIN){
        Random rand = new Random();
        int randNumber =rand.nextInt(MAX - MIN + 1) + MIN;
        return randNumber;
    }


    public static String getIntervalSectionStrSimple(int MAX,int MIN) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int m=random.nextInt(MAX - MIN + 1) + MIN;
        for (int i = 0; i < m; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        System.out.println(m);
        return sb.toString();
    }


    public static String getChineseString(int Max,int MIN ){
        String chineseString="";
        return chineseString;
    }

    private static Random getRandomInstance() {
        if (random == null) {
           Date date= new Date();
            random = new Random(date.getTime());
        }
        return random;
    }


    public static String getFixedLengthChinese(int length) {
        String str = "";
        for (int i = length; i > 0; i--) {
            str = str + getChinese();
        }
        return str;
    }

    public static String getRandomLengthChiness(int start, int end) {
        String str = "";
        int length = new Random().nextInt(end + 1);
        if (length < start) {
            str = getRandomLengthChiness(start, end);
        } else {
            for (int i = 0; i < length; i++) {
                str = str + getChinese();
            }
        }
        return str;
    }


    public static String getChinese() {
        String str = null;
        int highPos, lowPos;
        Random random = getRandomInstance();
        highPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[2];
        b[0] = (new Integer(highPos)).byteValue();
        b[1] = (new Integer(lowPos)).byteValue();
        try {
            str = new String(b, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static float generatingFloat()  {

        float floatUnbounded = new Random().nextFloat();
        return floatUnbounded;
    }

    public static double generationDouble(){
        Random r = new Random();
        double doubleDate=r.nextDouble()*5;
        return doubleDate;
    }

 public static String generationDecimal(int precision,float Max,float Min){
     BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
    return db.setScale(precision, BigDecimal.ROUND_HALF_UP).toString();
 }

    public static List<String> getDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
          tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);
            // 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static String getDate(String startTime, String endTime){
        List<String> days=getDays(startTime,endTime);
        int sizeInt=days.size();
        int indexInt=sizeInt/2;
        return days.get(indexInt);
    }

   public static String getTime(String startTime, String endTime){
        String HHYYMM="";
      String startHH=startTime.substring(0,2);
      int startHHInt=Integer.valueOf(startHH);
      String endHH=endTime.substring(0,2);
      int endHHInt=Integer.valueOf(endHH);

      String startYY=startTime.substring(3,5);
      int startYYInt=Integer.valueOf(startYY);
      String endYY=endTime.substring(3,5);
      int endYYInt=Integer.valueOf(endYY);

      String startMM=startTime.substring(6,8);
      int startMMInt=Integer.valueOf(startMM);
      String endMM=endTime.substring(6,8);
      int endMMInt=Integer.valueOf(endMM);

      if(endHHInt>startHHInt){
         int HH=getRandom(startHHInt,endHHInt);
         if(startHHInt<HH&&HH<endHHInt){
             HHYYMM=HH+":";
           int YY=getRandom(0,59);
           if(YY<10){
               HHYYMM=HHYYMM+"0"+YY+":";
           }else {
               HHYYMM=HHYYMM+YY+":";
           }
           int MM=getRandom(0,59);
             if(MM<10){
                 HHYYMM=HHYYMM+"0"+MM;
             }else {
                 HHYYMM=HHYYMM+MM;
             }
         }else {
             if(HH==endHHInt){
                 HHYYMM=HH+":";
                 int YY=getRandom(0,endYYInt);
                 if(YY<endYYInt){
                     if(YY<10){
                         HHYYMM=HHYYMM+"0"+YY+":";
                         int MM=getRandom(0,59);
                         if(MM<10){
                             HHYYMM=HHYYMM+"0"+MM;
                         }else {
                             HHYYMM=HHYYMM+MM;
                         }
                     }else {
                         HHYYMM=HHYYMM+YY+":";
                         int MM=getRandom(0,59);
                         if(MM<10){
                             HHYYMM=HHYYMM+"0"+MM;
                         }else {
                             HHYYMM=HHYYMM+MM;
                         }
                     }
                 }else {
                     if(YY==endYYInt){
                         if(YY<10){
                             HHYYMM=HHYYMM+"0"+YY+":";
                             int MM=getRandom(0,endMMInt);
                             if(MM<10){
                                 HHYYMM=HHYYMM+"0"+MM;
                             }else {
                                 HHYYMM=HHYYMM+MM;
                             }
                         }else {
                             HHYYMM=HHYYMM+YY+":";
                             int MM=getRandom(0,endMMInt);
                             if(MM<10){
                                 HHYYMM=HHYYMM+"0"+MM;
                             }else {
                                 HHYYMM=HHYYMM+MM;
                             }
                         }
                     }
                 }

             }
             if(HH==startHHInt){
                 HHYYMM=HH+":";
                 int YY=getRandom(startYYInt,endYYInt);
                 if(YY<endYYInt){
                     if(YY<10){
                         HHYYMM=HHYYMM+"0"+YY+":";
                         int MM=getRandom(startMMInt,endMMInt);
                         if(MM<10){
                             HHYYMM=HHYYMM+"0"+MM;
                         }else {
                             HHYYMM=HHYYMM+MM;
                         }
                     }else {
                         HHYYMM=HHYYMM+YY+":";
                         int MM=getRandom(startMMInt,endMMInt);
                         if(MM<10){
                             HHYYMM=HHYYMM+"0"+MM;
                         }else {
                             HHYYMM=HHYYMM+MM;
                         }
                     }
                 }else {
                     if(YY==endYYInt){
                         if(YY<10){
                             HHYYMM=HHYYMM+"0"+YY+":";
                             int MM=getRandom(startMMInt,endMMInt);
                             if(MM<10){
                                 HHYYMM=HHYYMM+"0"+MM;
                             }else {
                                 HHYYMM=HHYYMM+MM;
                             }
                         }else {
                             HHYYMM=HHYYMM+YY+":";
                             int MM=getRandom(startMMInt,endMMInt);
                             if(MM<10){
                                 HHYYMM=HHYYMM+"0"+MM;
                             }else {
                                 HHYYMM=HHYYMM+MM;
                             }
                         }
                     }
                 }

             }
         }
      }else {
          if(endHHInt==startHHInt){
              HHYYMM=endHHInt+":";
              if(startYYInt==endYYInt){
                  int YY=endYYInt;
                  HHYYMM=HHYYMM+YY+":";
                  int MM=getRandom(startMMInt,endMMInt);
                  if(MM<10){
                      HHYYMM=HHYYMM+"0"+MM;
                  }else {
                      HHYYMM=HHYYMM+MM;
                  }
              }else {
                  if(endYYInt>startYYInt){
                      int  YY=getRandom(startYYInt,endYYInt);
                      HHYYMM=HHYYMM+YY+":";
                      if(YY==endYYInt){
                          int  MM=getRandom(0,endMMInt);
                          if(MM<10){
                              HHYYMM=HHYYMM+"0"+MM;
                          }else {
                              HHYYMM=HHYYMM+MM;
                          }
                      }
                      if(YY==startYYInt){
                          int  MM=getRandom(startMMInt,endMMInt);
                          if(MM<10){
                              HHYYMM=HHYYMM+"0"+MM;
                          }else {
                              HHYYMM=HHYYMM+MM;
                          }
                      }
                  }
              }
          }
      }
      return HHYYMM;
    }

    public static int getRandom(int min, int max){
        Random random = new Random();
        int i = random.nextInt(max) % (max - min + 1) + min;
        return i;
    }

    /**
     * 获得指定日期下一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }

    /**
     * 获得指定日期上一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDay(String specifiedDay,int i) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        if(i==-1){
            c.set(Calendar.DATE, day - 1);
        }else {
            c.set(Calendar.DATE, day + 1);
        }
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayBefore;
    }

    public static String getSpecifiedDayTime(String time,int i){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Scanner sc = new Scanner(time);
        Date tempTime = new Date();
        String s = sc.nextLine();
        try {
            tempTime = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ss = tempTime.getTime();
        if(i==-1){
            ss = ss - 1000;
        }else {
            ss = ss + 1000;
        }
        Date time2 = new Date();
        time2.setTime(ss);
        return  format.format(time2);
    }

  public static int getNumber(String varchar){
     int intNumber=varchar.getBytes().length;
     return intNumber;
  }

    public static Boolean dateEqually(String dateBefore, String dateAfter, String dateType) {
        Date dateOne = null;
        Date dateTwo = null;
        Boolean booleanDaeEqually = false;
        try {
            if (dateType.equals("date")) {
                dateOne = new SimpleDateFormat("yy-MM-dd").parse(dateBefore);
                dateTwo = new SimpleDateFormat("yy-MM-dd").parse(dateAfter);
                if (dateOne.equals(dateTwo)) {
                    booleanDaeEqually = false;
                } else {
                    booleanDaeEqually = true;
                }
            }
            if (dateType.equals("time")) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Scanner scBefore = new Scanner(dateBefore);
                Scanner scAfter = new Scanner(dateAfter);
                Date tempTimeS1;
                Date tempTimeS2;
                String s1 = scBefore.nextLine();
                String s2 = scAfter.nextLine();
                tempTimeS1 = format.parse(s1);
                tempTimeS2 = format.parse(s2);
                if (tempTimeS1.equals(tempTimeS2)) {
                    booleanDaeEqually = false;
                } else {
                    booleanDaeEqually = true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return booleanDaeEqually;
    }


    public static void main(String[] args) {
       System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(10));
        System.out.println("返回一个定长的随机纯字母字符串(只包含大小写字母):" + generateMixString(10));
        System.out.println("返回一个定长的随机纯大写字母字符串(只包含大小写字母):"
                + generateLowerString(10));
        System.out.println("返回一个定长的随机纯小写字母字符串(只包含大小写字母):"
                + generateUpperString(10));
        System.out.println("生成一个定长的纯0字符串:" + generateZeroString(10));
        System.out.println("根据数字生成一个定长的字符串，长度不够前面补0:"
                + toFixdLengthString(123, 10));
        int[] in = { 1, 2, 3, 4, 5, 6, 7 };
        System.out.println("每次生成的len位数都不相同:" + getNotSimple(in, 3));
        System.out.println(getChinese());
        System.out.println(getFixedLengthChinese(20));
        System.out.println(getRandomLengthChiness(2, 5));
        //*System.out.println(getIntervalSectionIntSimple(9,5));*//*
        System.out.println(generatingFloat());
        System.out.println(generationDouble());
        System.out.println(generationDecimal(2,50,40));
     //* System.out.println("每次生成的len位数都不相同:" + getIntervalSectionStrSimple(30, 3));
        /*System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(0));*/
        System.out.println(getDate("2020-12-15","2020-12-20"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(getSpecifiedDayAfter("2015-02-2"));

        SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss"); //格式时间
      //*  Timestamp nowTime1= Timestamp.valueOf(String.valueOf(new Date(System.currentTimeMillis())));
        /*System.out.println(nowTime1);*/
        /*Timestamp nowTime2=Timestamp.valueOf(df.format(new Date(System.currentTimeMillis()+1000)));
        System.out.println(System.currentTimeMillis());*/
        System.out.println(getSpecifiedDayTime("14:24:00",1));
        System.out.println(getSpecifiedDay("2015-02-01",1));

        System.out.println( "00000"+getNumber("%"));
    }
}