package dev.abarmin.aml.image;

import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
public class ImagePresets {
  public ImagePreset avatar() {
    return new ImagePreset() {
      @Override
      @SneakyThrows
      public byte[] apply(InputStream inputStream) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(inputStream)
          .outputFormat("JPEG")
          .outputQuality(0.9)
          .size(150, 150)
          .toOutputStream(outputStream);

        return outputStream.toByteArray();
      }
    };
  }
}
