package com.example.mvvmarchitectureroompractice.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmarchitectureroompractice.Entitys.Note;
import com.example.mvvmarchitectureroompractice.Interface.OnRecyclerViewItemSelectedListner;
import com.example.mvvmarchitectureroompractice.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.ViewHolder> {

 //   private List<Note> notes = new ArrayList<>();
    public  OnRecyclerViewItemSelectedListner listner;

    public NoteAdapter() {
        super(DIFF_CALLBACK);

    }
    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription())&&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.itemscontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner != null && position != RecyclerView.NO_POSITION) {
                    listner.onItemClick(getItem(position));
                }
            }
        });

    }

//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }

//    public void setNotes(List<Note> notes){
//        this.notes = notes;
//        notifyDataSetChanged();
//    }
    public Note getNoteAt(int position){
        return getItem(position);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView priority,title,description;
        private CardView itemscontainer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            priority = itemView.findViewById(R.id.tvPriority);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            itemscontainer = itemView.findViewById(R.id.CardViewListItem);



        }
    }

    public  void setOnItemClickListner(OnRecyclerViewItemSelectedListner listner){
        this.listner = listner;
    }


}
