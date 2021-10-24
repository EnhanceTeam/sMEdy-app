package com.example.smedy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.smedy.R;
import com.example.smedy.model.Appointment;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.CardViewViewHolder> {

    private ArrayList<Appointment> appointmentList;
    private Context context;

    public AppointmentAdapter(ArrayList<Appointment> appointmentList, Context context) {
        this.appointmentList = appointmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_appointment, parent, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        String date = appointment.getJam() + ", " + appointment.getTanggal();
        holder.txtNameAppointmentCard.setText(appointment.getNamaPsikolog());
        holder.txtSpecialistAppointmentCard.setText(appointment.getSpesialis());
        holder.txtDateAppointmentCard.setText(date);
        Glide.with(context).load(appointment.getFoto()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ImgFotoAppointment);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameAppointmentCard, txtSpecialistAppointmentCard, txtDateAppointmentCard, txtStatusIndicatorAppointmentCard, txtStatusAppointmentCard, txtDescriptionAppointmentCard;
//        CardView cvAppointment;
        ImageView ImgFotoAppointment;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameAppointmentCard = itemView.findViewById(R.id.txtNameAppointmentCard);
            txtSpecialistAppointmentCard = itemView.findViewById(R.id.txtSpecialistAppointmentCard);
            txtDateAppointmentCard = itemView.findViewById(R.id.txtDateAppointmentCard);
            ImgFotoAppointment = itemView.findViewById(R.id.ImgFotoAppointment);
//            txtStatusIndicatorAppointmentCard = itemView.findViewById(R.id.txtStatusIndicatorAppointmentCard);
//            txtStatusAppointmentCard = itemView.findViewById(R.id.txtStatusAppointmentCard);
//            txtDescriptionAppointmentCard = itemView.findViewById(R.id.txtDescriptionAppointmentCard);
        }
    }
}
