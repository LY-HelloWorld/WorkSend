package com.a51zhipaiwang.worksend.Personal.resumeeditactivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.editprogressactivity.EditProgressActivity;
import com.a51zhipaiwang.worksend.Personal.editworkexperence.EditWorkExperenceActivity;
import com.a51zhipaiwang.worksend.Personal.educationeditactivity.EducationEditActivity;
import com.a51zhipaiwang.worksend.Personal.entity.ResumeEntity;
import com.a51zhipaiwang.worksend.Personal.entity.TblEducationalExperience;
import com.a51zhipaiwang.worksend.Personal.entity.TblProjectExperience;
import com.a51zhipaiwang.worksend.Personal.entity.TblWorkExperience;
import com.a51zhipaiwang.worksend.Personal.jobintentionactivity.JobIntentionActivity;
import com.a51zhipaiwang.worksend.Personal.resumeeditactivity.contract.IResumeEditContract;
import com.a51zhipaiwang.worksend.Personal.resumeeditactivity.presenter.IResumeEditPresenter;
import com.a51zhipaiwang.worksend.Personal.uploadimageactivity.UpLoadImageActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;
import com.a51zhipaiwang.worksend.Utils.IdCoverText;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/7/25
 *     desc   :
 *     version: 1.0
 *
        常量
        字段
        构造函数
        重写函数和回调
        公有函数
        私有函数
        内部类或接口
 * </pre>
 */
public class ResumeEditActivity extends BaseActivity implements IResumeEditContract.View{

    private IResumeEditContract.Presenter presenter;
    private ResumeEditActivityClicklistener resumeEditActivityClicklistener;
    private ArrayList<TblEducationalExperience> tblEducationalExperiences;
    private ArrayList<TblWorkExperience> tblWorkExperiences;
    private ArrayList<TblProjectExperience> tblProjectExperiences;
    private ResumeEntity resumeEntity;

    private String onLoadLogPath;

    private GlideUtil glideUtil;

    private LinearLayout lv_add_edu;
    private LinearLayout lv_edu_content;
    private LinearLayout lv_add_work;
    private LinearLayout lv_work_content;
    private LinearLayout lv_progress_content;
    private LinearLayout lv_add_progress;
    private LinearLayout lv_edit_need_work;
    private LinearLayout liv_job_intention;
    private ImageView img_upload_video;
    private EditText edx_name;
    private EditText edx_age;
    private EditText edx_location;
    private RadioGroup rag_sex;
    private RadioButton manRadioBt;
    private RadioButton wemanRadioBt;
    private TextView tx_work_type;
    private TextView tx_expect_work;
    private TextView tx_work_experence;
    private TextView tx_expect_money;
    private TextView tx_expect_city;
    private TextView tx_education;
    private TextView tx_work_state;
    private TextView tilte_text;
    private ImageView return_image;
    private EditText edx_skill_one;
    private EditText edx_skill_two;
    private EditText edx_skill_three;
    private EditText edx_skill_description;
    private EditText edx_awards_one;
    private EditText edx_awards_two;
    private EditText edx_awards_three;
    private Button btn_save;

    public static void startResumeEditActivity(Context context){
        context.startActivity(new Intent(context, ResumeEditActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_edit);
        init();
        setRegister();
        presenter.getResume(new HashMap());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.UpLoad:
                if (resultCode == RESULT_OK){
                    //img_personal_upload.setImageResource(R.mipmap.up_load_success);
                    resumeEntity.setCol4(data.getStringExtra("fengMianPath"));
                    resumeEntity.setVisualscreen(data.getStringExtra("videoPath"));
                    MyLog.e("ResumeEditActivity", "onActivityResult(ResumeEditActivity.java:116)" + data.getStringExtra("fengMianPath"));
                    MyLog.e("ResumeEditActivity", "onActivityResult(ResumeEditActivity.java:116)" + data.getStringExtra("videoPath"));
                    glideUtil.GlideImage(resumeEntity.getCol4(), ResumeEditActivity.this, img_upload_video);
                }
                break;
            case MyApplication.EduEdit:
                if (resultCode == RESULT_OK){
                    TblEducationalExperience tblEducationalExperience = (TblEducationalExperience) data.getExtras().getSerializable("tblEducationalExperience");
                    tblEducationalExperiences.add(tblEducationalExperience);
                    addEdu(tblEducationalExperience);
                }
                break;
            case MyApplication.WorkEdit:
                if (resultCode == RESULT_OK){
                    TblWorkExperience tblEducationalExperience = (TblWorkExperience) data.getExtras().getSerializable("tblWorkExperience");
                    tblWorkExperiences.add(tblEducationalExperience);
                    addWork(tblEducationalExperience);
                }
                break;
            case MyApplication.ProgressEdit:
                if (resultCode == RESULT_OK){
                    TblProjectExperience tblProjectExperience = (TblProjectExperience) data.getExtras().getSerializable("tblProjectExperience");
                    tblProjectExperiences.add(tblProjectExperience);
                    addProgress(tblProjectExperience);
                }
                break;
            case MyApplication.WorkIntentionEdit:
                if (resultCode == RESULT_OK){
                    liv_job_intention.setVisibility(View.VISIBLE);
                    ResumeEntity resumeEntity1 = (ResumeEntity) data.getExtras().getSerializable("resumeEntity");
                    resumeEntity.setLongitude(resumeEntity1.getLongitude());
                    resumeEntity.setLatitude(resumeEntity1.getLatitude());
                    resumeEntity.setExpectCity(resumeEntity1.getExpectCity());
                    resumeEntity.setExpectedcareerName(resumeEntity1.getExpectedcareerName());
                    resumeEntity.setExpectedcareer(resumeEntity1.getExpectedcareer());
                    resumeEntity.setWorkingLife(resumeEntity1.getWorkingLife());
                    resumeEntity.setEducation(resumeEntity1.getEducation());
                    resumeEntity.setSalaryexpectation(resumeEntity1.getSalaryexpectation());
                    resumeEntity.setPositionstatus(resumeEntity1.getPositionstatus());
                    resumeEntity.setCol2(resumeEntity1.getCol2());
                    initWorkIntention();
                }
                break;

        }
    }



    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }


    @Override
    public void setResume(ResumeEntity resume) {
        resumeEntity = resume;
        initResumeInfo(resumeEntity);
    }

    @Override
    public void returnInfo(String string) {
        if (string.equals("success")){
            ToastUtil.showToastTwo("编辑成功");
            this.finish();
        }else {
            ToastUtil.showToastTwo("请首先完善您的个人资料!");
        }
    }

    private void init(){
        presenter = new IResumeEditPresenter(this);
        glideUtil = new GlideUtil();
        resumeEntity = new ResumeEntity();
        tblEducationalExperiences = new ArrayList<>();
        tblWorkExperiences = new ArrayList<>();
        tblProjectExperiences = new ArrayList<>();
        lv_add_edu = (LinearLayout) findViewById(R.id.lv_add_edu);
        lv_edu_content = (LinearLayout) findViewById(R.id.lv_edu_content);
        lv_add_work = (LinearLayout) findViewById(R.id.lv_add_work);
        lv_work_content = (LinearLayout) findViewById(R.id.lv_work_content);
        lv_add_progress = (LinearLayout) findViewById(R.id.lv_add_progress);
        lv_progress_content = (LinearLayout) findViewById(R.id.lv_progress_content);
        lv_edit_need_work = (LinearLayout) findViewById(R.id.lv_edit_need_work);
        liv_job_intention = (LinearLayout) findViewById(R.id.liv_job_intention);
        img_upload_video = (ImageView) findViewById(R.id.img_upload_video);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("个人简历");
        return_image = (ImageView) findViewById(R.id.return_image);
        edx_name = (EditText) findViewById(R.id.edx_name);
        edx_age = (EditText) findViewById(R.id.edx_age);
        edx_location = (EditText) findViewById(R.id.edx_location);
        rag_sex = (RadioGroup) findViewById(R.id.rag_sex);
        manRadioBt = (RadioButton) findViewById(R.id.manRadioBt);
        wemanRadioBt = (RadioButton) findViewById(R.id.wemanRadioBt);

        tx_work_type = (TextView) findViewById(R.id.tx_work_type);
        tx_expect_work = (TextView) findViewById(R.id.tx_expect_work);
        tx_work_experence = (TextView) findViewById(R.id.tx_work_experence);
        tx_expect_money = (TextView) findViewById(R.id.tx_expect_money);
        tx_expect_city = (TextView) findViewById(R.id.tx_expect_city);
        tx_education = (TextView) findViewById(R.id.tx_education);
        tx_work_state = (TextView) findViewById(R.id.tx_work_state);

        edx_skill_one = (EditText) findViewById(R.id.edx_skill_one);
        edx_skill_two = (EditText) findViewById(R.id.edx_skill_two);
        edx_skill_three = (EditText) findViewById(R.id.edx_skill_three);
        edx_skill_description = (EditText) findViewById(R.id.edx_skill_description);
        edx_awards_one = (EditText) findViewById(R.id.edx_awards_one);
        edx_awards_two = (EditText) findViewById(R.id.edx_awards_two);
        edx_awards_three = (EditText) findViewById(R.id.edx_awards_three);

        btn_save = (Button) findViewById(R.id.btn_save);
        liv_job_intention.setVisibility(View.GONE);
    }

    private void setRegister(){
        resumeEditActivityClicklistener = new ResumeEditActivityClicklistener();
        lv_add_edu.setOnClickListener(resumeEditActivityClicklistener);
        lv_add_work.setOnClickListener(resumeEditActivityClicklistener);
        lv_edit_need_work.setOnClickListener(resumeEditActivityClicklistener);
        lv_add_progress.setOnClickListener(resumeEditActivityClicklistener);
        img_upload_video.setOnClickListener(resumeEditActivityClicklistener);
        btn_save.setOnClickListener(resumeEditActivityClicklistener);
        return_image.setOnClickListener(resumeEditActivityClicklistener);
        rag_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getId()) {
                    case R.id.rag_sex:
                        if (manRadioBt.isChecked()){
                            resumeEntity.setSex("010");
                        }
                        if (wemanRadioBt.isChecked()){
                            resumeEntity.setSex("020");
                        }
                        break;
                }
            }
        });
    }

    /**
     * 根据请求返回 设置简历
     * 当前只完善了 三个动态添加和 求职意向初始化
     * @param resumeEntity
     */
    private void initResumeInfo(ResumeEntity resumeEntity){
        this.resumeEntity = resumeEntity;
        tblEducationalExperiences = resumeEntity.getEe();
        tblProjectExperiences = resumeEntity.getPe();
        tblWorkExperiences = resumeEntity.getEs();
        for (int i = 0, len = resumeEntity.getEe().size(); i < len; i++) {
            addEdu(resumeEntity.getEe().get(i));
        }
        for (int i = 0, len = resumeEntity.getPe().size(); i < len; i++) {
            addProgress(resumeEntity.getPe().get(i));
        }
        for (int i = 0, len = resumeEntity.getEs().size(); i < len; i++) {
            addWork(resumeEntity.getEs().get(i));
        }
        initInfo();
        initWorkIntention();

    }

    private void initInfo(){

        edx_name.setText(resumeEntity.getUserName());
        edx_age.setText(resumeEntity.getUserAge());
        edx_location.setText(resumeEntity.getPresentaddress());
        if ("020".equals(resumeEntity.getSex())){
            wemanRadioBt.setChecked(true);
        }else {
            manRadioBt.setChecked(true);
        }
        if (!TextUtils.isEmpty(resumeEntity.getCol4())){
            glideUtil.GlideImage(resumeEntity.getCol4(), ResumeEditActivity.this, img_upload_video);
        }else {
            img_upload_video.setImageResource(R.drawable.img_personal_upload);
        }
        edx_skill_one.setText(resumeEntity.getMasteryOfSkillsOne());
        edx_skill_two.setText(resumeEntity.getMasteryOfSkillsTwo());
        edx_skill_three.setText(resumeEntity.getMasteryOfSkillsThree());
        edx_skill_description.setText(resumeEntity.getOtherMasterySkills());
        edx_awards_one.setText(resumeEntity.getOtherAwardsone());
        edx_awards_two.setText(resumeEntity.getOtherAwardstwo());
        edx_awards_three.setText(resumeEntity.getOtherAwardsthree());
    }

    private void initWorkIntention(){
        if (!TextUtils.isEmpty(resumeEntity.getExpectedcareerName())){
            liv_job_intention.setVisibility(View.VISIBLE);
            tx_work_state.setText(IdCoverText.coverWorkStatus(resumeEntity.getCol2()));
            tx_expect_work.setText(resumeEntity.getExpectedcareerName());
            MyLog.e("ResumeEditActivity", "initWorkIntention(ResumeEditActivity.java:300)" + resumeEntity.getExpectedcareerName());
            tx_work_experence.setText(IdCoverText.coverWorkExprence(resumeEntity.getWorkingLife()));
            tx_expect_money.setText(IdCoverText.coverMoney(resumeEntity.getSalaryexpectation()));
            tx_expect_city.setText(resumeEntity.getExpectCity());
            tx_education.setText(IdCoverText.coverEducation(resumeEntity.getEducation()));
            tx_work_type.setText(IdCoverText.coverWorkType(resumeEntity.getPositionstatus()));
        }else {
            liv_job_intention.setVisibility(View.GONE);
        }

    }

    private void addEdu(TblEducationalExperience tblEducationalExperience){
        View view = View.inflate(ResumeEditActivity.this, R.layout.add_edu_layout, null);
        ((TextView)view.findViewById(R.id.tx_school_name)).setText(tblEducationalExperience.getUniversityname());
        ((TextView)view.findViewById(R.id.tx_time)).setText(tblEducationalExperience.getEnrolmenttime());
        ((TextView)view.findViewById(R.id.tx_profession)).setText(tblEducationalExperience.getProfessionalcategory());
        lv_edu_content.addView(view);

    }


    private void addWork(TblWorkExperience tblWorkExperience){
        View view = View.inflate(ResumeEditActivity.this, R.layout.add_work_layout, null);
        ((TextView)view.findViewById(R.id.tx_school_name)).setText(tblWorkExperience.getCorporatename());
        ((TextView)view.findViewById(R.id.tx_time)).setText(tblWorkExperience.getEndtime());
        ((TextView)view.findViewById(R.id.tx_profession)).setText(tblWorkExperience.getPosition());
        lv_work_content.addView(view);

    }



    private void addProgress(TblProjectExperience tblProjectExperience){
        View view = View.inflate(ResumeEditActivity.this, R.layout.add_program_experence, null);
        ((TextView)view.findViewById(R.id.edx_progress_name)).setText(tblProjectExperience.getEntryname());
        ((TextView)view.findViewById(R.id.edx_progress_start_time)).setText(tblProjectExperience.getStarttime());
        ((TextView)view.findViewById(R.id.edx_progress_end_time)).setText(tblProjectExperience.getEndtime());
        ((TextView)view.findViewById(R.id.edx_work)).setText(tblProjectExperience.getPosition());
        ((TextView)view.findViewById(R.id.edx_main_duty)).setText(tblProjectExperience.getResponsibilities());
        lv_progress_content.addView(view);

    }


    private void returnLogoSubmitSuccess(String info) {
        try {
            JSONObject logoJson = new JSONObject(info);
            if (logoJson.getString("result").equals("上传成功")){
                onLoadLogPath = MyApplication.upLoadPath + logoJson.getString("lj");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean checkInfo(){
        if (TextUtils.isEmpty(edx_name.getText())){
            ToastUtil.showToastTwo("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(resumeEntity.getSex())){
            ToastUtil.showToastTwo("请选择性别");
            return false;
        }
        if (TextUtils.isEmpty(edx_age.getText())){
            ToastUtil.showToastTwo("请输入年龄");
            return false;
        }
        if (TextUtils.isEmpty(edx_location.getText())){
            ToastUtil.showToastTwo("请输入地址");
            return false;
        }
        if (TextUtils.isEmpty(resumeEntity.getCol4()) || TextUtils.isEmpty(resumeEntity.getVisualscreen())){
            ToastUtil.showToastTwo("请上传视频简历和视频封面");
            return false;
        }
        return true;
    }

    private void setInfo(){
        if (resumeEntity != null){
            resumeEntity.setUserName(edx_name.getText().toString());
            resumeEntity.setUserAge(edx_age.getText().toString());
            resumeEntity.setPresentaddress(edx_location.getText().toString());
            resumeEntity.setMasteryOfSkillsOne(edx_skill_one.getText().toString());
            resumeEntity.setMasteryOfSkillsTwo(edx_skill_two.getText().toString());
            resumeEntity.setMasteryOfSkillsThree(edx_skill_three.getText().toString());
            resumeEntity.setOtherMasterySkills(edx_skill_description.getText().toString());
            resumeEntity.setOtherAwardsone(edx_awards_one.getText().toString());
            resumeEntity.setOtherAwardstwo(edx_awards_two.getText().toString());
            resumeEntity.setOtherAwardsthree(edx_awards_three.getText().toString());
        }
    }


    class ResumeEditActivityClicklistener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lv_add_edu:
                    EducationEditActivity.startEducationEditActivity(ResumeEditActivity.this, MyApplication.EduEdit);
                    break;
                case R.id.lv_add_work:
                    EditWorkExperenceActivity.startEditWorkExperenceActivity(ResumeEditActivity.this, MyApplication.WorkEdit);
                    break;
                case R.id.lv_add_progress:
                    EditProgressActivity.startEditProgressActivity(ResumeEditActivity.this, MyApplication.ProgressEdit);
                    break;
                case R.id.img_upload_video:
                    //UpLoadFile.UpLoadFile(ResumeEditActivity.this, MyApplication.UpLoadLog, "image/*");
                    UpLoadImageActivity.startUpLoadImageActivity(ResumeEditActivity.this, MyApplication.UpLoad);
                    break;
                case R.id.lv_edit_need_work:
                    JobIntentionActivity.startJobIntentionActivity(ResumeEditActivity.this, MyApplication.WorkIntentionEdit);
                    break;
                case R.id.return_image:
                    ResumeEditActivity.this.finish();
                    break;
                case R.id.btn_save:
                    ToastUtil.showToastTwo("提交");
                    if (checkInfo()){
                        resumeEntity.setEe(tblEducationalExperiences);
                        resumeEntity.setEs(tblWorkExperiences);
                        resumeEntity.setPe(tblProjectExperiences);
                        setInfo();
                        presenter.submitResume(resumeEntity);
                    }
                    break;
            }
        }
    }


}
