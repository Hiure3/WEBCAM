using System.Drawing;
using System.Windows.Forms;

namespace CameraLivePro;

internal static class Program
{
    [STAThread]
    static void Main()
    {
        ApplicationConfiguration.Initialize();
        Application.Run(new MainForm());
    }
}

internal sealed class MainForm : Form
{
    private readonly Label status = new();
    public MainForm()
    {
        Text = "Camera Live Pro";
        MinimumSize = new Size(760, 500);
        Size = new Size(960, 640);
        BackColor = Color.FromArgb(16, 21, 29);
        ForeColor = Color.White;
        Font = new Font("Segoe UI", 10);
        var title = new Label {
            Text = "CAMERA LIVE PRO",
            Dock = DockStyle.Top,
            Height = 64,
            TextAlign = ContentAlignment.MiddleCenter,
            Font = new Font("Segoe UI", 22, FontStyle.Bold),
            ForeColor = Color.FromArgb(40, 199, 250)
        };
        var preview = new Panel {
            Dock = DockStyle.Fill,
            BackColor = Color.FromArgb(28, 36, 48),
            Margin = new Padding(20)
        };
        var previewLabel = new Label {
            Text = "PRÉ-VISUALIZAÇÃO\\r\\n\\r\\nO receptor de vídeo ainda não foi implementado.",
            Dock = DockStyle.Fill,
            TextAlign = ContentAlignment.MiddleCenter,
            ForeColor = Color.Gainsboro,
            Font = new Font("Segoe UI", 14)
        };
        preview.Controls.Add(previewLabel);
        status.Text = "Estado: versão inicial — sem transmissão Android/NDI";
        status.Dock = DockStyle.Bottom;
        status.Height = 42;
        status.TextAlign = ContentAlignment.MiddleCenter;
        status.ForeColor = Color.Orange;
        var footer = new Label {
            Text = "Próxima etapa: transporte de vídeo, integração NDI e webcam virtual.",
            Dock = DockStyle.Bottom,
            Height = 34,
            TextAlign = ContentAlignment.MiddleCenter,
            ForeColor = Color.LightGray
        };
        Controls.Add(preview);
        Controls.Add(footer);
        Controls.Add(status);
        Controls.Add(title);
    }
}
