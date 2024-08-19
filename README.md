<p align="center"> 
  <img src="https://github.com/Egg-03/FerrumX/assets/111327101/9aee9cdf-5213-401b-814d-a9738ee1a24c" alt="FerrumX logo">
</p>

# About
FerrumX is a lightweight Hardware and Network Information wrapper written in pure Java. It contacts some [Computer System Hardware Classes](https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/computer-system-hardware-classes) and [Operating System Classes](https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/operating-system-classes) of Windows Management Instrumentation (WMI) through PowerShell to fetch comprehensive system details on Windows Operating Systems.

It also has a simple user friendly GUI Application that lists hardware info in separate panels. Comes in 6 different FlatLaf Looks and Feels
![Untitled](https://github.com/user-attachments/assets/e2af21ab-8b69-42bc-aff7-e1403c4490f0)
For more images and tutorials on how to use the GUI, visit the wiki.

# Key Features
<ins>Pre-built Jar File:</ins> Simplifies usage by providing a downloadable jar file that can be directly imported as a classpath dependency. No external dependencies required (Skill issue: I need to learn about build tools.)

<ins>GUI Application:</ins> Included for end-users who require a quick and easy way to generate a system report encompassing hardware, network, and OS details.

# Supported Operating Systems
FerrumX has been tested to work exclusively on <strong>Windows 8.1, Windows 10 and Windows 11</strong> devices.
For Windows 7 and Vista support, see: [FerrumL](https://github.com/Egg-03/FerrumL) [not actively developed anymore]

# Installation
<h4>For Developers:</h4>

- Download the latest jar file from [FerrumX Releases](https://github.com/Egg-03/FerrumX/releases)
- Import the jar file as a classpath dependency in your project.

<h4>For End Users:</h4>

- Download the GUI Application from [FerrumX Releases](https://github.com/Egg-03/FerrumX/releases)
- Run the downloaded executable file.

# Dependencies
> [!NOTE]
> The wrapper does not have any dependencies.

> [!NOTE]
> The GUI Application has 3 dependencies:
> [Flatlaf](https://github.com/JFormDesigner/FlatLaf),
> [ini4j](https://ini4j.sourceforge.net/index.html),
> [jsvg](https://github.com/weisJ/jsvg)
> All of them are bundled within the application itself

# Documentation
Documentation can be found [here](https://egg-03.github.io/FerrumX-Documentation/)

# Usage
Please refer to our [Wiki](https://github.com/Egg-03/FerrumX/wiki) page to learn about the various functions and their usage, along with example code snippets.
You can also check out some examples [here](https://github.com/Egg-03/FerrumX/tree/be360eeb6bbf1ca6e992d5d8fbb1e2109bfa6514/src/com/ferrumx/tests)

For guidance on the GUI Application itself, check this tutorial out: 

# License
This project is licensed under the MIT License. Read the LICENSE.md for more information.
The GUI for FerrumX uses FlatLaf themes for it's Look and Feel, under the [Apache License 2.0](https://github.com/JFormDesigner/FlatLaf/blob/main/LICENSE)

# Special Thanks
[Rugino3](https://github.com/Soumil-Biswas) for the banner, icons for the GUI and documentation proofreading

> [!CAUTION]
> The GUI Application executable is wrapped using launch4j and is currently unsigned (I'm too poor to afford signing). As of now, the project's github repository is the only official distribution page for the program. Be careful if you've downloaded the program from other sources.
