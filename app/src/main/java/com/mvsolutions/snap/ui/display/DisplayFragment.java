package com.mvsolutions.snap.ui.display;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mvsolutions.snap.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class DisplayFragment extends Fragment {

    private DisplayViewModel DisplayViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DisplayViewModel =
                ViewModelProviders.of(this).get(DisplayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_display, container, false);

        assert getArguments() != null;
        ArrayList<String> txtBlb = new ArrayList<>(Objects.requireNonNull(getArguments().getStringArrayList("textblobs")));

        for(int i = 0; i < txtBlb.size(); i++ ) {
            View selectorHeader = root.findViewById(R.id.SelectorHeader);
            Button dynamicButton = new Button(this.getContext());
            dynamicButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dynamicButton.setVisibility(View.VISIBLE);
            dynamicButton.setText(txtBlb.get(i));

            LinearLayout linearLayout = root.findViewById(R.id.SelectorLinearLayout);
            linearLayout.addView(dynamicButton);
        }
        Button dynamicButton = new Button(this.getContext());
        dynamicButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dynamicButton.setText("Click to manually  enter");
        final LinearLayout linearLayout = root.findViewById(R.id.SelectorLinearLayout);
        linearLayout.addView(dynamicButton);
        dynamicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                final TextInputEditText txt = new TextInputEditText(getContext());
                txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.addView(txt);
                Button btn = new Button(getContext());
                btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                btn.setText("Done");
                linearLayout.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = txt.getText().toString();
                        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return root;
    }




}