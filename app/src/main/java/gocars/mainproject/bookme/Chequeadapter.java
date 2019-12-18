package gocars.mainproject.bookme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder>  {

    private Context mCtx;
    private ArrayList<Cheque> productList;


    public Chequeadapter(Context mCtx, ArrayList<Cheque> pproductList) {
        this.mCtx = mCtx;
        productList=pproductList;

    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.adapterfor_c, null);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
      final   Cheque cheque;
      cheque = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(cheque.getImage())
                .into(holder.imageView);
        holder.text.setText(cheque.getPrize());
holder.txtt.setText(cheque.getStatus().toUpperCase());
holder.ts.setText("\u20B9"+cheque.getUser());
holder.tt.setText(cheque
.getPid());
holder.cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(
                mCtx,Pay.class
        );
        i.putExtra("amount",cheque.getUser());
        i.putExtra("bname",cheque.getStatus());
        mCtx.startActivity(i);
    }

});
      //  SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


//    public void filterList(ArrayList<Cheque> filteredList) {
//        productList = filteredList;
//        notifyDataSetChanged();
//    }



    class ProductViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView text,txtt,ts,tt;
Button cart;
        public ProductViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView21);
            text=itemView.findViewById(R.id.textView21);
            txtt=itemView.findViewById(R.id.restname1);
            ts=itemView.findViewById(R.id.mm1);
            cart=itemView.findViewById(R.id.button);
            tt=itemView.findViewById(R.id.textView2);
        }

    }
    public void filterList(ArrayList<Cheque> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

    }
