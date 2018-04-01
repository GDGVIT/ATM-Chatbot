package gdg.mehakmeet.atmchatbot.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gdg.mehakmeet.atmchatbot.R;
import gdg.mehakmeet.atmchatbot.detail_page;
import gdg.mehakmeet.atmchatbot.floating_main;
import gdg.mehakmeet.atmchatbot.model.chat_show;
import gdg.mehakmeet.atmchatbot.model.data;
import gdg.mehakmeet.atmchatbot.ui.RecyclerItemClickListener;

/**
 * Created by MEHAKMEET on 12-03-2018.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<chat_show> mainList;
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private Activity activity;
    private final ArrayList<data> detailList;

    public Adapter(Activity activity, ArrayList<chat_show> mainList, ArrayList<data> detailList) {
        this.mainList=mainList;
        this.activity=activity;
        this.detailList=detailList;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==TYPE_ONE){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_user,parent,false);
            return new MyChatViewHolder(view);
        }else if(viewType==TYPE_TWO){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_other,parent,false);
            return new OtherChatViewHolder(view);
        }else{
            throw new RuntimeException("The type has to be ONE or TWO");
        }
        /*LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (who) {
            case 0:
                View viewChatMine = layoutInflater.inflate(R.layout.chat_item_user, parent, false);
                viewHolder = new MyChatViewHolder(viewChatMine);
                break;
            case 1:
                View viewChatOther = layoutInflater.inflate(R.layout.chat_item_other, parent, false);
                viewHolder = new OtherChatViewHolder(viewChatOther);
                break;
        }
        return viewHolder;
    */}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch(holder.getItemViewType()){
            case TYPE_ONE:
                configureMyChatViewHolder((MyChatViewHolder)holder,position);
                break;
            case TYPE_TWO:
                configureOtherChatViewHolder((OtherChatViewHolder)holder,position);
                break;
            default:
                break;
        }
        /*if(who==0){
            configureMyChatViewHolder((MyChatViewHolder) holder, position);
        }
        else
            configureOtherChatViewHolder((OtherChatViewHolder) holder, position);
*/
    }
    private void configureOtherChatViewHolder(OtherChatViewHolder holder, int position) {
        chat_show data=mainList.get(position);
        holder.my_message.setText(data.getMessage());

        if(data.getRv2()==1){

            suggestAdapter sa=new suggestAdapter(detailList);
            holder.rv_suggest.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));
            holder.rv_suggest.setAdapter(sa);

        }
        holder.rv_suggest.addOnItemTouchListener(new RecyclerItemClickListener(activity.getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // detail_page dp=new detail_page();
               // FragmentTransaction ft=activity.getFragmentManager();
                Toast.makeText(activity.getApplicationContext(),detailList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                detail_page dp=new detail_page(detailList);
                floating_main.prev_pos=-2;
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                        .add(R.id.content_lay, dp)
                        .addToBackStack(null)
                        .commit();

            }
        }));
    }

    private void configureMyChatViewHolder(MyChatViewHolder holder, int position) {
        chat_show data=mainList.get(position);

        holder.my_message.setText(data.getMessage());

    }
    @Override
    public int getItemCount() {
        return mainList.size();
    }


    static class MyChatViewHolder extends RecyclerView.ViewHolder {
        TextView my_message;
        public MyChatViewHolder(View itemView) {
            super(itemView);
            my_message=(TextView)itemView.findViewById(R.id.my_message);
        }
    }
    static class OtherChatViewHolder extends RecyclerView.ViewHolder {
        TextView my_message;
        RecyclerView rv_suggest;
        OtherChatViewHolder(View itemView) {
            super(itemView);
            my_message=itemView.findViewById(R.id.my_message);

            rv_suggest=itemView.findViewById(R.id.rv_suggestion);
        }
    }
    @Override
    public int getItemViewType(int position) {
        chat_show item=mainList.get(position);
        if(item.getType()==TYPE_ONE){
            return TYPE_ONE;
        }
        else if(item.getType()==TYPE_TWO){
            return TYPE_TWO;
        }
        else {
            return -1;
        }
    }

}
