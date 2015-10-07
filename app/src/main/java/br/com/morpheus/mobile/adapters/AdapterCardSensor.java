package br.com.morpheus.mobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.morpheus.mobile.R;
import br.com.morpheus.mobile.model.Sensor;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pedronobre on 06/10/2015.
 */
public class AdapterCardSensor extends RecyclerView.Adapter<AdapterCardSensor.ContactViewHolder> {
    Context context;
    ArrayList<Sensor> sensors;

    public AdapterCardSensor(Context context, ArrayList<Sensor> contas) {
        this.context = context;
        this.sensors = contas;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Sensor sensor = sensors.get(position);

        holder.vName.setText(sensor.getNome());

        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        myBitmap = BitmapFactory.decodeResource(context.getResources(), sensor.getIdOfResource(), options);
        myBitmap = Bitmap.createScaledBitmap(myBitmap, 200, 200, false);
        holder.imageView.setImageBitmap(myBitmap);

        if (myBitmap.isRecycled()) {
            myBitmap.recycle();
        }


    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected ImageView imageView;

        public ContactViewHolder(View v) {
            super(v);

            vName =  (TextView) v.findViewById(R.id.text_view_item);
            imageView = (ImageView)v.findViewById(R.id.icon_health);

        }
    }


}
