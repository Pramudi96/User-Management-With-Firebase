package com.example.user.smartbustracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    Context context;
    private String mParam1;
    private String mParam2;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button Delete;
    private  Button Up;
    DatabaseReference ref;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        /*button7 =(Button)view.findViewById(R.id.button7);*/
        button8 =(Button)view.findViewById(R.id.button8);
       /* button9 =(Button)view.findViewById(R.id.button9);*/
        button10 =(Button)view.findViewById(R.id.button10);
        Delete =(Button)view.findViewById(R.id.Delete);
        Up =(Button)view.findViewById(R.id.Up);
        final DatabaseReference[] ref = new DatabaseReference[1];

       /* button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });*/



        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getBaseContext(),"Update Driver Location",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), getDrivetCureentLocation.class);
                startActivity(intent);
            }
        });

       /* button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Toast.makeText(getActivity().getBaseContext(),"Loading 17 Root",Toast.LENGTH_LONG).show();*/
                /*Intent intent = new Intent(getActivity(), Root.class);
                startActivity(intent);
            }
        })*/

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getBaseContext(),"Loading 17 Map",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), CustomerMapsActivity.class);
                startActivity(intent);
            }
        });

        Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Update.class);
                startActivity(intent);
            }
        });



      Delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              FirebaseDatabase.getInstance().getReference("Registration").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
              final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
              if(user !=null)
                  user.delete()
                  .addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getActivity().getBaseContext(),"Your Profile is deleted",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Welcome.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getActivity().getBaseContext(),"Failed to delete Account",Toast.LENGTH_SHORT).show();
                            }
                      }
                  });

          }
      });



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
        this.context = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
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

   /* public void deleteUser(String Id) {



        DatabaseReference drDe = FirebaseDatabase.getInstance().getReference().child();
        drDe.removeValue();
    }*/
}
