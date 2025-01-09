package com.example.seleniumdemo;

import com.sun.jna.*;
import com.sun.jna.ptr.*;
import com.sun.jna.win32.*;
import java.util.*;

public class WifiScanner {

    public interface WlanAPI extends StdCallLibrary {
        WlanAPI INSTANCE = Native.load("Wlanapi", WlanAPI.class);

        int WlanOpenHandle(int dwClientVersion, PointerByReference pReserved, IntByReference pdwNegotiatedVersion, PointerByReference phClientHandle);

        int WlanCloseHandle(Pointer hClientHandle, Pointer pReserved);

        int WlanEnumInterfaces(Pointer hClientHandle, Pointer pReserved, PointerByReference ppInterfaceList);

        int WlanScan(Pointer hClientHandle, Guid.GUID pInterfaceGuid, Pointer pDot11Ssid, Pointer pIeData, Pointer pReserved);

        int WlanGetAvailableNetworkList(Pointer hClientHandle, Guid.GUID pInterfaceGuid, int dwFlags, Pointer pReserved, PointerByReference ppAvailableNetworkList);
    }

    public static class Guid extends Structure {
        public byte[] Data = new byte[16];

        @Override
        protected List<String> getFieldOrder() {
            return Collections.singletonList("Data");
        }

        public static class GUID extends Guid implements Structure.ByReference {}
    }

    public static void main(String[] args) {
        try {
            IntByReference negotiatedVersion = new IntByReference();
            PointerByReference clientHandle = new PointerByReference();
            int result = WlanAPI.INSTANCE.WlanOpenHandle(2, null, negotiatedVersion, clientHandle);

            if (result != 0) {
                System.err.println("Failed to open WLAN handle. Error code: " + result);
                return;
            }

            Pointer hClientHandle = clientHandle.getValue();

            // Enumerate interfaces
            PointerByReference ppInterfaceList = new PointerByReference();
            result = WlanAPI.INSTANCE.WlanEnumInterfaces(hClientHandle, null, ppInterfaceList);

            if (result != 0) {
                System.err.println("Failed to enumerate interfaces. Error code: " + result);
                return;
            }

            // Process interface list
            WlanInterfaceInfoList interfaceList = new WlanInterfaceInfoList(ppInterfaceList.getValue());
            for (WlanInterfaceInfo info : interfaceList.getInterfaces()) {
                System.out.println("Found interface: " + info.getDescription());

                // Initiate scan
                result = WlanAPI.INSTANCE.WlanScan(hClientHandle, info.InterfaceGuid, null, null, null);

                if (result == 0) {
                    System.out.println("Scan initiated successfully for interface: " + info.getDescription());
                } else {
                    System.err.println("Failed to initiate scan. Error code: " + result);
                }
            }

            // Close the WLAN handle
            WlanAPI.INSTANCE.WlanCloseHandle(hClientHandle, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Structures for WLAN interface list
    public static class WlanInterfaceInfo extends Structure {
        public Guid.GUID InterfaceGuid;
        public byte[] strInterfaceDescription = new byte[256];
        public int isState;
        public WlanInterfaceInfo[] InterfaceInfo;
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("InterfaceGuid", "strInterfaceDescription", "isState");
        }

        public String getDescription() {
            return Native.toString(strInterfaceDescription);
        }
    }

    public static class WlanInterfaceInfoList extends Structure {
        public int dwNumberOfItems;
        public int dwIndex;
        public WlanInterfaceInfo[] InterfaceInfo;

        public WlanInterfaceInfoList(Pointer p) {
            super(p);
            read();
            InterfaceInfo = (WlanInterfaceInfo[]) new WlanInterfaceInfo().toArray(dwNumberOfItems);
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwNumberOfItems", "dwIndex", "InterfaceInfo");
        }

        public WlanInterfaceInfo[] getInterfaces() {
            return InterfaceInfo;
        }
    }
}
