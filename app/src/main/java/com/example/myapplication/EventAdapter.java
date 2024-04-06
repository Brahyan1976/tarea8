package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events; // Cambia Event por tu clase de modelo real

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event currentEvent = events.get(position);
        holder.textViewTitle.setText(currentEvent.getTitle());
        holder.textViewDate.setText(currentEvent.getDate());
        holder.textViewDescription.setText(currentEvent.getDescription());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewDescription;

        public EventViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.eventTitleTextView);
            textViewDate = itemView.findViewById(R.id.eventDateTextView);
            textViewDescription = itemView.findViewById(R.id.eventDescriptionTextView);
        }
    }
}
