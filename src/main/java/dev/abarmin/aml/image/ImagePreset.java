package dev.abarmin.aml.image;

import java.awt.image.BufferedImage;
import java.util.function.Function;

public interface ImagePreset extends Function<BufferedImage, BufferedImage> {
}
