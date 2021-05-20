package com.xhzy.common;


import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Logger {

    /**
     * 日志文件总开关
     * true 为打日志，false 为关闭日志
     */
    public static Boolean MYLOG_SWITCH = true; // 日志文件总开关
    private static Context mContext = null;

    /**
     * 日志写入文件
     * true 为写入文件，false 为关闭写入文件
     */
    public static Boolean MYLOG_WRITE_TO_FILE = true;// 日志写入文件
    private static char MYLOG_TYPE = 'v';// 输入日志类型，w代表只输出告警信息等，v代表输出
    private static int SDCARD_LOG_FILE_SAVE_DAYS = 7;// sd卡中日志文件的最多保存天数
    private static String LOGFILE_NAME = "_sxly_ar_log.txt";// 本类输出的日志文件名称
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss:SSS");// 日志的输出格式
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式

    private static String OTHER_LOG_PREFIX = "tombstone";

    public static void initLog(Context context){
        mContext = context;
    }

    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    public static void w(String tag, String text) {
        log(tag, text, 'w');
    }

    public static void e(String tag, String text) {
        log(tag, text, 'e');
    }

    public static void e(String tag, Throwable thr) {
        log(tag, getStackTrace(thr), 'e');
    }

    public static void e(String tag, String text, Throwable thr) {
        log(tag, text + getStackTrace(thr), 'e');
    }

    public static void d(String tag, String text) {
        log(tag, text, 'd');
    }

    public static void i(String tag, String text) {
        log(tag, text, 'i');
    }

    public static void v(String tag, String text) {
        log(tag, text, 'v');
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     * @return void
     * @since v 1.0
     */
    private static void log(String tag, String msg, char level) {
        if (MYLOG_SWITCH) {
            if ('e' == level && ('e' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) { // 输出错误信息
                Log.e(tag, msg);
            } else if ('w' == level && ('w' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.w(tag, msg);
            } else if ('d' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.d(tag, msg);
            } else if ('i' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);
            }
            //log.d  不写文件
            if (MYLOG_WRITE_TO_FILE && 'd' != level)
                writeLogtoFile(String.valueOf(level), tag, msg);
        }
    }

    /**
     * 采取应用内部缓存，避免无存储权限导致的日志写入失败
     * @return
     */
    private static String getLogPath() {
        if(mContext == null){
            Log.i("Logger","please init logger");
            return null;
        }
        return mContext.getExternalFilesDir(null) + "/log/";

    }

    /**
     * 打开日志文件并写入日志
     *
     * @return
     **/
    private static void writeLogtoFile(String mylogtype, String tag, String text) {// 新建或打开日志文件
        if(mContext == null){
            return;
        }
        Date nowtime = new Date();
        String needWriteFiel = dateFormat.format(nowtime);
        String needWriteMessage = myLogSdf.format(nowtime) + "    " + mylogtype
                + "    " + tag + "    " + text;

        // 取得日志存放目录
        String path = getLogPath();
        if (path != null && !"".equals(path)) {
            try {
                // 创建目录
                File dir = new File(path);
                if (!dir.exists())
                    dir.mkdir();
                // 打开文件
                File file = new File(path + File.separator + needWriteFiel
                        + LOGFILE_NAME);
                FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
                BufferedWriter bufWriter = new BufferedWriter(filerWriter);
                bufWriter.write(needWriteMessage);
                bufWriter.newLine();
                bufWriter.close();
                filerWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除制定的日志文件
     */
    public static void delFile() {// 删除日志文件
        // 取得日志存放目录
        String path = getLogPath();
        if(path == null){
            return;
        }
        File logFile = new File(path);
        if (!logFile.exists()) {
            return;
        }
        long needDelDate = getDateBefore();
        String[] list = logFile.list();
        if (list == null) {
            return;
        }
        for (String fileStr : list) {
            if (fileStr.startsWith(OTHER_LOG_PREFIX)){
                continue;
            }
            String date = fileStr.split("_")[0];
            try {
                long currentFileTime = dateFormat.parse(date).getTime();
                if (currentFileTime <= needDelDate) {
                    File file = new File(logFile, fileStr);
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件
     */
    private static long getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime().getTime();
    }

    private static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
