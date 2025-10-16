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
For Windows 7 and Vista support, see: [FerrumL](https://github.com/Egg-03/FerrumL)

# Download
Maven:
```xml
<dependency>
    <groupId>io.github.eggy03</groupId>
    <artifactId>ferrumx-windows</artifactId>
    <version>2.2.0</version>
</dependency>
```

Gradle:
```gradle
implementation group: 'io.github.eggy03', name: 'ferrumx-windows', version: '2.2.0'
```

For other build ecosystems, check out the [Maven Central Repository](https://central.sonatype.com/artifact/io.github.egg-03/ferrum-x/overview)

# Documentation
Documentation can be found [here](https://egg-03.github.io/FerrumX-Documentation/)

# Usage
> [!IMPORTANT]
> More usage examples can be found in the [Wiki](https://github.com/Egg-03/FerrumX/wiki).

```java
public class ProcessorExample {

    static void main(String[] args) {
        
        List<Processor> processorList = new ProcessorService().get();
        
        // you can also create and manage your own re-usable PowerShell session
        // good for cases where you need to fetch results for multiple queries
        List<Processor> processorListTwo;
        try(PowerShell session = PowerShell.openSession()) {
            processorListTwo = new ProcessorService().get(session);
            processorListTwo.forEach(processor -> log.info(processor.toString()));
        }

        // individual fields are accessible via getter methods
        log.info("Processor Name: {}", processorList.getFirst().getName());
        log.info("Processor Manufacturer: {}", processorList.getFirst().getManufacturer());
        log.info("Processor Max Clock Speed: {} MHz", processorList.getFirst().getMaxClockSpeed());
    }
}
```

# License
This project is licensed under the MIT License. Read the LICENSE.md for more information.

# Information about v2

- Changes incorporated in v2.0.0 from v1.3.7 can be found in this [PR](https://github.com/Egg-03/FerrumX/pull/20)
- A migration guide will be provided in the project's [Wiki](https://github.com/Egg-03/FerrumX/wiki) if you want to migrate from v1 to v2


