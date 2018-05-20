package com.example.cecil.database2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button BnAddMad, BnReadMad, BnUpdate, BnLogud, BnInfo;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        BnAddMad = view.findViewById(R.id.bn_add_mad);
        BnAddMad.setOnClickListener(this);

        BnReadMad = view.findViewById(R.id.bn_view_mad);
        BnReadMad.setOnClickListener(this);

        BnUpdate = view.findViewById(R.id.bn_update_mad);
        BnUpdate.setOnClickListener(this);

        BnLogud = view.findViewById(R.id.bn_logud);
        BnLogud.setOnClickListener(this);

        BnInfo = (Button) view.findViewById(R.id.bn_info);
        BnInfo.setOnClickListener(this);
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

    //Afhængig af hvilken knap man klikker på, kommer man ind på den tilsvarende fragment
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bn_add_mad:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddMadFragment()).
                        addToBackStack(null).commit();
                break;

            case R.id.bn_view_mad:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new ReadMadFragment()).
                        addToBackStack(null).commit();
                break;

            case R.id.bn_update_mad:
                //check if date has been entered. This works because the user can only change it fom "" by selecting a date
                //Mad mad = new Mad();
               /* if(MainActivity.myAppDatabase.myDao().getMad().isEmpty()){
                    Toast.makeText(getActivity(), "Husk at tilføje dato", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).
                        addToBackStack(null).commit();
                break;

            case R.id.bn_logud:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new ForsideFragment()).
                        addToBackStack(null).commit();
                break;

            case R.id.bn_info:
                Intent intent = new Intent(getActivity(), Info.class);
                startActivity(intent);
                break;

        }

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
