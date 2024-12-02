package dev.abarmin.aml.image;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
@RequiredArgsConstructor
public class ImageService {
  public BufferedImage process(@NonNull BufferedImage source, @NonNull ImagePreset preset) {
    return preset.apply(source);
  }
}
