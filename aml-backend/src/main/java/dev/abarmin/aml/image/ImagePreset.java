package dev.abarmin.aml.image;

import java.io.InputStream;
import java.util.function.Function;

public interface ImagePreset extends Function<InputStream, byte[]> {

}
