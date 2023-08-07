package io.github.handharbeni.erpas.ui.bt;

import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.github.handharbeni.erpas.adapters.BluetoothDevicesAdapter;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentBluetoothBinding;
import io.github.handharbeni.erpas.events.BluetoothEvent;
import io.github.handharbeni.erpas.utils.Constant;
import io.github.handharbeni.erpas.utils.UtilPermission;

public class BluetoothFragment extends BaseFragment implements BluetoothDevicesAdapter.DeviceCallback {
    private final String TAG = BluetoothFragment.class.getSimpleName();
    BluetoothDevicesAdapter bluetoothDevicesAdapter;
    private FragmentBluetoothBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentBluetoothBinding.inflate(inflater, container, false);

        navController = NavHostFragment.findNavController(this);

        initAdapter();

        sendRequest();

        observe(Constant.BLUETOOTH_CONNECTED_STRING, (Observer<BluetoothStatus>) bluetoothStatus -> {
            if (bluetoothStatus == BluetoothStatus.NONE) {
                bluetoothDevicesAdapter.refreshData();
            }
        });

        binding.refreshDevice.setOnRefreshListener(this::sendRequest);

        requireActivity().runOnUiThread(() -> {
            binding.refreshDevice.setRefreshing(true);
            sendRequest();
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void updateDevice(List<BluetoothDevice> listDevice) {
        try {
            bluetoothDevicesAdapter.updateData(listDevice);
            binding.refreshDevice.setRefreshing(false);
        } catch (Exception ignored) {
        }
    }

    void initAdapter() {
        bluetoothDevicesAdapter = new BluetoothDevicesAdapter(
                requireContext(),
                BluetoothFragment.this,
                new ArrayList<>(),
                this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.rvDevice.setLayoutManager(linearLayoutManager);
        binding.rvDevice.setAdapter(bluetoothDevicesAdapter);
    }

    @Override
    public void onDeviceClick(BluetoothDevice device) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!UtilPermission.checkPermission(requireContext())) {
                setState(Constant.REQUEST_PERMISSION, BluetoothFragment.this);
            }
        }
        setState(Constant.BLUETOOTH_CONNECT_REQUEST, device);
    }

    void sendRequest() {
        setState(Constant.BLUETOOTH_SCAN_REQUEST, BluetoothFragment.class.getSimpleName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onConnect(BluetoothEvent bluetoothEvent) {
//        if (bluetoothEvent.message == BluetoothEvent.BTEvent.BLUETOOTH_CONNECTED) {
//            navController.navigateUp();
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}