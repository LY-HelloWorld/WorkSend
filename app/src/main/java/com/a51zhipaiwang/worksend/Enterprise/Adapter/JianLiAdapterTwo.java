package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiManagerActivity.JianLiManagerActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.IdCoverText;
import com.a51zhipaiwang.worksend.Utils.MyLog;

import java.util.ArrayList;



public class JianLiAdapterTwo extends BaseAdapter {

    //全部展示
    private boolean showAll = false;
    //展示个数
    private int showCount = 0;

    private ArrayList<SampleJianLiData> sampleJianLiData;

    private BaseActivity context;

    private boolean showDelete;

    private int showInfoNum;

    /**
     *
     * @param showAll 是否展示所有item
     * @param context 上下文
     * @param showDelete 是否展示删除按钮
     * @param showInfoNum 信息展示数目
     * @param sampleJianLiData 简历数据
     */
    public JianLiAdapterTwo(boolean showAll, BaseActivity context, boolean showDelete, int showInfoNum, ArrayList<SampleJianLiData> sampleJianLiData) {
        this.showAll = showAll;
        this.context = context;
        this.showDelete = showDelete;
        this.showInfoNum = showInfoNum;
        this.sampleJianLiData = sampleJianLiData;
    }

    public JianLiAdapterTwo(int showCount, BaseActivity context, boolean showDelete, int showInfoNum, ArrayList<SampleJianLiData> sampleJianLiData) {
        this.showCount = showCount;
        this.context = context;
        this.showDelete = showDelete;
        this.showInfoNum = showInfoNum;
        this.sampleJianLiData = sampleJianLiData;
    }

    @Override
    public int getCount() {
        if (sampleJianLiData == null){
            return 0;
        }
        if (showAll){
            return sampleJianLiData.size();
        }
        if (sampleJianLiData.size() >= showCount){
            return showCount;
        }else {
            return sampleJianLiData.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return sampleJianLiData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int i, View contentView, ViewGroup viewGroup) {
        JianLiHodler jianLiHodler;
        final SampleJianLiData data = sampleJianLiData.get(i);
        if (contentView == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.item_jian_li_two, null);
            jianLiHodler = new JianLiHodler();
            jianLiHodler.touXiangImage = contentView.findViewById(R.id.touXiangImage);
            jianLiHodler.nameText = contentView.findViewById(R.id.nameText);
            jianLiHodler.moneyText = contentView.findViewById(R.id.moneyText);
            jianLiHodler.infomationsText = contentView.findViewById(R.id.infomationsText);
            jianLiHodler.distanceText = contentView.findViewById(R.id.distanceText);
            jianLiHodler.workStateText = contentView.findViewById(R.id.workStateText);
            jianLiHodler.cityText = contentView.findViewById(R.id.cityText);
            jianLiHodler.readJianLiBt = contentView.findViewById(R.id.readJianLiBt);
            jianLiHodler.deleteJianLiBt = contentView.findViewById(R.id.deleteJianLiBt);
            jianLiHodler.choiceImage = contentView.findViewById(R.id.choiceImage);

            contentView.setTag(jianLiHodler);

        }else {
            jianLiHodler = (JianLiHodler) contentView.getTag();
        }
        jianLiHodler.nameText.setText(data.getUserName());
        jianLiHodler.infomationsText.setText(IdCoverText.coverSex(data.getSex()) + " | " + data.getUserAge() + " | "
                + IdCoverText.coverEducation(data.getEducation()) + " | " + IdCoverText.coverWorkExprence(data.getWorkingLife()));
        jianLiHodler.distanceText.setText("距离" + data.getKilometre() + "km");
        jianLiHodler.workStateText.setText(IdCoverText.coverWorkStatus(data.getPositionstatus()));
        jianLiHodler.moneyText.setText(IdCoverText.coverMoney(data.getSalaryexpectation()));
        jianLiHodler.cityText.setText(data.getExpectCity());
        if (showDelete){
            jianLiHodler.deleteJianLiBt.setVisibility(View.VISIBLE);
            jianLiHodler.deleteJianLiBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyLog.e("JianLiAdapter", "onClick(JianLiAdapter.java:122)" + 123);
                    ((JianLiManagerActivity)context).deletJianLiList(sampleJianLiData.get(i).getId());
                }
            });
        }
        if (data.isChoice()){
            jianLiHodler.choiceImage.setVisibility(View.VISIBLE);
        }else {
            jianLiHodler.choiceImage.setVisibility(View.INVISIBLE);
        }
        return contentView;
    }


    public void setSampleJianLiData(ArrayList<SampleJianLiData> sampleJianLiData) {
        this.sampleJianLiData = sampleJianLiData;
    }

    class JianLiHodler{
        ImageView touXiangImage;
        ImageView choiceImage;
        TextView nameText;
        TextView moneyText;
        TextView infomationsText;
        TextView distanceText;
        TextView workStateText;
        TextView workText;
        TextView cityText;
        Button readJianLiBt;
        Button deleteJianLiBt;

    }


}
