package com.example.cecil.database2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */

public class AddMadFragment extends Fragment {
    private EditText MMA, HF12, HF3, HF4, fedt, Id;
    private Button BnSave, BnDate, BnKamera, BnTilbageMad;
    private TextView Dato;
    private ImageView imageView;


    private static final int CAMERA_PIC_REQUEST = 1337;

    private OnFragmentInteractionListener mListener;


    public AddMadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_mad, container, false);
        //Id = view.findViewById(R.id.txt_mad_id);
        MMA = (EditText) view.findViewById(R.id.txt_mma);
        HF12 = (EditText) view.findViewById(R.id.txt_HF12);
        HF3 = (EditText) view.findViewById(R.id.txt_HF3);
        HF4 = (EditText) view.findViewById(R.id.txt_HF4);
        fedt = (EditText) view.findViewById(R.id.txt_fedt);
        BnSave = (Button) view.findViewById(R.id.bn_save_mad);
        BnDate = (Button) view.findViewById(R.id.bn_date);
        Dato = (TextView) view.findViewById(R.id.date);
        Dato.setText("");
        BnKamera = (Button) view.findViewById(R.id.bn_kamera);
        imageView = (ImageView) view.findViewById(R.id.img_imageView);
        BnTilbageMad = view.findViewById(R.id.bn_tilbage_add);

        BnTilbageMad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).
                        addToBackStack(null).commit();
            }});

        BnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if date has been entered. This works because the user can only change it fom "" by selecting a date
                if(Dato.getText() == ""){
                    Toast.makeText(getActivity(), "Husk at tilføje dato", Toast.LENGTH_SHORT).show();
                    return;
                }

                //TODO:if date not chosen, warn user with textbox
                String datoString = Dato.getText().toString();
                String mma = MMA.getText().toString();
                String hf12 = HF12.getText().toString();
                String hf3 = HF3.getText().toString();
                String hf4 = HF4.getText().toString();
                String Fedt = fedt.getText().toString();

                //take image and compress, this is what reduces the quality
                Bitmap foto = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();

                Mad mad = new Mad();
                mad.setMMA(mma);
                mad.setHF12(hf12);
                mad.setHF3(hf3);
                mad.setHF4(hf4);
                mad.setFedt(Fedt);
                mad.setDato(datoString);
                mad.setFoto(imageInByte);

                MainActivity.myAppDatabase.myDao().addMad(mad);
                Toast.makeText(getActivity(), "Måltid tilføjet til madkassen", Toast.LENGTH_SHORT).show();

                MMA.setText("");
                HF12.setText("");
                HF3.setText("");
                HF4.setText("");
                fedt.setText("");
                Dato.setText("");
            }
        });

        BnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerFragment();
                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });



        BnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                            getContext(), Manifest.permission.CAMERA)) {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_PIC_REQUEST);

                    } else {
                        ActivityCompat.requestPermissions((Activity) getContext(),
                                new String[]{Manifest.permission.CAMERA},
                                1);
                    }

                }else{
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);
                }
            }


        });
        return view;
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
             //super.onActivityResult(requestCode, resultCode, data);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.img_imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
