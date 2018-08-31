package com.a51zhipaiwang.worksend.Utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/08/06
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class EditInputMoneyFilter implements InputFilter {
    /**
     * 小数点后的数字的位数
     */
    public static final int PONTINT_LENGTH = 2;
    Pattern p;

    public EditInputMoneyFilter() {
        p = Pattern.compile("([0-9]|\\.)*"); //除数字外的其他的
    }

    /**
     * source    新输入的字符串
     * start    新输入的字符串起始下标，一般为0
     * end    新输入的字符串终点下标，一般为source长度-1
     * dest    输入之前文本框内容
     * dstart    原内容起始坐标，一般为0
     * dend    原内容终点坐标，一般为dest长度-1
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        String oldtext = dest.toString();

        //验证删除等按键
        if ("".equals(source.toString())) {
            return "";
        }

        //验证非数字或者小数点的情况
        Matcher m = p.matcher(source);
        if (oldtext.contains(".")) {
            //已经存在小数点的情况下，只能输入数字
            if (!m.matches()) {
                return "";
            }
        } else {
            if (!m.matches()) {
                return "";
            }else {
                if ("0".equals(source) && "0".equals(oldtext)) {
                    return "";
                }
            }
            if (".".equals(source) && TextUtils.isEmpty(oldtext)){
                return "";
            }
        }

        //验证小数位精度是否正确
        if (oldtext.contains(".")) {
            int index = oldtext.indexOf(".");
            int len = dend - index;
            //小数位只能2位
            if (len > PONTINT_LENGTH) {
                CharSequence newText = dest.subSequence(dstart, dend);
                return newText;
            }
        }

        return dest.subSequence(dstart, dend) + source.toString();
    }
}