package dev.abarmin.aml.image;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ImageService {
  public byte[] process(@NonNull InputStream inputStream,
                        @NonNull ImagePreset preset) {

    return preset.apply(inputStream);
  }
}
