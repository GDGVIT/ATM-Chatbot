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

import com.irozon.sneaker.Sneaker;
import com.simmorsal.recolor_project.ReColor;

import java.util.ArrayList;

import gdg.mehakmeet.atmchatbot.adapter.Adapter;
import gdg.mehakmeet.atmchatbot.model.chat_show;


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

    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        rv=v.findViewById(R.id.recycler_chat);
        send=v.findViewById(R.id.send_message);
        message=v.findViewById(R.id.et_chat);
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mainList=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cAdapter=new Adapter(getActivity(),mainList);
        rv.setAdapter(cAdapter);
        initalize_rv();

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
        cAdapter = new Adapter(getActivity(),mainList);
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

            cAdapter.notifyItemInserted(mainList.size() - 1);
            rv.scrollToPosition(cAdapter.getItemCount() - 1);
            chat_show reply_data = new chat_show(reply, 1,1);
            mainList.add(reply_data);
            cAdapter.notifyItemInserted(mainList.size()-1);

            rv.scrollToPosition(cAdapter.getItemCount() - 1);


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

}
