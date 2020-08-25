package com.mvsolutions.snap.ui.display;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mvsolutions.snap.R;

import java.util.ArrayList;
import java.util.Objects;

public class DisplayFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.mvsolutions.snap.ui.display.DisplayViewModel displayViewModel = ViewModelProviders.of(this).get(DisplayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_display, container, false);

        assert getArguments() != null;
        ArrayList<String> txtBlb = new ArrayList<>(Objects.requireNonNull(getArguments().getStringArrayList("textblobs")));

        for(int i = 0; i < txtBlb.size(); i++ ) {
            View selectorHeader = root.findViewById(R.id.SelectorHeader);
            Button dynamicButton = new Button(this.getContext());
            dynamicButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //Edited this line to see if I can make the corners of the dynamic buttons rounded
            //If error occurs delete line
            dynamicButton.setBackgroundResource(R.drawable.rounded_corners);
            dynamicButton.setTextColor(Color.WHITE);
            dynamicButton.setText(txtBlb.get(i));

            LinearLayout linearLayout = root.findViewById(R.id.SelectorLinearLayout);
            linearLayout.addView(dynamicButton);

            TextView txt = new TextView(this.getContext());
            txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            txt.setTextSize(7);
            linearLayout.addView(txt);
        }
        Button typeOwn = new Button(this.getContext());
        typeOwn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        typeOwn.setText("Click to manually enter");
        typeOwn.setBackgroundResource(R.drawable.rounded_corners);
        typeOwn.setTextColor(Color.WHITE);
        final LinearLayout linearLayout = root.findViewById(R.id.SelectorLinearLayout);
        linearLayout.addView(typeOwn);
        typeOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                final TextInputEditText txt = new TextInputEditText(Objects.requireNonNull(getContext()));
                txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.addView(txt);
                Button btn = new Button(getContext());
                btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                btn.setText("Done");
                linearLayout.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = Objects.requireNonNull(txt.getText()).toString();
                        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return root;
    }




}