package com.irfankhoirul.apps.androidsensor.adapter;

import android.content.Context;
import android.hardware.Sensor;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.irfankhoirul.apps.androidsensor.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Irfan Khoirul on 06/05/2016.
 */
public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {

    private List<Sensor> sensorList;
    private Context context;
    private HashMap<Integer, String> sensorTypes;

    public SensorAdapter(List<Sensor> sensorList, Context context) {
        this.sensorList = sensorList;
        this.context = context;
        sensorTypes = new HashMap<>();{
            sensorTypes.put(Sensor.TYPE_ACCELEROMETER, "TYPE_ACCELEROMETER");
            sensorTypes.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "TYPE_AMBIENT_TEMPERATURE");
            sensorTypes.put(Sensor.TYPE_GRAVITY, "TYPE_GRAVITY");
            sensorTypes.put(Sensor.TYPE_GYROSCOPE, "TYPE_GYROSCOPE");
            sensorTypes.put(Sensor.TYPE_LIGHT, "TYPE_LIGHT");
            sensorTypes.put(Sensor.TYPE_LINEAR_ACCELERATION, "TYPE_LINEAR_ACCELERATION");
            sensorTypes.put(Sensor.TYPE_MAGNETIC_FIELD, "TYPE_MAGNETIC_FIELD");
            sensorTypes.put(Sensor.TYPE_ORIENTATION, "TYPE_ORIENTATION (deprecated)");
            sensorTypes.put(Sensor.TYPE_PRESSURE, "TYPE_PRESSURE");
            sensorTypes.put(Sensor.TYPE_PROXIMITY, "TYPE_PROXIMITY");
            sensorTypes.put(Sensor.TYPE_RELATIVE_HUMIDITY, "TYPE_RELATIVE_HUMIDITY");
            sensorTypes.put(Sensor.TYPE_ROTATION_VECTOR, "TYPE_ROTATION_VECTOR");
            sensorTypes.put(Sensor.TYPE_TEMPERATURE, "TYPE_TEMPERATURE (deprecated)");
        }
    }


    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sensor, parent, false);

        return new SensorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SensorViewHolder holder, int position) {
        final Sensor sensor = sensorList.get(position);

        if (position % 2 == 0) {
            holder.llContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.amber_50));
        } else {
            holder.llContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.amber_100));
        }
        holder.tvName.append(sensor.getName());
        holder.tvVendor.append(sensor.getVendor());
        holder.tvVersion.append(String.valueOf(sensor.getVersion()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            holder.tvType.append(sensor.getStringType());
        } else {
            holder.tvType.append(sensorTypes.get(sensor.getType()));
        }
        holder.tvMaxRange.append(String.valueOf(sensor.getMaximumRange()));
        holder.tvResolution.append(String.valueOf(sensor.getResolution()));
        holder.tvPower.append(String.valueOf(sensor.getPower()));
        holder.tvMinDelay.append(String.valueOf(sensor.getMinDelay()));

    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    public class SensorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llContainer)
        LinearLayout llContainer;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvVendor)
        TextView tvVendor;
        @BindView(R.id.tvVersion)
        TextView tvVersion;
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.tvMaxRange)
        TextView tvMaxRange;
        @BindView(R.id.tvResolution)
        TextView tvResolution;
        @BindView(R.id.tvPower)
        TextView tvPower;
        @BindView(R.id.tvMinDelay)
        TextView tvMinDelay;


        public SensorViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
