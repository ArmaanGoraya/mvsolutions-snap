package com.mvsolutions.snap.ui.platefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mvsolutions.snap.R;
import com.mvsolutions.snap.ui.finalfragment.FinalFragment;

public class PlateFragment extends Fragment {

    private PlateViewModel mViewModel;

    public static PlateFragment newInstance() {
        return new PlateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_plate, container, false);

        LinearLayout linearLayout = root.findViewById(R.id.LinearLayout1);


        assert getArguments() != null;
        String p1 = getArguments().getString("plate");
        final String[] s1 = new String[1];

        TextView t1 = root.findViewById(R.id.t1);
        Spinner sp = (Spinner) root.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1[0] = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), s1[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        t1.setText(p1);


        Button btn = root.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("plate", p1);
                b.putString("state", s1[0]);
                FinalFragment finalFragment = new FinalFragment();
                finalFragment.setArguments(b);
                Navigation.findNavController(root).navigate(R.id.action_plateFragment_to_finalFragment, b);
            }
        });

        return root;
    }
}