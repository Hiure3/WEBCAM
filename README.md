# Camera Live Pro — projeto inicial

Este repositório contém uma **base inicial compilável** para o projeto Camera Live Pro:
- `android/`: aplicativo Android com pré-visualização local da câmera usando CameraX.
- `windows/`: aplicativo Windows de interface/diagnóstico.
- `.github/workflows/build.yml`: compila o APK Android e o instalador Windows no GitHub Actions.
- `installer/CameraLivePro.iss`: script do Inno Setup para gerar o instalador.

## Importante — estado real deste pacote
Esta é uma **base de MVP**, não uma substituição completa do app comercial de referência. A pré-visualização Android é implementada, mas a transmissão de vídeo do Android para o Windows, NDI real e webcam virtual do Windows **ainda não estão implementados**. Não use o app como se já transmitisse vídeo para OBS/vMix.

## Como gerar os arquivos no GitHub
1. Crie um repositório novo no GitHub.
2. Extraia o ZIP e envie **o conteúdo** desta pasta para o repositório (`Add file` → `Upload files`).
3. Abra a aba **Actions** e habilite workflows, se o GitHub solicitar.
4. Execute `Build Camera Live Pro` (`Run workflow`) ou envie um novo commit.
5. Aguarde a conclusão do workflow.
6. Abra a execução concluída e baixe os artefatos:
   - `CameraLivePro-Android-APK`
   - `CameraLivePro-Windows-Installer`
   - `CameraLivePro-Build-Reports`

O APK de depuração não é assinado para distribuição comercial. Para publicar na Play Store, configure assinatura de release segura.

## Compilar localmente
### Android
Requer JDK 17 e Gradle 8.9+:
```bash
cd android
gradle assembleDebug
```
APK esperado: `android/app/build/outputs/apk/debug/app-debug.apk`.

### Windows
Requer .NET 8 SDK:
```powershell
dotnet publish windows/CameraLivePro.Windows.csproj -c Release -r win-x64 --self-contained true -o publish
```
Para gerar o instalador, instale o Inno Setup 6 e compile `installer/CameraLivePro.iss` depois de apontar `AppSource` para a pasta publicada (ou use o workflow do GitHub Actions).

## Recursos incluídos
- Android: solicita permissão de câmera e exibe pré-visualização frontal/traseira.
- Windows: tela inicial de diagnóstico com status explícito de protótipo.
- Pipeline GitHub Actions para compilar APK e instalador Windows.

## Próximas etapas técnicas
1. Implementar transporte de vídeo autenticado por USB/Wi-Fi.
2. Implementar receptor Windows e protocolo de controle.
3. Integrar NDI usando SDK oficial e cumprindo seus termos.
4. Desenvolver webcam virtual compatível com a arquitetura escolhida.
5. Testar em hardware físico, OBS Studio e vMix.
