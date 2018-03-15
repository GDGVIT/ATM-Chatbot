package gdg.mehakmeet.atmchatbot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;
import com.simmorsal.recolor_project.ReColor;

import gdg.mehakmeet.atmchatbot.adapter.fdAdapter;
import gdg.mehakmeet.atmchatbot.ui.RecyclerItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class floating_main extends Fragment {

    FrameLayout fl;
    RecyclerView rv;
    LinearLayout ll;
    fdAdapter cAdapter;
    RippleView ripple;

    int prev_pos=0;
    public floating_main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_floating_main, container, false);
        rv=v.findViewById(R.id.fd_rv);
        ll=v.findViewById(R.id.fd_ll);
        String[] mainList={"Home","Profile","About us","Feedback"};
        fl=v.findViewById(R.id.container_fd);
        new ReColor(getContext()).setViewBackgroundColor(ll, "#3F51B5", "3cb2e2", 400);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cAdapter=new fdAdapter(mainList);
        rv.setAdapter(cAdapter);

       rv.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
           @Override
           public void onItemClick(View view, final int position) {
              // View v2=view.inflater.inflate(R.layout.fd_items,container,false);
             //  View v2=getActivity().getLayoutInflater().inflate()
               //ripple=v2.findViewById(R.id.ripple_fd_items);

               Log.i("HM2M","WELLLL2222");

            /* ripple.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                 @Override
                 public void onComplete(RippleView rippleView) {
                     Log.i("HMM","WELLLL");
                 }
             });*/

            switch (position){
                case 0:{
                        if(!(prev_pos==position)){
                            MainActivity.mDrawer.closeMenu(true);
                            home nf=new home();
                            FragmentTransaction ft=getFragmentManager().beginTransaction();
                            ft.replace(R.id.content_lay,nf).addToBackStack(null).commit();
                        }
                        else
                            MainActivity.mDrawer.closeMenu(true);
                        prev_pos=0;
                        break;
                }
                case 3:
                {
                    if(!(prev_pos==position)) {
                        MainActivity.mDrawer.closeMenu(true);
                        feedback nf = new feedback();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_lay, nf).addToBackStack(null).commit();
                    }
                    else
                        MainActivity.mDrawer.closeMenu(true);
                }
                prev_pos=3;
                break;
            }

           }
       }));


        return v;
    }

}
