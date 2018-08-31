package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.a51zhipaiwang.worksend.R;


public class PaiDanFragment extends BaseFragment {

    private Button btn_sure;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pai_dan, null);
        init(view);
        setRegister();
        return view;
    }

    private void init(View view){
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
    }

    private void setRegister(){
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }


}
