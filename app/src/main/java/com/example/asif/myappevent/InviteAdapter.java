package com.example.asif.myappevent;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by asif on 10/5/17.
 */

public class InviteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Students> list;
    private Context context;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public InviteAdapter(Context context,List<Students> list) {
        this.list = list;
        this.context = context;
    }

    public class sViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView,textView1,textView2;
        private ImageButton add;
        private CardView cv;
        private Button inviteAll;
        public sViewHolder(View itemView)
        {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.textView);
            textView1=(TextView) itemView.findViewById(R.id.textView3);
            textView2=(TextView) itemView.findViewById(R.id.textView4);
            add= (ImageButton) itemView.findViewById(R.id.addPerson1);
            cv= (CardView) itemView.findViewById(R.id.cvSearch);
        }
    }

    @Override
    public sViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_search,parent,false);
        return new sViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Students temp01 = list.get(position);
        ((sViewHolder)holder).textView.setText(temp01.getName());
        ((sViewHolder)holder).textView1.setText(temp01.getYear()+" Year");
        ((sViewHolder)holder).textView2.setText(temp01.getDept());

        ((sViewHolder)holder). add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetails temp = new EventDetails();
                //EventDetails newTemp = new EventDetails(temp.getEventName(),temp.getEventDate(),temp.getEventTime(),temp.getEventDescription(),temp.getEventID(),temp.getOpernerID());
                temp.eventName = EventDetails.tName;
                temp.eventDate = EventDetails.tDate;
                temp.eventTime = EventDetails.tTime;
                temp.eventDescription = EventDetails.tDescription;
                temp.eventID = EventDetails.tEventID;
                temp.openerID = EventDetails.tOpenerID;
                temp.eventLoc = EventDetails.tLoc;

                mDatabase = FirebaseDatabase.getInstance().getReference().child("Invitation_Events");
                mDatabase.child(temp01.getUid()).child(temp.eventID).setValue(temp);
                Toast.makeText(context,"DONE",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
