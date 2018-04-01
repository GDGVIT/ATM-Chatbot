package gdg.mehakmeet.atmchatbot.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gdg.mehakmeet.atmchatbot.R;
import gdg.mehakmeet.atmchatbot.model.data;

/**
 * Created by MEHAKMEET on 12-03-2018.
 */

public class suggestAdapter extends RecyclerView.Adapter<suggestAdapter.ViewHolder> {
    private final ArrayList<data> detailList;

    suggestAdapter(ArrayList<data> detailList) {
        this.detailList=detailList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suggestion_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.item_name.setText(detailList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(detailList==null){
            return 0;
        }
        return detailList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        ViewHolder(View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.suggest_txt);
        }
    }
}
