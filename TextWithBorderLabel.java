package Proyecto;
import javax.swing.*;
import java.awt.*;

public class TextWithBorderLabel extends JLabel {

    private Color outerBorderColor; // Color del borde exterior
    private Color innerTextColor;  // Color del texto interior

    public TextWithBorderLabel(String text, Color outerBorderColor, Color innerTextColor) {
        super(text);
        this.outerBorderColor = outerBorderColor;
        this.innerTextColor = innerTextColor;
        setHorizontalAlignment(SwingConstants.LEFT); // Ajusta alineación según sea necesario
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Habilitar suavizado para bordes más limpios
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibuja el borde exterior (más grueso)
        g2d.setFont(getFont());
        g2d.setColor(outerBorderColor);

        // Dibujar el texto con desplazamiento en 4 direcciones para simular el borde grueso
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                if (x == 0 && y == 0) continue; // Saltar el centro
                g2d.drawString(getText(), getInsets().left + x, getInsets().top + getFontMetrics(getFont()).getAscent() + y);
            }
        }

        // Dibuja el texto interior
        g2d.setColor(innerTextColor);
        g2d.drawString(getText(), getInsets().left, getInsets().top + getFontMetrics(getFont()).getAscent());

        super.paintComponent(g);
    }
}
