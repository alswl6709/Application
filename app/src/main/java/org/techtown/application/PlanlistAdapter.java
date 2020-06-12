package org.techtown.application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanlistAdapter extends RecyclerView.Adapter<PlanlistAdapter.ViewHolder>{

    ArrayList<Planlist> items = new ArrayList<Planlist>();
    OnPlanlistItemClickListener listener;

    public void addItem(Planlist item){
        items.add(item);
    }

    public void setItems (ArrayList<Planlist> items){
        this.items = items;
    }

    public void setOnItemClickListener(OnPlanlistItemClickListener listener){
        this.listener = listener; //리스너 할당
    }

    public Planlist getItem(int position){
        return items.get(position);
    }
    public void setItem(int position , Planlist item){
        items.set(position, item);
    }



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView place; //지역
        TextView date;  //날짜

        public ViewHolder(View itemView,final OnPlanlistItemClickListener listener){
            super(itemView);

            place = itemView.findViewById(R.id.place);
            date = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); //몇번째 뷰인지

                    //뷰클릭했을 떄 리스너에 정보전달
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,v,position);
                    }
                }
            });
        }
        //Planlist.java 데이터 뷰에 설정
        public void setItem(Planlist item){
            place.setText(item.getPlace());
            date.setText(item.getYear()+"/"+item.getMonth()+"/"+item.getDay());

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.planlist_item,parent,false);

        return new ViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planlist item = items.get(position); //인덱스값
        holder.setItem(item); //뷰홀더 새로 만드는것 아님
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
