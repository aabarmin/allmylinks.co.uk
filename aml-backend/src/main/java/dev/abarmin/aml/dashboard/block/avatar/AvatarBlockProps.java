package dev.abarmin.aml.dashboard.block.avatar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.file.FileId;
import dev.abarmin.aml.file.FileService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AvatarBlockProps implements BlockProps {
  public static final FileId DEFAULT_AVATAR = FileId.resource("/img/avatar_placeholder.png");

  private FileId avatarId;
  private FileId backgroundId;

  @Builder.Default
  private boolean showShareButton = false;

  @JsonIgnore
  public Map<String, String> getCoverStyle(FileService fileService) {
    final String backgroundPublicUrl = fileService.getPublicUrl(backgroundId);

    return Map.of(
      "background-image", "url(" + backgroundPublicUrl + ")",
      "background-position", "center",
      "background-repeat", "no-repeat"
    );
  }

  @JsonIgnore
  public Map<String, String> getAvatarStyle() {
    if (Objects.nonNull(backgroundId)) {
      return Map.of(
        "border", "5px solid #ffffff"
      );
    }
    return Map.of();
  }
}
