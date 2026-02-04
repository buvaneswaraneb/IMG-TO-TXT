package org.soul;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    static void main() {
        // Tesseract
        Tesseract tesseract = new Tesseract();
        File file = new File("src/Asserts/Input_images/img.png");
        try {
            tesseract.setDatapath("/opt/homebrew/share/tessdata");
            tesseract.setLanguage("eng");
            BufferedImage img = ImageIO.read(file);

            BufferedImage gray = new BufferedImage(
                    img.getWidth(),
                    img.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY
            );

            Graphics2D g = gray.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();

            tesseract.setOcrEngineMode(1);
            tesseract.setPageSegMode(6);

            String ans  = tesseract.doOCR(gray);
            System.out.println(ans);


        } catch (TesseractException e) {
            log.error("e: ", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

