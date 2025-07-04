package dev.abarmin.aml.image;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.function.Function;

@UtilityClass
public class ImageHelper {
  @SneakyThrows
  public static <T> T with(@NonNull MultipartFile file, @NonNull Function<BufferedImage, T> processor) {
    try (final InputStream inputStream = file.getInputStream()) {
      final BufferedImage bufferedImage = ImageIO.read(inputStream);
      return processor.apply(bufferedImage);
    }
  }
}
