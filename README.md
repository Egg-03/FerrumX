<p align="center"> 
  <img src="https://github.com/Egg-03/FerrumX/assets/111327101/9aee9cdf-5213-401b-814d-a9738ee1a24c" alt="FerrumX logo">
</p>

[![Project Stats](https://openhub.net/p/FerrumX/widgets/project_thin_badge.gif)](https://openhub.net/p/FerrumX)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Egg-03_FerrumX&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Egg-03_FerrumX)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/Egg-03/FerrumX/.github%2Fworkflows%2Fbuild_windows_maven.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Egg-03_FerrumX&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Egg-03_FerrumX)

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
    <version>1.3.7</version>
</dependency>
```

Gradle:
```gradle
implementation group: 'io.github.egg-03', name: 'ferrum-x', version: '1.3.7'
```

For other build ecosystems, check out the [Maven Central Repository](https://central.sonatype.com/artifact/io.github.egg-03/ferrum-x/overview)

# Documentation
Documentation can be found [here](https://egg-03.github.io/FerrumX-Documentation/)

# Usage
Please refer to our [Wiki](https://github.com/Egg-03/FerrumX/wiki) page to learn about the various functions and their usage, along with example code snippets.
You can also check out some examples [here](https://github.com/Egg-03/FerrumX/tree/be360eeb6bbf1ca6e992d5d8fbb1e2109bfa6514/src/com/ferrumx/tests)

# License
This project is licensed under the MIT License. Read the LICENSE.md for more information.

# Special Thanks
[Rugino3](https://github.com/Soumil-Biswas) for the banner and documentation proofreading
