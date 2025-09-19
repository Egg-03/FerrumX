package com.ferrumx.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CimQuery {

    BATTERY_QUERY("Get-CimInstance Win32_Battery | Select-Object * | ConvertTo-Json"),

    MONITOR_QUERY("Get-CimInstance Win32_DesktopMonitor | Select-Object * | ConvertTo-Json"),
    VIDEO_CONTROLLER_QUERY("Get-CimInstance Win32_VideoController | Select-Object * | ConvertTo-Json"),

    PROCESSOR_QUERY("Get-CimInstance Win32_Processor | Select-Object * | ConvertTo-Json"),
    PROCESSOR_CACHE_QUERY("Get-CimInstance Win32_CacheMemory | Select-Object * | ConvertTo-Json"),

    BIOS_QUERY("Get-CimInstance Win32_BIOS | Where-Object { $_.PrimaryBIOS -eq $true } | Select-Object * | ConvertTo-Json"),
    MAINBOARD_QUERY("Get-CimInstance Win32_Baseboard | Select-Object * | ConvertTo-Json"),
    MAINBOARD_PORT_QUERY("Get-CimInstance Win32_PortConnector | Select-Object * | ConvertTo-Json"),

    PHYSICAL_MEMORY_QUERY("Get-CimInstance Win32_PhysicalMemory | Select-Object * | ConvertTo-Json"),

    NETWORK_ADAPTER_QUERY("Get-CimInstance Win32_NetworkAdapter | Select-Object * | ConvertTo-Json"),
    NETWORK_ADAPTER_CONFIGURATION_QUERY("Get-CimInstance Win32_NetworkAdapterConfiguration | Select-Object * | ConvertTo-Json"),

    OPERATING_SYSTEM_QUERY("Get-CimInstance Win32_OperatingSystem | Select-Object * | ConvertTo-Json"),

    DISK_QUERY("Get-CimInstance Win32_DiskDrive | Select-Object * | ConvertTo-Json"),
    DISK_PARTITION_QUERY("Get-CimInstance Win32_DiskPartition | Select-Object * | ConvertTo-Json");

    private final String query;
}
