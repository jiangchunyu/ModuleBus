package com.cangwang.page_name;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cangwang.core.IBaseClient;
import com.cangwang.core.ModuleBus;
import com.cangwang.core.ModuleEvent;
import com.cangwang.core.cwmodule.ELBasicModule;
import com.cangwang.core.cwmodule.ELModuleContext;

/**
 * Created by air on 2016/12/28.
 */

public class PageNameModule extends ELBasicModule {
    private Activity activity;
    private ViewGroup parentViewGroup;
    private View pageNameView;
    private TextView pageTitle;

    @Override
    public void init(ELModuleContext moduleContext, String extend) {
        super.init(moduleContext, extend);
        activity = moduleContext.getActivity();
        parentViewGroup = moduleContext.getView(0);
        this.moduleContext = moduleContext;
        initView();
        ModuleBus.getInstance().register(this);
    }

    private void initView(){
        pageNameView = LayoutInflater.from(activity).inflate(R.layout.page_name_layout,parentViewGroup,true);
        pageTitle = (TextView) pageNameView.findViewById(R.id.page_title);
    }

    @Override
    public void onDestroy() {
        ModuleBus.getInstance().unregister(this);
        super.onDestroy();
    }

    @ModuleEvent(coreClientClass = IBaseClient.class)
    public void changeNameTxt(String name){
        pageTitle.setText(name);
    }
}
