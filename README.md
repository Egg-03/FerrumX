<p align="center"> 
  <img src="https://github.com/Egg-03/FerrumX/assets/111327101/9aee9cdf-5213-401b-814d-a9738ee1a24c" alt="FerrumX logo">
</p>

[![Project Stats](https://openhub.net/p/FerrumX/widgets/project_thin_badge.gif)](https://openhub.net/p/FerrumX)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Egg-03_FerrumX&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Egg-03_FerrumX)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/Egg-03/FerrumX/.github%2Fworkflows%2Fbuild.yml)


[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Egg-03_FerrumX&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=Egg-03_FerrumX)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Egg-03_FerrumX&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=Egg-03_FerrumX)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Egg-03_FerrumX&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Egg-03_FerrumX)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Egg-03_FerrumX&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Egg-03_FerrumX)

[![License](https://img.shields.io/github/license/Egg-03/FerrumX)](https://github.com/Egg-03/FerrumX/blob/main/LICENSE)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.egg-03/ferrum-x)](https://central.sonatype.com/artifact/io.github.egg-03/ferrum-x)
![Commits to main since latest release](https://img.shields.io/github/commits-since/Egg-03/FerrumX/latest)

# About
FerrumX is a lightweight Hardware and Network Information wrapper written in pure Java. It contacts some [Computer System Hardware Classes](https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/computer-system-hardware-classes) and [Operating System Classes](https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/operating-system-classes) of Windows Management Instrumentation (WMI) through PowerShell to fetch comprehensive system details on Windows Operating Systems.

# Supported Operating Systems
FerrumX has been tested to work exclusively on <strong>Windows 8.1, Windows 10 and Windows 11</strong> devices.
For Windows 7 and Vista support, see: [FerrumL](https://github.com/Egg-03/FerrumL) [not actively developed anymore]

# Download
Maven:
```xml
<dependency>
    <groupId>io.github.egg-03</groupId>
    <artifactId>ferrum-x</artifactId>
    <version>1.3.7</version> // OUTDATED (will be updated soon)
</dependency>
```

Gradle:
```gradle
implementation group: 'io.github.egg-03', name: 'ferrum-x', version: '1.3.7'
```

For other build ecosystems, check out the [Maven Central Repository](https://central.sonatype.com/artifact/io.github.egg-03/ferrum-x/overview)

# Documentation
Documentation can be found [here](https://egg-03.github.io/FerrumX-Documentation/) // WILL BE UPDATED

# Usage
// TODO

# License
This project is licensed under the MIT License. Read the LICENSE.md for more information.

# New in v2

> [!NOTE]
> Read this only if you have ever used v1
> 
> I will work on a migration from v1 to v2 guide soon

### **Visual and Functional Overhaul**

1. The legacy shell classes and custom parsing logic have been completely removed and replaced with a new service/entity structure.
2. Each service now runs a powershell query via jPowershell that parses the JSON output to it's respective entity class via GSON. Instead of a Map data structure in v1, you now get typed objects with their fields which are accessible via the provided getters.
3. Removed all forced checked exceptions. The only time an unchecked exception may be thrown is if the JSON is malformed.
4. Improved null safety with the usage of Optional and empty Lists.

### **Features Added/Removed**

1. A new class `ComputerSystemProductService` fetches detailed product information like vendor, name, and UUID.
2. Removed custom HWID generation logic.
3. Removed `Win32_Printer` and `Win32_SoundDevice` classes.
4. Removed the associative classes `Win32_AssociatedProcessorMemory`, `Win32_NetworkAdapterSetting`. They are no longer needed to link Processor with its Cache and a Network Adapter with its Adapter Configuration respectively. However, `Win32_DiskDriveToDiskPartition` and `Win32_LogicalDiskToPartition` are required to link drive letters and partition names to the disks. Right now, they have been removed and a suitable implementation has not been decided yet.


### **Project Structure Changes**

1.Multi-Module Project: The codebase is now split into a multi-module Maven project:

    ferrumx-core: Contains the main library logic.
    ferrumx-examples: Provides practical usage demonstrations.

2.Package Refactoring: All core packages have been moved under the `org.ferrumx.core` namespace, and the root package was renamed from `com.ferrumx` to `org.ferrumx` to align with open-source conventions.


### **Testing and Documentation**

1.Unit Testing: An extensive suite of unit tests has been added for all core services using Mockito, ensuring the stability and correctness of the data retrieval logic.

2.Javadoc: All public classes and methods in the ferrumx-core and ferrumx-examples modules have been documented with comprehensive Javadoc, clarifying their purpose and usage.
