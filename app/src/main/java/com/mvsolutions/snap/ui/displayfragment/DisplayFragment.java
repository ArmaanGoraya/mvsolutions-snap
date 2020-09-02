package com.mvsolutions.snap.ui.displayfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.mvsolutions.snap.R;
import com.mvsolutions.snap.ui.platefragment.PlateFragment;

import java.util.ArrayList;
import java.util.Objects;

public class DisplayFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.mvsolutions.snap.ui.displayfragment.DisplayViewModel displayViewModel = ViewModelProviders.of(this).get(DisplayViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_display, container, false);

        assert getArguments() != null;
        ArrayList<String> txtBlb = new ArrayList<>(Objects.requireNonNull(getArguments().getStringArrayList("textblobs")));

        for(int i = 0; i < txtBlb.size(); i++ ) {
            final LinearLayout linearLayout = root.findViewById(R.id.SelectorLinearLayout);
            linearLayout.removeAllViews();
            final Button dynamicButton = new Button(this.getContext());
            dynamicButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            dynamicButton.setTextColor(Color.WHITE);
            dynamicButton.setText(txtBlb.get(i));
            dynamicButton.setBackgroundResource(R.drawable.rounded_corners);
            linearLayout.addView(dynamicButton);

            TextView txt = new TextView(this.getContext());
            txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            txt.setTextSize(10);
            linearLayout.addView(txt);

            dynamicButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String plate = Objects.requireNonNull(dynamicButton.getText()).toString();
                    linearLayout.removeAllViews();

                    Bundle b = new Bundle();
                    b.putString("plate", plate);
                    PlateFragment plateFragment = new PlateFragment();
                    plateFragment.setArguments(b);

                    Navigation.findNavController(root).navigate(R.id.action_displayFragment_to_plateFragment, b);
                }
            });
        }
        Button typeOwn = new Button(this.getContext());
        typeOwn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        typeOwn.setText("Click to manually enter");
        typeOwn.setBackgroundResource(R.drawable.rounded_corners);
        typeOwn.setTextColor(Color.WHITE);
        final LinearLayout linearLayout = root.findViewById(R.id.SelectorLinearLayout);
        linearLayout.addView(typeOwn);

        TextView txt = new TextView(this.getContext());
        txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        txt.setTextSize(10);
        linearLayout.addView(txt);

        typeOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                final EditText txt = new EditText(Objects.requireNonNull(getContext()));
                txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.addView(txt);
                Button btn = new Button(getContext());
                btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                btn.setText("Done");
                btn.setTextColor(Color.WHITE);
                btn.setBackgroundResource(R.drawable.rounded_corners);
                linearLayout.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String plate = Objects.requireNonNull(txt.getText()).toString();
                        linearLayout.removeAllViews();

                        Bundle b = new Bundle();
                        b.putString("plate", plate);
                        PlateFragment plateFragment = new PlateFragment();
                        plateFragment.setArguments(b);

                        Navigation.findNavController(root).navigate(R.id.action_displayFragment_to_plateFragment, b);
                    }
                });
            }
        });
        return root;
    }
}