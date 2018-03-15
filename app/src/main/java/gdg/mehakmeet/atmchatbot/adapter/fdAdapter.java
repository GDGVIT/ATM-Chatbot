package gdg.mehakmeet.atmchatbot.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import gdg.mehakmeet.atmchatbot.R;

/*
 * Created by MEHAKMEET on 12-03-2018.
 */

public class fdAdapter extends RecyclerView.Adapter<fdAdapter.MyViewHolder> {

    private String[] myList;

    private int[] imgs=new int[]{R.drawable.ai_full_white,R.drawable.prof,R.drawable.info_ic,R.drawable.fb_ai};

    public fdAdapter(String[] myList) {
        this.myList = myList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fd_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.cat_items.setText(myList[position]);
        holder.fd_imgs.setImageResource(imgs[position]);

    }

    @Override
    public int getItemCount() {
        if (myList == null) {
            return 0;
        } else
            return myList.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView fd_imgs;
        TextView cat_items;
        MyViewHolder(View itemView) {
            super(itemView);

            cat_items = itemView.findViewById(R.id.item_txt);
            fd_imgs= itemView.findViewById(R.id.fd_item_img);
        }
    }
}
    /*public void setFilter(ArrayList<data_food> newlist)
    {
        myList=new ArrayList<>();
        myList.addAll(newlist);
        notifyDataSetChanged();
    }*/

