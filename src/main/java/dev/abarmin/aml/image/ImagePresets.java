package dev.abarmin.aml.image;

import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class ImagePresets {
  public ImagePreset noop() {
    return bufferedImage -> bufferedImage;
  }

  public ImagePreset avatarPreset() {
    return new ImagePreset() {
      @Override
      @SneakyThrows
      public BufferedImage apply(BufferedImage bufferedImage) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Thumbnails.of(bufferedImage)
          .outputFormat("JPEG")
          .outputQuality(0.9)
          .size(150, 150)
          .toOutputStream(outputStream);

        return ImageIO.read(new ByteArrayInputStream(outputStream.toByteArray()));
      }
    };
  }
}
