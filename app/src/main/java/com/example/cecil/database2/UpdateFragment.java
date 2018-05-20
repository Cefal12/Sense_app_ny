package com.example.cecil.database2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UpdateFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private EditText HF12, HF3, HF4, Fedt;
    private TextView Date;
    private Button BnUpdate, BnReadDate, BnDate, BnTilbageUp;
    private ImageView ImgUp;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        Date = view.findViewById(R.id.date);
        HF12 = view.findViewById(R.id.txt_HF12);
        HF3 = view.findViewById(R.id.txt_HF3);
        HF4 = view.findViewById(R.id.txt_HF4);
        Fedt = view.findViewById(R.id.txt_fedt);
        BnUpdate = view.findViewById(R.id.bn_update_mad);
        BnReadDate = view.findViewById(R.id.bn_read_date);
        BnDate = view.findViewById(R.id.bn_date);
        ImgUp = view.findViewById(R.id.imgUp);


        HF12.setVisibility(View.INVISIBLE);
        HF3.setVisibility(View.INVISIBLE);
        HF4.setVisibility(View.INVISIBLE);
        Fedt.setVisibility(View.INVISIBLE);
        ImgUp.setVisibility(View.INVISIBLE);


        BnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        BnReadDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dato = Date.getText().toString();

                Mad mad = new Mad();

                //TODO: check if date data has been entered. This works because the user can only change it fom "" by selecting a date
                if(MainActivity.myAppDatabase.myDao().getMadFromDate(dato)== null) {
                    Toast.makeText(getActivity(), "Vælg udfyldt dato", Toast.LENGTH_SHORT).show();
                    return;
                }

                HF12.setVisibility(View.VISIBLE);
                HF3.setVisibility(View.VISIBLE);
                HF4.setVisibility(View.VISIBLE);
                Fedt.setVisibility(View.VISIBLE);

                mad = MainActivity.myAppDatabase.myDao().getMadFromDate(dato);

                HF12.setText(mad.getHF12());
                HF3.setText(mad.getHF3());
                HF4.setText(mad.getHF4());
                Fedt.setText(mad.getFedt());

                Toast.makeText(getActivity(),"Loaded!", Toast.LENGTH_SHORT).show();
            }
        });

        BnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dato = Date.getText().toString();


                Mad mad = new Mad();

                if(MainActivity.myAppDatabase.myDao().getMadFromDate(dato)== null){
                    Toast.makeText(getActivity(), "Vælg udfyldt dato /n Indlæs før du gemmer", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Bitmap foto = ((BitmapDrawable) ImgUp.getDrawable()).getBitmap();
                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //foto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                //byte[] imageInByte = baos.toByteArray();


                //set the id of the object were saving, to the id from the object we loaded
                mad.setId(MainActivity.myAppDatabase.myDao().getMadFromDate(Date.getText().toString()).getId());
               // mad.setFoto(MainActivity.myAppDatabase.myDao().getMadFromFoto(;
                mad.setHF12(HF12.getText().toString());
                mad.setHF3(HF3.getText().toString());
                mad.setHF4(HF4.getText().toString());
                mad.setFedt(Fedt.getText().toString());
                mad.setDato(Date.getText().toString());

                MainActivity.myAppDatabase.myDao().updateMad(mad);
                Toast.makeText(getActivity(),"Opdateret!", Toast.LENGTH_SHORT).show();

                Date.setText("");
                HF12.setText("");
                HF3.setText("");
                HF4.setText("");
                Fedt.setText("");
            }
        });

        BnTilbageUp = view.findViewById(R.id.bn_tilbage_update);

        BnTilbageUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).
                        addToBackStack(null).commit();
            }});




        return view;
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
