package com.mvsolutions.snap.ui.finalfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mvsolutions.snap.R;
import com.mvsolutions.snap.services.ApiUtils;
import com.mvsolutions.snap.services.VerifyRequest;
import com.mvsolutions.snap.services.VerifyResponse;
import com.mvsolutions.snap.services.VerifyService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalFragment extends Fragment {

    private FinalViewModel mViewModel;
    String naicNo;
    String policyNo;
    String responseCode;
    String compName;
    String errorMessage;

    public static FinalFragment newInstance() {
        return new FinalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_final, container, false);

        TextView txt1 = root.findViewById(R.id.txt);
        TextView txt2 = root.findViewById(R.id.txt1);
        TextView txt3 = root.findViewById(R.id.txt2);
        TextView txt4 = root.findViewById(R.id.txt3);
        TextView txt5 = root.findViewById(R.id.txt4);

        assert getArguments() != null;
        String plate = getArguments().getString("plate");
        String state = getArguments().getString("state");

        VerifyService mAPIService = ApiUtils.getAPIService();
        assert plate != null;
        assert state != null;
        VerifyRequest request = new VerifyRequest(plate.toUpperCase(), state.toUpperCase());
        mAPIService.verify(request).enqueue(new Callback<VerifyResponse>() {
            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {
                responseCode = response.body().getResponseCode();
                policyNo = response.body().getPolicyNo();
                naicNo = response.body().getNaicNo();
                compName = response.body().getCompName();
                errorMessage = response.body().getErrorMessage();

                switch (responseCode) {
                    case "ER":
                        txt1.setText("Error, please close application and try again.");
                        txt2.setText("Error Message: " + errorMessage);
                        break;
                    case "CO":
                        txt1.setText("Success");
                        txt2.setText("Policy Number: " + policyNo);
                        txt3.setText("NAIC Number: " + naicNo);
                        txt4.setText("Company Name: " + compName);
                        break;
                    case "UN":

                        break;
                    case "NF":

                        break;
                }
            }

            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error, please close application and try again.", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}