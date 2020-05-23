package com.android.letsgetplaced;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.letsgetplaced.Company;
import com.android.letsgetplaced.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>
{
    private Context mCtx;
     private ArrayList<Company> companylist;
     private ItemClickListener clicklistener;
     private ItemLongClickListener longClickListener;

    public CompanyAdapter(Context mCtx,ArrayList<Company> companylist)
    {
        this.mCtx=mCtx;
        this.companylist=companylist;
    }

    @NonNull
    @Override
    public CompanyAdapter.CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CompanyViewHolder( LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_company, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.CompanyViewHolder holder, int position) {

        Company company = companylist.get(position);
        holder.tvCompanyName.setText(company.getName());
        holder.tvDate2.setText(company.getDate());



    }

    @Override
    public int getItemCount() {
        return companylist.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clicklistener = itemClickListener;
    }

    public void setItemLongClickListener(ItemLongClickListener ic)
    {
        this.longClickListener=ic;
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        TextView  tvCompanyName,tvDate1,tvDate2;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCompanyName=itemView.findViewById(R.id.tvCompanyName);
            tvDate1=itemView.findViewById(R.id.tvDate1);
            tvDate2=itemView.findViewById(R.id.tvDate2);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(mCtx, "HI", Toast.LENGTH_SHORT).show();
            if (clicklistener != null) clicklistener.onClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            longClickListener.onItemLongClick(v,getAdapterPosition());
              return false;
        }
    }
    }

