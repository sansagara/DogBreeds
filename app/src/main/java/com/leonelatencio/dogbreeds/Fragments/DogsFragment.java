package com.leonelatencio.dogbreeds.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.leonelatencio.dogbreeds.API.ApiClient;
import com.leonelatencio.dogbreeds.Adapters.DogAdapter;
import com.leonelatencio.dogbreeds.DogDetail;
import com.leonelatencio.dogbreeds.Model.Dog;
import com.leonelatencio.dogbreeds.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DogsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private List<String> dogs;
    private GridView listView;
    private DogAdapter adapter;

    public DogsFragment() {
        // Required empty public constructor
    }


    public static DogsFragment newInstance() {
        DogsFragment fragment = new DogsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dogs, container, false);
        listView = (GridView) view.findViewById(R.id.listView);
        if (savedInstanceState == null) {
            getDogs();
        } else {
            newInstance();
        }


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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



    private void getDogs() {
        Call<com.leonelatencio.dogbreeds.Model.Response> call;
        final ProgressDialog loading = ProgressDialog.show(getActivity(),getContext().getString(R.string.loading_title),getContext().getString(R.string.loading_please_wait),false,false);
        call = ApiClient.get().getDogs();

        call.enqueue(new Callback<com.leonelatencio.dogbreeds.Model.Response>() {
            @Override
            public void onFailure(Call<com.leonelatencio.dogbreeds.Model.Response> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), getContext().getString(R.string.error_loading_data_failure), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call<com.leonelatencio.dogbreeds.Model.Response> call, Response<com.leonelatencio.dogbreeds.Model.Response> response) {
                loading.dismiss();
                Log.d("API", response.message());
                if (response.message() != null) {
                    dogs = response.body().getMessage();
                    if (dogs.size() > 0) {
                        listView.setEnabled(true);
                        showList();
                    } else {
                        listView.setEnabled(false);
                        Toast.makeText(getContext(), getContext().getString(R.string.no_match), Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getContext(), getContext().getString(R.string.error_loading_data), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    //Our method to show list
    private void showList() {
        adapter = new DogAdapter(getActivity(), dogs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dogBreed = (String) parent.getItemAtPosition(position);
                Intent detail = new Intent(getContext(), DogDetail.class);
                detail.putExtra("breed", dogBreed);
                startActivityForResult(detail, 1);
            }});
    }


}
