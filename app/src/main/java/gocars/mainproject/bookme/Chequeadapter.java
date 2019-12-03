package gocars.mainproject.bookme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder> {

    private Context mCtx;
    private List<Cheque> productList;

    public Chequeadapter(Context mCtx, List<Cheque> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.adapterfor_c, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
      final   Cheque cheque;   cheque = productList.get(position);

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
        i.putExtra("amount",cheque.getPrize());
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



}