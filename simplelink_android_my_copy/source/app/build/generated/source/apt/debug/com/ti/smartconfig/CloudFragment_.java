//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.ti.smartconfig;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ti.smartconfig.R.layout;
import com.ti.smartconfig.utils.SharedPreferencesInterface_;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CloudFragment_
    extends com.ti.smartconfig.CloudFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    @Override
    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.cloud_view, container, false);
        }
        return contentView_;
    }

    @Override
    public void onDestroyView() {
        contentView_ = null;
        super.onDestroyView();
    }

    private void init_(Bundle savedInstanceState) {
        prefs = new SharedPreferencesInterface_(getActivity());
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static CloudFragment_.FragmentBuilder_ builder() {
        return new CloudFragment_.FragmentBuilder_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        push_button_radio_button = ((RadioButton) hasViews.findViewById(com.ti.smartconfig.R.id.push_button_radio_button));
        freetext_edittext = ((EditText) hasViews.findViewById(com.ti.smartconfig.R.id.freetext_edittext));
        cloud_configuration_button = ((Button) hasViews.findViewById(com.ti.smartconfig.R.id.cloud_configuration_button));
        shake_board_radio_button = ((RadioButton) hasViews.findViewById(com.ti.smartconfig.R.id.shake_board_radio_button));
        trigger_radio_group = ((RadioGroup) hasViews.findViewById(com.ti.smartconfig.R.id.trigger_radio_group));
        cloud_connection_textview = ((TextView) hasViews.findViewById(com.ti.smartconfig.R.id.cloud_connection_textview));
        send_to_edittext = ((EditText) hasViews.findViewById(com.ti.smartconfig.R.id.send_to_edittext));
        if (cloud_configuration_button!= null) {
            cloud_configuration_button.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    CloudFragment_.this.cloud_configuration_button();
                }

            }
            );
        }
        if (shake_board_radio_button!= null) {
            shake_board_radio_button.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    CloudFragment_.this.shake_board_radio_button();
                }

            }
            );
        }
        if (push_button_radio_button!= null) {
            push_button_radio_button.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    CloudFragment_.this.push_button_radio_button();
                }

            }
            );
        }
        afterViews();
    }

    public static class FragmentBuilder_
        extends FragmentBuilder<CloudFragment_.FragmentBuilder_, com.ti.smartconfig.CloudFragment>
    {


        @Override
        public com.ti.smartconfig.CloudFragment build() {
            CloudFragment_ fragment_ = new CloudFragment_();
            fragment_.setArguments(args);
            return fragment_;
        }

    }

}
