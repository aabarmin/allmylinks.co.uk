package dev.abarmin.aml.profile;

import lombok.NonNull;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class QrCodeService {
  public ByteArrayOutputStream generate(final @NonNull Request request) {
    return QRCode.from(request.text())
      .withSize(request.width(), request.height())
      .to(request.imageType())
      .stream();
  }

  public record Request(String text, int width, int height, ImageType imageType) {}
}
