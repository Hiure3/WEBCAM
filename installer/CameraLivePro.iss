; Inno Setup 6 — script do instalador Windows
#define MyAppName "Camera Live Pro"
#define MyAppVersion "0.1.0"
#define MyAppPublisher "Camera Live Pro Project"
#ifndef AppSource
  #define AppSource "..\publish"
#endif

[Setup]
AppId={{3B73D44D-DA4A-4E25-AF6B-CA6E5C5E4B31}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={autopf}\Camera Live Pro
DefaultGroupName=Camera Live Pro
OutputDir=output
OutputBaseFilename=CameraLivePro-Setup-x64
ArchitecturesInstallIn64BitMode=x64
Compression=lzma
SolidCompression=yes
WizardStyle=modern
UninstallDisplayIcon={app}\CameraLivePro.exe

[Files]
Source: "{#AppSource}\*"; DestDir: "{app}"; Flags: recursesubdirs ignoreversion

[Icons]
Name: "{group}\Camera Live Pro"; Filename: "{app}\CameraLivePro.exe"
Name: "{autodesktop}\Camera Live Pro"; Filename: "{app}\CameraLivePro.exe"; Tasks: desktopicon

[Tasks]
Name: "desktopicon"; Description: "Criar atalho na área de trabalho"; GroupDescription: "Atalhos:"

[Run]
Filename: "{app}\CameraLivePro.exe"; Description: "Abrir Camera Live Pro"; Flags: postinstall nowait skipifsilent
