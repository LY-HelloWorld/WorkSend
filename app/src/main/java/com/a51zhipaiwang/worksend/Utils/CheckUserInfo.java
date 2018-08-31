package com.a51zhipaiwang.worksend.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Created by 罗怡 on 2017/8/30.
 */

public class CheckUserInfo {

    public static boolean CheckPassWord(String password){
        if (TextUtils.isEmpty(password) || password.length() < 10){
            return false;
        }else {
            return true;
        }
    }


    public static boolean CheckUserName(String user_name, Context context){

        if (TextUtils.isEmpty(user_name) || user_name.length() < 2 || user_name.length() > 4){
            new AlertDialog.Builder(context)
                    .setTitle("消息提示")
                    .setMessage("请填写至少2-4位姓名信息（仅限中文）")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }
        if (!user_name.matches("^[\\u4e00-\\u9fa5]{0,}$")){
            new AlertDialog.Builder(context)
                    .setTitle("消息提示")
                    .setMessage("请填写正确的姓名信息（仅限中文）")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }
        return true;

    }

    public static boolean CheckTelNum(String tel_num){

        if (TextUtils.isEmpty(tel_num)){
            ToastUtil.showToastTwo("请填写手机号");
            return false;
        }
        if (tel_num.length() != 11 || !tel_num.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|14[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|4|5|6|7|8|9]|17[0|1|2|3|4|5|6|7|8|9]|166|199)\\d{8}$")){
            ToastUtil.showToastTwo("请填写正确的手机号");
            return false;
        }
        return true;

    }


    public static boolean CheckTelNumNoToast(String tel_num, Context context){

        if (TextUtils.isEmpty(tel_num)){
            return false;
        }
        if (tel_num.length() != 11 || !tel_num.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|14[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|4|5|6|7|8|9]|17[0|1|2|3|4|5|6|7|8|9]|166|199)\\d{8}$")){
            return false;
        }
        return true;

    }

    public static boolean CheckCard(String card, Context context){

        if (TextUtils.isEmpty(card)){/*
            new AlertDialog.Builder(context)
                    .setTitle("消息提示")
                    .setMessage("请填写身份证号")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();*/
            ToastUtil.showToast(context, "请填写身份证号");
            return false;
        }
        if (card.length() == 15 || card.length() == 18){
            return true;
        }/*
        new AlertDialog.Builder(context)
                .setTitle("消息提示")
                .setMessage("请填写正确的身份证号")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();*/
        ToastUtil.showToast(context, "请填写正确的身份证号");
        return false;

    }

    public static boolean CheckEmail(String email, Context context){

        MyLog.e("CheckUserInfo", "CheckEmail(CheckUserInfo.java:106)" + email);
        if (TextUtils.isEmpty(email)){/*
            new AlertDialog.Builder(context)
                    .setTitle("消息提示")
                    .setMessage("请填写邮箱")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();*/
            ToastUtil.showToast(context, "请填写工作邮箱");
            return false;
        }

        if (!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){/*
            new AlertDialog.Builder(context)
                    .setTitle("消息提示")
                    .setMessage("请填写正确的邮箱")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();*/
            ToastUtil.showToast(context, "请填写正确的工作邮箱");
            return false;
        }
        return true;
    }

    public static boolean CheckCity(String city, Context context){

        if (!TextUtils.isEmpty(city) && !city.matches("^[\\u4e00-\\u9fa5]{0,}$")){
            /*new AlertDialog.Builder(context)
                    .setTitle("消息提示")
                    .setMessage("请填写正确的城市")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();*/
            ToastUtil.showToast(context, "请填写正确的城市");
            return false;
        }
        return true;

    }






}
