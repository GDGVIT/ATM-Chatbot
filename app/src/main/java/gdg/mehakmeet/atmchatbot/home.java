package gdg.mehakmeet.atmchatbot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.irozon.sneaker.Sneaker;
import com.simmorsal.recolor_project.ReColor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gdg.mehakmeet.atmchatbot.API.api;
import gdg.mehakmeet.atmchatbot.adapter.Adapter;
import gdg.mehakmeet.atmchatbot.model.chat_show;
import gdg.mehakmeet.atmchatbot.model.data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
    RecyclerView rv;
    EditText message;
    Adapter cAdapter;
    ArrayList<chat_show> mainList;
    String my_message,reply;
    ImageView send;
    View v;

    ArrayList<data> detailList;
    Retrofit retrofit;
    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(v==null) {
            v = inflater.inflate(R.layout.fragment_home, container, false);
        }
        rv=v.findViewById(R.id.recycler_chat);
        send=v.findViewById(R.id.send_message);
        message=v.findViewById(R.id.et_chat);
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mainList=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cAdapter=new Adapter(getActivity(),mainList, detailList);
        rv.setAdapter(cAdapter);
        initalize_rv();
        detailList=new ArrayList<>();

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!message.getText().toString().trim().isEmpty()) {
                    sendMessage();
                }
                else{
                   /* Sneaker.with(this)
                            .setTitle("Error", R.color.white) // Title and title color
                            .setMessage("Please fill in the Text field.", R.color.white) // Message and message color
                            .setDuration(4000) // Time duration to show
                            .autoHide(true) // Auto hide Sneaker view
                            .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT) // Height of the Sneaker layout
                            .setIcon(R.drawable.ic_error, R.color.white, false) // Icon, icon tint color and circular icon view
                            .setOnSneakerDismissListener(this) // Dismiss listener for Sneaker. - Version 1.0.2
                            .sneak(R.color.blue_shade); // Sneak with background color*/
                    Sneaker.with(getActivity())
                            .setTitle("Error!")
                            .setMessage("Please fill in the text field :)")
                            .sneakError();
                }

            }
        });

        return v;
    }

    private void initalize_rv() {
        reply = "Hi, I am ATM chatbot. Feel Free to ask me any queries you have about Anime, " +
                "TV series or Movies, i'll be here to help you.\n\nSuggestions:\n" +
                " \"Suggest\" for suggestion\n \"Hi\" for My response...(._.)";
        chat_show my_data = new chat_show(reply, 1,0);
        mainList.add(my_data);
        cAdapter = new Adapter(getActivity(),mainList,detailList);
        rv.setAdapter(cAdapter);
        rv.scrollToPosition(cAdapter.getItemCount() - 1);

    }

    private void sendMessage() {
        new ReColor(getContext()).setStatusBarColor("3F51B5", "3cb2e2", 800);
        my_message = message.getText().toString();
        if (my_message.toLowerCase().trim().compareTo("suggest") == 0) {
            Log.i("WUT","YESA");
            reply = "Here are some Suggestions";
            chat_show my_data = new chat_show(my_message, 0,0);
            mainList.add(my_data);
            cAdapter.notifyItemInserted(mainList.size()-1);
            getAnime();



        }
        else if(my_message.toLowerCase().compareTo("hi") == 0) {
            reply = "Hello!";
            chat_show my_data = new chat_show(my_message, 0,0);
            mainList.add(my_data);
            cAdapter.notifyItemInserted(mainList.size() - 1);
            rv.scrollToPosition(cAdapter.getItemCount() - 1);
            chat_show reply_data = new chat_show(reply, 1,0);
            mainList.add(reply_data);
            cAdapter.notifyItemInserted(mainList.size()-1);

            rv.scrollToPosition(cAdapter.getItemCount() - 1);


        }
        else {
            chat_show my_data = new chat_show(my_message, 0,0);
            mainList.add(my_data);
            cAdapter.notifyItemInserted(mainList.size()-1);

            rv.scrollToPosition(cAdapter.getItemCount() - 1);
        }
        message.setText("");

    }

    private void getAnime() {
        Log.i("ddd","hmm");
        retrofit=new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api api_create=retrofit.create(api.class);
        Call<JsonObject> call = api_create.getData();

       call.enqueue(new Callback<JsonObject>() {
           @Override
           public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
               Log.i("RESPONSE", String.valueOf(response.body()));
               try {
                   JSONObject jobj=new JSONObject(response.body().toString());
                   String title_name=jobj.getString("title");
                   Log.i("NAME",title_name);
                   String synop=jobj.getString("synopsis");
                   String image=jobj.getString("image");
                   data new_data=new data();
                   new_data.setTitle(title_name);
                   new_data.setImage(image);
                   new_data.setSynopsis(synop);
                   detailList=new ArrayList<>();
                   detailList.add(new_data);
                   rv.scrollToPosition(cAdapter.getItemCount() - 1);
                   chat_show reply_data = new chat_show(reply, 1,1);
                   mainList.add(reply_data);
                   cAdapter.notifyItemInserted(mainList.size()-1);
                   cAdapter=new Adapter(getActivity(),mainList,detailList);
                   //cAdapter.notifyItemInserted(mainList.size()-1);
                   rv.setAdapter(cAdapter);

                   rv.scrollToPosition(cAdapter.getItemCount() - 1);

               } catch (JSONException e) {
                   e.printStackTrace();
               }


           }

           @Override
           public void onFailure(Call<JsonObject> call, Throwable t) {

           }
       });
    }

}
