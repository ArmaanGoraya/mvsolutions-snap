package com.mvsolutions.snap.ui.homefragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;
import com.mvsolutions.snap.R;
import com.mvsolutions.snap.ui.displayfragment.DisplayFragment;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;

    private ImageView imageView;
    private Button captureImageButton, detectButton, pickButton;
    private Bitmap imageBitmap;
    private TextView textView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_LOAD_IMAGE = 101;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        detectButton = root.findViewById(R.id.action_homeFragment_to_displayFragment2);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        imageView = root.findViewById(R.id.home_image_view_img);
        textView = root.findViewById(R.id.textView);
        captureImageButton = root.findViewById(R.id.capture_image_btn);
        detectButton = root.findViewById(R.id.detect_text_btn);

        captureImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                captureImage();
            }
        });
        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageBitmap == null){
                    Toast.makeText(getContext(), "No Image Available", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
                FirebaseVisionTextDetector visionTextDetector = FirebaseVision.getInstance().getVisionTextDetector();
                final ArrayList<String> textBlobs = new ArrayList<>();
                visionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        List<FirebaseVisionText.Block> textBlocks = firebaseVisionText.getBlocks();

                        if (textBlocks.size() == 0) {
                            Toast.makeText(getContext(), "No Text Found", Toast.LENGTH_SHORT).show();
                        } else {
                            for (FirebaseVisionText.Block block : textBlocks) {
                                String text = block.getText();

                                textBlobs.add(text);
                            }
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("textblobs", textBlobs);
                            DisplayFragment displayFragment = new DisplayFragment();
                            displayFragment.setArguments(bundle);

                            Navigation.findNavController(root).navigate(R.id.action_homeFragment_to_displayFragment2, bundle);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        Log.d("Error", e.getMessage());
                    }
                });


            }
        });
        return root;
    }

//    private void detectTextFromImage() {
//        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
//        FirebaseVisionTextDetector visionTextDetector = FirebaseVision.getInstance().getVisionTextDetector();
//        final ArrayList<String> textBlobs = new ArrayList<>();
//        visionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
//            @Override
//            public void onSuccess(FirebaseVisionText firebaseVisionText) {
//                List<FirebaseVisionText.Block> textBlocks = firebaseVisionText.getBlocks();
//
//                if (textBlocks.size() == 0) {
//                    Toast.makeText(getContext(), "No Text Found", Toast.LENGTH_SHORT).show();
//                } else {
//                    for (FirebaseVisionText.Block block : textBlocks) {
//                        String text = block.getText();
//
//                        textBlobs.add(text);
//                    }
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                Log.d("Error", e.getMessage());
//            }
//        });
//    }


    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            textView.setVisibility(View.GONE);
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
