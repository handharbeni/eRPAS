package io.github.handharbeni.erpas.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import io.github.handharbeni.erpas.R;
import io.github.handharbeni.erpas.databinding.BluetoothItemBinding;
import io.github.handharbeni.erpas.utils.Constant;
import io.github.handharbeni.erpas.utils.UtilPermission;

public class BluetoothDevicesAdapter extends RecyclerView.Adapter<BluetoothDevicesAdapter.ViewHolder> {
    Context context;
    Fragment fragment;
    List<BluetoothDevice> listDevice;
    DeviceCallback deviceCallback;

    public BluetoothDevicesAdapter() {
    }

    public BluetoothDevicesAdapter(Context context, Fragment fragment, List<BluetoothDevice> listDevice, DeviceCallback deviceCallback) {
        this.context = context;
        this.fragment = fragment;
        this.listDevice = listDevice;
        this.deviceCallback = deviceCallback;
    }

    public BluetoothDevicesAdapter(Context context, List<BluetoothDevice> listDevice, DeviceCallback deviceCallback) {
        this.context = context;
        this.listDevice = listDevice;
        this.deviceCallback = deviceCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BluetoothItemBinding binding = BluetoothItemBinding.inflate(layoutInflater);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        binding.getRoot().setLayoutParams(lp);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            BluetoothDevice bluetoothDevice = listDevice.get(position);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (!UtilPermission.checkPermission(context)) {
                    Objects
                            .requireNonNull(
                                    NavHostFragment
                                            .findNavController(this.fragment)
                                            .getCurrentBackStackEntry()
                            )
                            .getSavedStateHandle()
                            .set(Constant.REQUEST_PERMISSION, this.fragment);
                }
            }
            holder.binding.btName.setText(bluetoothDevice.getName());
            holder.binding.btAddress.setText(bluetoothDevice.getAddress());
            holder.itemView.setOnClickListener(view -> {
                holder.binding.btAddress.setText(R.string.label_connecting);
                deviceCallback.onDeviceClick(bluetoothDevice);
            });
        } catch (Exception ignored) {}
    }

    @Override
    public int getItemCount() {
        return listDevice.size();
    }

    public void updateData(List<BluetoothDevice> listDevice) {
        try {
            this.listDevice = listDevice;
            notifyDataSetChanged();
        } catch (Exception ignored) {}
    }

    public void refreshData() {
        try {
            notifyDataSetChanged();
        } catch (Exception ignored) {}
    }

    public interface DeviceCallback {
        void onDeviceClick(BluetoothDevice device);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        BluetoothItemBinding binding;

        public ViewHolder(@NonNull BluetoothItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
